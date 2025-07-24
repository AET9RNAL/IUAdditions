//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.spectralconverters.tiles;

import cofh.redstoneflux.api.IEnergyHandler;
import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import com.aeternal.Constants;
import com.aeternal.Core;
import com.aeternal.spectralconverters.blocks.BlockSpectralConverter;
import com.aeternal.spectralconverters.container.ContainerSpectralConverter;
import com.aeternal.spectralconverters.gui.GuiSpectralConverter;
import com.brandon3055.draconicevolution.DEFeatures;
import com.brandon3055.draconicevolution.blocks.tileentity.TileEnergyStorageCore;
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
import com.google.common.collect.Iterables;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.*;

public class TileSpectralConverter extends TileEntityInventory implements
        IUpdatableTileEvent, IEnergyHandler, IEnergyReceiver, IEnergyProvider, IUpgradableBlock {
    protected BlockPos foundCore = null;
    protected int energyCoreSearchFailedCount = 0;
    public static boolean delayedEnergyCoreSearch = true;
    public static int energyCoreSearchDelay = 100;
    public static int maxEnergyCoreSearchDelay = 300;
    public static byte coreSearchRange = Config.coreSearchRange;

    public final Energy energy;
    public final InvSlotUpgrade upgradeSlot;
    public final double defaultEnergyRFStorage;
    public final double defaultEnergyStorage;
    public double capacity;
    public double maxStorage2;
    public double energy2;
    public boolean rf;
    public double differenceenergy = 0;
    public double perenergy = 0;
    public double differenceenergy1 = 0;
    public int tier;
    public List<EntityPlayer> list = new ArrayList<>();
    List<TransferRFEnergy> transferRFEnergyList = new ArrayList<>();
    private long tick;
    public boolean isCore;
    public boolean isCoreActive;
    public long coreEnergy;
    public long coreCapacity;

    public TileSpectralConverter() {
        this.energy2 = 0.0D;
        this.maxStorage2 = 7.5E10;
        this.rf = true;
        this.energy = this.addComponent((new Energy(this, 7.5E10, ModUtils.allFacings,
                ModUtils.allFacings,
                15,
                15, false
        )));
        this.capacity = this.energy.capacity;
        this.energy.setDirections(ModUtils.allFacings, ModUtils.allFacings);
        this.upgradeSlot = new InvSlotUpgrade(this, 4);
        this.defaultEnergyStorage = 10000000;
        this.defaultEnergyRFStorage = 40000000;
        this.tier = 15;
        this.tick = 0;
        this.isCore = false;
        this.isCoreActive = false;
        this.coreEnergy = (long) 0.0;
        this.coreCapacity = (long) 0.0;
    }

    public IMultiTileBlock getTeBlock() {
        return BlockSpectralConverter.spectral_converter;
    }

    public BlockTileEntity getBlock() {
        return Core.itemSpectralPowerConverter;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip, ITooltipFlag advanced) {
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
            energy2 = (double) DecoderHandler.decode(customPacketBuffer);
            capacity = (double) DecoderHandler.decode(customPacketBuffer);
            maxStorage2 = (double) DecoderHandler.decode(customPacketBuffer);
            perenergy = (double) DecoderHandler.decode(customPacketBuffer);
            rf = (boolean) DecoderHandler.decode(customPacketBuffer);
            tier = (int) DecoderHandler.decode(customPacketBuffer);
            differenceenergy1 = (double) DecoderHandler.decode(customPacketBuffer);
            differenceenergy = (double) DecoderHandler.decode(customPacketBuffer);
            isCore = (boolean) DecoderHandler.decode(customPacketBuffer);
            isCoreActive = (boolean) DecoderHandler.decode(customPacketBuffer);
            coreEnergy = (long)  DecoderHandler.decode(customPacketBuffer);
            coreCapacity = (long)  DecoderHandler.decode(customPacketBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CustomPacketBuffer writeContainerPacket() {
        final CustomPacketBuffer packet = super.writeContainerPacket();
        try {
            EncoderHandler.encode(packet, energy2);
            EncoderHandler.encode(packet, capacity);
            EncoderHandler.encode(packet, maxStorage2);
            EncoderHandler.encode(packet, perenergy);
            EncoderHandler.encode(packet, rf);
            EncoderHandler.encode(packet, tier);
            EncoderHandler.encode(packet, differenceenergy1);
            EncoderHandler.encode(packet, differenceenergy);
            EncoderHandler.encode(packet, isCore);
            EncoderHandler.encode(packet, isCoreActive);
            EncoderHandler.encode(packet, coreEnergy);
            EncoderHandler.encode(packet, coreCapacity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return packet;
    }

    public void setOverclockRates() {
        int tier = this.upgradeSlot.getTier(15);
        this.energy.setSinkTier(tier);
        this.energy.setSourceTier(tier);
        this.energy.setCapacity(this.upgradeSlot.extraEnergyStorage*10 +
                this.defaultEnergyStorage
        );
        this.maxStorage2 = this.defaultEnergyRFStorage + this.upgradeSlot.extraEnergyStorage*10 * Config.coefficientrf;
        this.tier = tier;
        this.capacity = this.energy.capacity;
    }


    public void markDirty() {
        super.markDirty();
        if (IUCore.proxy.isSimulating()) {
            this.setOverclockRates();
        }

    }

    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return !this.rf ? receiveEnergy(maxReceive, simulate) : 0;

    }

    public int receiveEnergy(int paramInt, boolean paramBoolean) {
        int i = Math.max(0, (int) Math.min(
                this.maxStorage2 - this.energy2,
                Math.min(
                        EnergyNetGlobal.instance.getPowerFromTier(this.energy.getSourceTier() * Config.coefficientrf),
                        paramInt
                )
        ));
        if (!paramBoolean) {
            this.energy2 += i;
            this.differenceenergy1 += i;
            if (this.tick != this.getWorld().getWorldTime()) {
                this.tick = this.getWorld().getWorldTime();
                this.perenergy = i;
            } else {
                this.perenergy += i;
            }
        }
        return i;
    }

    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        return extractEnergy((int) Math.min(
                EnergyNetGlobal.instance.getPowerFromTier(this.energy.getSourceTier() * Config.coefficientrf),
                maxExtract
        ), simulate);
    }


    public long transferLimit;

    public void updateEntityServer() {
        super.updateEntityServer();
        this.energy.setReceivingEnabled(!this.shouldEmitEnergy());
        this.energy.setSendingEnabled(this.shouldEmitEnergy());
        this.differenceenergy = 0;
        this.differenceenergy1 = 0;
        if (this.tick != this.getWorld().getWorldTime()) {
            this.perenergy = 0;
        }
        if (this.rf) {
            if (energy.getEnergy() > 0 && energy2 < maxStorage2) {
                double add = Math.min(maxStorage2 - energy2, energy.getEnergy() * Config.coefficientrf);
                add = Math.max(add, 0);
                energy2 += add;
                energy.useEnergy(add / Config.coefficientrf);
            }
        } else {

            if (energy2 > 0 && energy.getEnergy() < energy.getCapacity()) {
                double k = energy.addEnergy(energy2 / Config.coefficientrf) * Config.coefficientrf;
                energy2 -= k;
            }

        }
        if (!this.list.isEmpty()) {
            NodeStats stats = EnergyNetGlobal.instance.getNodeStats(this.energy.getDelegate());
            if (this.rf) {
                if (stats != null) {
                    this.differenceenergy1 = stats.getEnergyIn();
                }

            } else {
                if (stats != null) {
                    this.differenceenergy = stats.getEnergyOut();
                }

            }
        }
        if (this.energy2 > 0) {
            if (this.rf && Constants.DE_LOADED) {
                    long transferCap = (long) Math.min(this.energy2, this.maxStorage2);
                    attemptDECoreTransfer(transferCap/*, false*/);

            }
            boolean refresh = false;
            for (TransferRFEnergy rfEnergy : this.transferRFEnergyList) {
                if (rfEnergy.getTile().isInvalid()) {
                    refresh = true;
                    continue;
                }
                extractEnergy(rfEnergy.getFacing(), rfEnergy.getSink().receiveEnergy(rfEnergy.getFacing().getOpposite(),
                        extractEnergy(rfEnergy.getFacing(), (int) this.energy2, true), false
                ), false);
            }
            if (refresh) {
                transferRFEnergyList.clear();
                for (EnumFacing facing : EnumFacing.VALUES) {
                    BlockPos pos = new BlockPos(
                            this.pos.getX() + facing.getFrontOffsetX(),
                            this.pos.getY() + facing.getFrontOffsetY(),
                            this.pos.getZ() + facing.getFrontOffsetZ()
                    );
                    TileEntity tile = this.getWorld().getTileEntity(pos);
                    if (tile == null) {
                        continue;
                    }
                    if (tile instanceof IEnergyReceiver) {
                        transferRFEnergyList.add(new TransferRFEnergy(tile, ((IEnergyReceiver) tile), facing));
                    }
                }
            }

        }
        if (!this.rf && Constants.DE_LOADED && energy2 < maxStorage2) {
            long request = (long) (maxStorage2 - energy2);
            attemptDECoreReceive(request);
        }
        if (this.isCore && this.isCoreActive && foundCore != null) {
            TileEntity te = world.getTileEntity(foundCore);
            if (te instanceof TileEnergyStorageCore) {
                TileEnergyStorageCore core = (TileEnergyStorageCore) te;
                this.coreEnergy = core.energy.value;
                this.coreCapacity = core.getExtendedCapacity();
            }
        } else {
            this.coreEnergy = 0;
            this.coreCapacity = 0;
        }



        final boolean needsInvUpdate = this.upgradeSlot.tickNoMark();
        if (needsInvUpdate) {
            setOverclockRates();
        }
        this.energy2 = Math.min(this.energy2, this.maxStorage2);

    }


    @Optional.Method(modid = "draconicevolution")
    private synchronized long attemptDECoreTransfer(long transferCap) {
        try {
            TileEntity te = foundCore == null ? null : world.getTileEntity(foundCore);
            if (foundCore == null || !(te instanceof TileEnergyStorageCore)) {
                isCore = false;
                if (world.getTotalWorldTime() % 100 == 0) {
                    foundCore = findCore(foundCore);
                }
            }

            if (foundCore != null && te instanceof TileEnergyStorageCore ) {
                if (((TileEnergyStorageCore) te).active.value){
                    TileEnergyStorageCore core = (TileEnergyStorageCore) te;
                    isCore = true;
                    isCoreActive = true;

                    long energyReceived = Math.min(core.getExtendedCapacity() - core.energy.value, transferCap);
                    ((TileEnergyStorageCore) te).energy.value += energyReceived;
                    energy2 -= energyReceived;

                    return energyReceived;
                }else{isCoreActive = false;}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Optional.Method(modid = "draconicevolution")
    private synchronized long attemptDECoreReceive(long maxReceive) {
        try {
            TileEntity te = foundCore == null ? null : world.getTileEntity(foundCore);
            if (foundCore == null || !(te instanceof TileEnergyStorageCore)) {
                isCore = false;
                if (world.getTotalWorldTime() % 100 == 0) {
                    foundCore = findCore(foundCore);
                }
            }

            if (foundCore != null && te instanceof TileEnergyStorageCore) {
                TileEnergyStorageCore core = (TileEnergyStorageCore) te;
                if (core.active.value) {
                    isCore = true;
                    isCoreActive = true;

                    long canExtract = Math.min(core.energy.value, maxReceive);
                    core.energy.value -= canExtract;
                    this.energy2 += canExtract;

                    return canExtract;
                } else {
                    isCoreActive = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }




    @Optional.Method(modid = "draconicevolution")
    private BlockPos findCore(BlockPos before) {
        List<TileEnergyStorageCore> list = new LinkedList<>();
        int range = coreSearchRange;
        Iterable<BlockPos> positions = BlockPos.getAllInBox(pos.add(-range, -range, -range), pos.add(range, range, range));

        for (BlockPos blockPos : positions) {
            if (world.getBlockState(blockPos).getBlock() == DEFeatures.energyStorageCore) {
                TileEntity tile = world.getTileEntity(blockPos);
                if (tile instanceof TileEnergyStorageCore && ((TileEnergyStorageCore) tile).active.value) {
                    list.add(((TileEnergyStorageCore) tile));
                }
            }
        }
        if (before != null) {
            list.removeIf(tile -> tile.getPos().equals(before));
        }
        Collections.shuffle(list);
        TileEnergyStorageCore first = Iterables.getFirst(list, null);
        return first == null ? null : first.getPos();
    }

    protected boolean shouldEmitEnergy() {
        return !this.rf;
    }

    public int extractEnergy(int paramInt, boolean paramBoolean) {
        int i = (int) Math.min(this.energy2, paramInt);
        if (!paramBoolean) {
            this.energy2 -= i;
            this.differenceenergy += i;
        }
        return i;
    }

    public boolean canConnectEnergy(EnumFacing arg0) {
        return true;
    }

    public int getEnergyStored(EnumFacing from) {
        return (int) this.energy2;
    }

    public int getMaxEnergyStored(EnumFacing from) {
        return (int) this.maxStorage2;
    }


    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.energy.setDirections(ModUtils.allFacings, ModUtils.allFacings);
        this.tier = nbttagcompound.getInteger("tier");

        this.energy2 = ModUtils.limit(nbttagcompound.getDouble("energy2"), 0.0D,
                this.maxStorage2
        );
        this.rf = nbttagcompound.getBoolean("rf");

    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        if (energy2 > 0) {
            nbttagcompound.setDouble("energy2", this.energy2);
        }
        nbttagcompound.setInteger("tier", this.tier);
        nbttagcompound.setBoolean("rf", this.rf);
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

    public ContainerSpectralConverter getGuiContainer(EntityPlayer player) {
        list.add(player);
        return new ContainerSpectralConverter(player, this);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiSpectralConverter(getGuiContainer(entityPlayer));
    }


    public void updateTileServer(EntityPlayer player, double event) {
        this.rf = !this.rf;

    }

    public int gaugeICEnergyScaled(int i) {
        return (int) Math.min(this.energy.getEnergy() * i / this.energy.getCapacity(), i);
    }

    public int gaugeTEEnergyScaled(int i) {
        this.maxStorage2 = this.defaultEnergyRFStorage + this.upgradeSlot.extraEnergyStorage*10 * Config.coefficientrf;
        return (int) Math.min(this.energy2 * i / this.maxStorage2, i);
    }


    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(
                UpgradableProperty.Transformer,
                UpgradableProperty.EnergyStorage
        );
    }


}