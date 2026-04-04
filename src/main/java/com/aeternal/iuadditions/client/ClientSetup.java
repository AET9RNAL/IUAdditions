package com.aeternal.iuadditions.client;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-side setup and event handling (replaces ClientProxy from 1.12.2).
 *
 * Two mechanisms:
 * 1. init(modEventBus) called via DistExecutor from main mod class
 * 2. @EventBusSubscriber(value = Dist.CLIENT) for auto-discovered static handlers
 *
 * This class is safe to reference from common code ONLY through DistExecutor
 * or @EventBusSubscriber. Never import directly from common code.
 */
@Mod.EventBusSubscriber(modid = IUAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    /**
     * Called from main mod constructor via DistExecutor.
     * Register any client-only event listeners here.
     */
    public static void init(IEventBus modEventBus) {
        // Register client-only event listeners
        // modEventBus.addListener(ClientSetup::onRegisterRenderers);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        IUAdditions.LOGGER.info("{} client setup", IUAdditions.MOD_ID);

        event.enqueueWork(() -> {
            // Thread-safe client setup work:
            // MenuScreens.register(ModMenuTypes.EXAMPLE_MENU.get(), ExampleScreen::new);
            // ItemBlockRenderTypes.setRenderLayer(ModBlocks.EXAMPLE_BLOCK.get(), RenderType.cutout());
        });
    }

    // === Example: Register block entity renderers ===
    // @SubscribeEvent
    // public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
    //     event.registerBlockEntityRenderer(ModBlockEntities.EXAMPLE_BE.get(), ExampleBERenderer::new);
    // }
}
