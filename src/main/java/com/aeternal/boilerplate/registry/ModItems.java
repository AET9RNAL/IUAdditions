package com.aeternal.boilerplate.registry;

import com.aeternal.boilerplate.Boilerplate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * In 1.19.2, items don't self-register in their constructor.
 * Instead, declare RegistryObject<Item> fields here.
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Boilerplate.MOD_ID);

    // === Example item ===
    // public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
    //         () -> new Item(new Item.Properties()));

    // === Example BlockItem ===
    // public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
    //         () -> new BlockItem(ModBlocks.EXAMPLE_BLOCK.get(), new Item.Properties()));

    /**
     * Helper for bulk BlockItem registration.
     * Replaces the old IUAItemBase pattern where items registered themselves.
     */
    private static RegistryObject<Item> registerBlockItem(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
