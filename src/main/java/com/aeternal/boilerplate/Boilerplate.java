package com.aeternal.boilerplate;

import com.aeternal.boilerplate.integration.IntegrationManager;
import com.aeternal.boilerplate.registry.ModBlockEntities;
import com.aeternal.boilerplate.registry.ModBlocks;
import com.aeternal.boilerplate.registry.ModItems;
import com.aeternal.boilerplate.registry.ModMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Boilerplate.MOD_ID)
public class Boilerplate {

    public static final String MOD_ID = "boilerplate";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Boilerplate(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register deferred registries
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        // ModMenuTypes.MENUS.register(modEventBus); // Uncomment when needed

        // Lifecycle events
        modEventBus.addListener(this::commonSetup);

        // Client-only setup via DistExecutor
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            com.aeternal.boilerplate.client.ClientSetup.init(modEventBus);
        });

        // Register to Forge event bus for game events
        MinecraftForge.EVENT_BUS.register(this);

        // Config
        context.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.SPEC);

        // Initialize integrations
        IntegrationManager.init(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("{} common setup", MOD_ID);
        event.enqueueWork(() -> {
            // Thread-safe setup work here (e.g. recipe registration, dispenser behaviors)
        });
    }
}
