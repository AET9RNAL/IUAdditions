package com.aeternal.items.resource;

import com.aeternal.Constants;
import com.aeternal.api.IModelRegister;
import com.aeternal.items.block.ISubItem;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.*;

public class ItemSubTypes<T extends Enum<T> & IStringSerializable> extends Item implements ISubItem<T>, IModelRegister {
    protected final PropertyEnum<T> typeProperty;

    private ItemSubTypes(PropertyEnum<T> typeProperty) {
        super();
        this.typeProperty = typeProperty;
        this.setHasSubtypes(true);
    }

    protected ItemSubTypes(Class<T> typeClass) {

        this(PropertyEnum.create("type", typeClass, Arrays.asList(typeClass.getEnumConstants())));

    }


    @SideOnly(Side.CLIENT)
    public void registerModels() {
        final List<T> list = new ArrayList<>(this.typeProperty.getAllowedValues());
        for (int i = 0; i < this.typeProperty.getAllowedValues().size(); i++) {
            this.registerModel(this, i, list.get(i).getName());
        }


    }

    @SideOnly(Side.CLIENT)
    public void registerModel(Item item, int meta, String extraName) {
        ModelLoader.setCustomModelResourceLocation(
                item,
                meta,
                new ModelResourceLocation(Constants.MOD_ID + ":" + "items" + "/" + extraName, null)
        );
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedName(stack));
    }

    public String getUnlocalizedName(ItemStack stack) {
        T type = this.getType(stack);
        return type == null
                ? super.getUnlocalizedName(stack).replace("item", "iua").replace(".name", "")
                : super.getUnlocalizedName(stack).replace("item", "iua") + "." + type.getName();
    }

    public ItemStack getItemStack(T type) {
        return this.getItemStackUnchecked(type);
    }

    protected ItemStack getItemStackUnchecked(T type) {
        return new ItemStack(this, 1, new ArrayList<>(this.typeProperty.getAllowedValues()).indexOf(type));
    }


    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {

            for (final T type : this.typeProperty.getAllowedValues()) {
                subItems.add(this.getItemStackUnchecked(type));
            }

        }
    }

    public Set<T> getAllTypes() {
        return EnumSet.allOf(this.typeProperty.getValueClass());
    }

    public final T getType(ItemStack stack) {
        return new ArrayList<>(this.typeProperty.getAllowedValues()).get(stack.getMetadata());
    }
}
