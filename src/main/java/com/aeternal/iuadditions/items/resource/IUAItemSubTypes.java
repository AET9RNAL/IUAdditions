package com.aeternal.iuadditions.items.resource;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.api.block.ISubEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class IUAItemSubTypes<T extends Enum<T> & ISubEnum> extends ItemSubTypes<T> {

    protected IUAItemSubTypes(Class<T> typeClass) {
        super(typeClass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel(Item item, int meta, String extraName) {
        ModelLoader.setCustomModelResourceLocation(
                item,
                meta,
                new ModelResourceLocation(Constants.MOD_ID + ":items/" + extraName, (String) null)
        );
    }

    @Override
    public @NotNull String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack);
    }



}
