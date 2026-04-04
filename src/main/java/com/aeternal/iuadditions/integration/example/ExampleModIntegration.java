package com.aeternal.iuadditions.integration.example;

import com.aeternal.iuadditions.IUAdditions;
import com.aeternal.iuadditions.integration.IIntegrationModule;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Each integration can have its own DeferredRegisters for
 * items/blocks specific to that integration.
 */
public class ExampleModIntegration implements IIntegrationModule {

    // Integration-specific registries (only created when integration is active)
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IUAdditions.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IUAdditions.MOD_ID);

    // === Integration-specific registered objects ===
    // public static final RegistryObject<Item> INTEGRATION_ITEM = ITEMS.register("integration_item",
    //         () -> new Item(new Item.Properties()));

    @Override
    public String getTargetModId() {
        return "target_mod_id"; // e.g., "forestry", "botania", etc.
    }

    @Override
    public boolean isConfigEnabled() {
        // return ModConfig.forestryEnabled;
        return true;
    }

    @Override
    public void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }

    @Override
    public void commonSetup() {
        // Register recipes, process tags, etc.
    }

    @Override
    public void clientSetup() {
        // Register renderers, screens, etc.
    }
}
