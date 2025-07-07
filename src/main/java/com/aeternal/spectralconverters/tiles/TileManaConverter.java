//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.spectralconverters.tiles;


import com.aeternal.Constants;
import com.aeternal.Core;
import com.aeternal.botaniverse.blocks.tiles.TileMorePool;
import com.aeternal.spectralconverters.blocks.BlockManaConverter;
import com.aeternal.spectralconverters.container.ContainerManaConverter;
import com.aeternal.spectralconverters.gui.GuiManaConverter;
import com.aeternal.Config;
import com.denfop.IUCore;
import com.denfop.Localization;
import com.denfop.api.energy.EnergyNetGlobal;
import com.denfop.api.energy.NodeStats;
import com.denfop.api.tile.IMultiTileBlock;
import com.denfop.api.upgrades.IUpgradableBlock;
import com.denfop.api.upgrades.UpgradableProperty;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.componets.Energy;
import com.denfop.invslot.InvSlotUpgrade;
import com.denfop.network.DecoderHandler;
import com.denfop.network.EncoderHandler;
import com.denfop.network.IUpdatableTileEvent;
import com.denfop.network.packet.CustomPacketBuffer;
import com.denfop.tiles.base.TileEntityInventory;
import com.denfop.tiles.panels.entity.TransferRFEnergy;
import com.denfop.utils.ModUtils;
import com.google.common.base.Predicates;
import com.meteor.extrabotany.common.block.tile.TileManaBuffer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.zeitheron.botanicadds.blocks.tiles.TileDreamingPool;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.common.block.tile.mana.TilePool;

import java.io.IOException;
import java.util.*;

public class TileManaConverter extends TileEntityInventory implements
        IUpdatableTileEvent, IManaReceiver, ISparkAttachable, IUpgradableBlock {


    public final Energy energy;
    public final InvSlotUpgrade upgradeSlot;
    public final double defaultEnergyStorage;
    public double capacity;
    public double differenceenergy = 0;
    public double perenergy = 0;
    public double differenceenergy1 = 0;
    public int tier;
    public List<EntityPlayer> list = new ArrayList<>();
    List<TransferRFEnergy> transferRFEnergyList = new ArrayList<>();
    private long tick;

    public int mana;
    public int manaCap;
    public boolean mn;
    public final int defaultManaStorage;
    public int transferSpeed;

    private String TAG_MANA;
    private String TAG_MANA_CAP;


    public TileManaConverter() {
        this.energy = this.addComponent((new Energy(this, 40000, ModUtils.allFacings,
                ModUtils.allFacings,
                5,
                5, false
        )));
        this.capacity = this.energy.capacity;
        this.energy.setDirections(ModUtils.allFacings, ModUtils.allFacings);
        this.upgradeSlot = new InvSlotUpgrade(this, 4);
        this.defaultEnergyStorage = 40000;
        this.tier = 5;
        this.tick = 0;

        this.TAG_MANA = "mana";
        this.TAG_MANA_CAP = "manaCap";
        this.mana = 0;
        this.manaCap = 400000;
//        this.mn = true;
        this.defaultManaStorage = 400000;
        this.transferSpeed = 1000;
        if (!Config.allowEfToManaConversion) {
            mn = false;
        }
        else if (!Config.allowManaToEfConversion) {
            mn = true;
        }
    }
    public IMultiTileBlock getTeBlock() {
        return BlockManaConverter.mana_converter;
    }

    public BlockTileEntity getBlock() {
        return Core.itemManaConverter;
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return !mn;
    }

    @Override
    public boolean isFull() {
        return mn || mana >= manaCap;
    }

    public int getMaxMana() {
        return manaCap;
    }

    public int getTransferSpeed() {
        return this.transferSpeed;
    }

    @Override
    public void recieveMana(int delta) {
        if (delta < 0) {
            mana = Math.max(0, mana + delta);
        } else if (delta > 0 && !mn) {
            // allow deposit only in MN→EF
            mana = Math.min(manaCap, mana + delta);
        }
    }


    @Override
    public int getAvailableSpaceForMana() {
        return !mn
                ? Math.max(0, manaCap - mana)
                : 0;
    }
    @Override
    public int getCurrentMana() {
        return mana;
    }

    @Override
    public boolean areIncomingTranfersDone() {
        if (!mn) return true;
        return false;
    }


    @Override
    public void attachSpark(ISparkEntity arg0) {
    }

    @Override
    public boolean canAttachSpark(ItemStack arg0) {
        return true;
    }

    @Override
    public ISparkEntity getAttachedSpark() {
        List sparks = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.up(), pos.up().add(1, 1, 1)),
                Predicates.instanceOf(ISparkEntity.class));
        if (sparks.size() == 1) {
            Entity e = (Entity) sparks.get(0);
            return (ISparkEntity) e;
        }
        return null;
    }


    @SideOnly(Side.CLIENT)

    public void addInformation(ItemStack stack, List<String> tooltip) {
        if (this.hasComp(Energy.class)) {
            Energy energy = this.getComp(Energy.class);
            if (!energy.getSourceDirs().isEmpty()) {
                tooltip.add(Localization.translate("iu.item.tooltip.PowerTier", energy.getSourceTier()));
            } else if (!energy.getSinkDirs().isEmpty()) {
                tooltip.add(Localization.translate("iu.item.tooltip.PowerTier", energy.getSinkTier()));
            }
        }

    }

    public void onLoaded() {
        super.onLoaded();
        if (IUCore.proxy.isSimulating()) {
            this.setOverclockRates();
        }

    }

    @Override
    public void readContainerPacket(final CustomPacketBuffer customPacketBuffer) {
        super.readContainerPacket(customPacketBuffer);
        try {
            capacity = (double) DecoderHandler.decode(customPacketBuffer);
            perenergy = (double) DecoderHandler.decode(customPacketBuffer);
            tier = (int) DecoderHandler.decode(customPacketBuffer);
            differenceenergy1 = (double) DecoderHandler.decode(customPacketBuffer);
            differenceenergy = (double) DecoderHandler.decode(customPacketBuffer);
            mana = (int) DecoderHandler.decode(customPacketBuffer);
            manaCap = (int) DecoderHandler.decode(customPacketBuffer);
            mn = (boolean) DecoderHandler.decode(customPacketBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CustomPacketBuffer writeContainerPacket() {
        final CustomPacketBuffer packet = super.writeContainerPacket();
        try {
            EncoderHandler.encode(packet, capacity);
            EncoderHandler.encode(packet, perenergy);
            EncoderHandler.encode(packet, tier);
            EncoderHandler.encode(packet, differenceenergy1);
            EncoderHandler.encode(packet, differenceenergy);
            EncoderHandler.encode(packet, mana);
            EncoderHandler.encode(packet, manaCap);
            EncoderHandler.encode(packet, mn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return packet;
    }

    public void setOverclockRates() {
        int tier = this.upgradeSlot.getTier(5);
        this.energy.setSinkTier(tier);
        this.energy.setSourceTier(tier);
        this.energy.setCapacity(this.upgradeSlot.extraEnergyStorage +
                this.defaultEnergyStorage
        );
        this.manaCap = (int) (this.defaultManaStorage + this.upgradeSlot.extraEnergyStorage * Config.coefficienteftomana);
        this.tier = tier;
        this.capacity = this.energy.capacity;
    }


    public void markDirty() {
        super.markDirty();
        if (IUCore.proxy.isSimulating()) {
            this.setOverclockRates();
        }

    }

private static final BlockPos[] POOL_LOCATIONS = {
        new BlockPos( 1, 0, 0), new BlockPos( 0, 0, 1),
        new BlockPos(-1, 0, 0), new BlockPos( 0, 0,-1),
        new BlockPos( 0,-1, 0)
};
    private static final BlockPos[] POOL_LOCATIONS2 = {
            new BlockPos(0, 1, 0)
    };

    public void updateEntityServer() {
        if (!Config.allowEfToManaConversion && !Config.allowManaToEfConversion) return;
        super.updateEntityServer();
        this.energy.setReceivingEnabled(!this.shouldEmitEnergy());
        this.energy.setSendingEnabled(this.shouldEmitEnergy());
        this.differenceenergy = 0;
        this.differenceenergy1 = 0;
        if (this.tick != this.getWorld().getWorldTime()) {
            this.perenergy = 0;
        }
        if (this.mn && Config.allowEfToManaConversion) {
            if (energy.getEnergy() > 0 && mana < manaCap) {
                double add = Math.min(manaCap - mana, energy.getEnergy() * Config.coefficienteftomana);
                add = Math.max(add, 0);
                mana += add;
                energy.useEnergy(add / Config.coefficienteftomana);
                this.perenergy = add;

            }
        } else {
            if(Config.allowManaToEfConversion){
                if (mana > 0 && energy.getEnergy() < energy.getCapacity()) {
                    double k = energy.addEnergy(mana / Config.coefficientmanatoef) * Config.coefficientmanatoef;
                    mana -= k;
                    this.perenergy = k;
                }
            }



        }
        if (!this.list.isEmpty()) {
            NodeStats stats = EnergyNetGlobal.instance.getNodeStats(this.energy.getDelegate());
            if (this.mn) {
                if (stats != null) {
                    this.differenceenergy1 = stats.getEnergyIn();
                }

            } else {
                if (stats != null) {
                    this.differenceenergy = stats.getEnergyOut();
                }

            }
        }

        if (!mn) {
            int speed = transferSpeed * Math.max(energy.getSourceTier(), energy.getSinkTier());
            if (mana < manaCap) {
                for (BlockPos off : POOL_LOCATIONS) {
                    TileEntity te = world.getTileEntity(pos.add(off));
                    // Botania pool
                    if (te instanceof TilePool) {
                        TilePool p = (TilePool) te;
                        int want  = Math.min(speed, p.getCurrentMana());
                        int space = manaCap - mana;
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            p.recieveMana(-move);
                            mana += move;
                        }
                    }
                    //converters
                    else if (te instanceof TileManaConverter) {
                        TileManaConverter c = (TileManaConverter) te;
                        int want  = Math.min(speed, c.mana);
                        int space = manaCap - mana;
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            c.recieveMana(-move);
                            mana += move;
                        }
                    }
                    // ExtraBotany buffers
                    if (Constants.EB_LOADED && te instanceof TileManaBuffer) {
                        TileManaBuffer b = (TileManaBuffer) te;
                        int want  = Math.min(speed, b.getCurrentMana());
                        int space = manaCap - mana;
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            b.recieveMana(-move);
                            mana += move;
                        }
                    }
                    //Botaniverse Pools
                    if (Constants.BV_LOADED && te instanceof TileMorePool) {
                        TileMorePool p = (TileMorePool) te;
                        int want  = Math.min(speed, p.getCurrentMana());
                        int space = manaCap - mana;
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            p.recieveMana(-move);
                            mana += move;
                        }
                    }
                }
            }
        }

        if (mn) {
            int speed = transferSpeed * Math.max(energy.getSourceTier(), energy.getSinkTier());
            if (mana > 0) {
                for (BlockPos off : POOL_LOCATIONS2) {
                    TileEntity te = world.getTileEntity(pos.add(off));
                    // Botania pool
                    if (te instanceof TilePool) {
                        TilePool p = (TilePool) te;
                        int want  = Math.min(speed, mana);
                        int space = p.manaCap - p.getCurrentMana();
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            p.recieveMana(move);
                            mana -= move;
                        }
                    }
                    else if (te instanceof TileManaConverter) {
                        TileManaConverter c = (TileManaConverter) te;
                        int want  = Math.min(speed, mana);
                        int space = c.manaCap - c.mana;
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            c.recieveMana(move);
                            mana -= move;
                        }
                    }
                    if (Constants.EB_LOADED && te instanceof TileManaBuffer) {
                        TileManaBuffer b = (TileManaBuffer) te;
                        int want  = Math.min(speed, mana);
                        int space = b.getMaxMana() - b.getCurrentMana();
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            b.recieveMana(move);
                            mana -= move;
                        }
                    }
                    // Botaniverse pool
                    if (Constants.BV_LOADED && te instanceof TileMorePool) {
                        TileMorePool p = (TileMorePool) te;
                        int want  = Math.min(speed, mana);
                        int space = p.manaCap - p.getCurrentMana();
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            p.recieveMana(move);
                            mana -= move;
                        }
                    }
                    // Botaniverse pool
                    if (Constants.BADD_LOADED && te instanceof TileDreamingPool) {
                        TileDreamingPool  p = (TileDreamingPool ) te;
                        int want  = Math.min(speed, mana);
                        int space = p.MAX_MANA - p.getCurrentMana();
                        int move  = Math.min(want, space);
                        if (move > 0) {
                            p.recieveMana(move);
                            mana -= move;
                        }
                    }
                }

            }
        }





        final boolean needsInvUpdate = this.upgradeSlot.tickNoMark();
        if (needsInvUpdate) {
            setOverclockRates();
        }
        this.mana = Math.min(this.mana, this.manaCap);
    }

    protected boolean shouldEmitEnergy() {

        return !this.mn;

    }

    public int extractEnergy(int paramInt, boolean paramBoolean) {
        int i = (int) Math.min(this.mana, paramInt);
        if (!paramBoolean) {
            this.mana -= i;
            this.differenceenergy += i;
        }
        return i;
    }

    public boolean canConnectEnergy(EnumFacing arg0) {
        return true;
    }

    public int getEnergyStored(EnumFacing from) {
        return (int) this.mana;
    }

    public int getMaxEnergyStored(EnumFacing from) {
        return (int) this.manaCap;
    }


    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.energy.setDirections(ModUtils.allFacings, ModUtils.allFacings);
        this.tier = nbttagcompound.getInteger("tier");

        this.mana = ModUtils.limit(nbttagcompound.getInteger("mana"), 0,
                this.manaCap
        );
        this.mn = nbttagcompound.getBoolean("mn");

    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("tier", this.tier);

        if (mana > 0) {
            nbttagcompound.setInteger("mana", this.mana);
        }
        nbttagcompound.setBoolean("mn", this.mn);
        return nbttagcompound;
    }

    public int getCapacity() {
        return (int) this.energy.getCapacity();
    }


    public int getOutput() {
        return (int) EnergyNetGlobal.instance.getPowerFromTier(this.energy.getSourceTier());
    }

    public double getOutputEnergyUnitsPerTick() {
        return EnergyNetGlobal.instance.getPowerFromTier(this.energy.getSourceTier());
    }


    public void setStored(int energy1) {

    }

    public int addEnergy(int amount) {
        this.energy.addEnergy(amount);
        return amount;
    }


    public boolean isTeleporterCompatible(EnumFacing side) {
        return true;
    }

    public ContainerManaConverter getGuiContainer(EntityPlayer player) {
        list.add(player);
        return new ContainerManaConverter(player, this);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiManaConverter(getGuiContainer(entityPlayer));
    }


    public void updateTileServer(EntityPlayer player, double event) {
        this.mn = !this.mn;

    }

    public int gaugeICEnergyScaled(int i) {
        return (int) Math.min(this.energy.getEnergy() * i / this.energy.getCapacity(), i);
    }

    public int gaugeTEEnergyScaled(int i) {
        this.manaCap = (int) (this.defaultManaStorage + this.upgradeSlot.extraEnergyStorage * Config.coefficienteftomana);
        return (int) Math.min(this.mana * i / this.manaCap, i);
    }

    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(
                UpgradableProperty.Transformer,
                UpgradableProperty.EnergyStorage
        );
    }


}