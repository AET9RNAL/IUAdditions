//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.items;

import com.aeternal.Constants;
import com.aeternal.Core;
import com.aeternal.api.IModelRegister;
import com.aeternal.register.ItemHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

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
        ItemHandler.registerItem((Item) this, Core.getIdentifier(name)).setUnlocalizedName(name);
        Core.proxy.addIModelRegister(this);
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedName(stack).replace("item.", "iua.") + ".name");
    }

    public String getUnlocalizedName() {
        return super.getUnlocalizedName() + ".name";
    }

    @Override
    public boolean hasEffect(@Nonnull final ItemStack stack) {
        return false;
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(Constants.MOD_ID + ":" + path + this.name, null)
        );
    }

}
