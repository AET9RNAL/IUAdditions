package com.aeternal.items;

import com.aeternal.Constants;
import com.aeternal.Core;
import com.aeternal.api.IModelRegister;
import com.aeternal.register.Register;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IUAItemBase extends Item implements IModelRegister {

    private final String name;
    private final String path;

    public IUAItemBase(String name) {
        this(name, "");
    }

    public IUAItemBase(String name, String path) {
        super();
        this.setCreativeTab(Core.IUATab);
        this.setMaxStackSize(64);

        this.name = name;
        this.path = path;
        setUnlocalizedName(name);
        Register.registerItem((Item) this, Core.getIdentifier(name)).setUnlocalizedName(name);
        Core.proxy.addIModelRegister(this);
    }

    public String getUnlocalizedName() {
        return super.getUnlocalizedName() + ".name";
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedName(stack).replace("item.", "iu.") + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(Constants.MOD_ID + ":" + path + this.name, null)
        );
    }




}
