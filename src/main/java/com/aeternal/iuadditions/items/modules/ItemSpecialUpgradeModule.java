package com.aeternal.iuadditions.items.modules;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.Core;
import com.aeternal.iuadditions.Localization;
import com.aeternal.iuadditions.api.IModelRegister;
import com.aeternal.iuadditions.api.upgrade.SpecialUpgradeItemInform;
import com.aeternal.iuadditions.items.modules.data.EnumSpecialModules;
import com.aeternal.iuadditions.items.resource.IUAItemSubTypes;
import com.aeternal.iuadditions.register.ItemHandler;
import com.denfop.blocks.ISubEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;

public class ItemSpecialUpgradeModule extends IUAItemSubTypes<ItemSpecialUpgradeModule.Types> implements IModelRegister {

    protected static final String NAME = "specialmodules";

    public ItemSpecialUpgradeModule() {
        super(Types.class);
        this.setCreativeTab(Core.IUATab);
        ItemHandler.registerItem((Item) this, Core.getIdentifier(NAME)).setUnlocalizedName(NAME);
        Core.proxy.addIModelRegister(this);
    }

    public static EnumSpecialModules getType(int meta) {
        return EnumSpecialModules.getFromID(meta);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(
            final ItemStack stack,
            @Nullable final World worldIn,
            final List<String> tooltip,
            @Nonnull final ITooltipFlag flagIn
    ) {
        final SpecialUpgradeItemInform upgrade = new SpecialUpgradeItemInform(getType(stack.getItemDamage()), 1);
        tooltip.add(upgrade.getName());
        tooltip.add(Localization.translate("iua.specialupgrade_item.info") + upgrade.upgrade.max);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }


    public String getUnlocalizedName() {
        return "iua." + super.getUnlocalizedName().substring(3);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel(Item item, int meta, String extraName) {

        ModelLoader.setCustomModelResourceLocation(
                this,
                meta,
                new ModelResourceLocation(Constants.MOD_ID + ":" + NAME + "/" + Types.getFromID(meta).getName(), null)
        );
    }

    public enum Types implements ISubEnum {
        upgrademodule(0),

        ;

        private final String name;
        private final int ID;

        Types(final int ID) {
            this.name = this.name().toLowerCase(Locale.US);
            this.ID = ID;
        }

        public static Types getFromID(final int ID) {
            return values()[ID % values().length];
        }

        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.ID;
        }
    }

}
