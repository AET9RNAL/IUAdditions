package com.aeternal.iuadditions.integration;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

public class IntegrationManager {

    private static final List<IIntegrationModule> activeModules = new ArrayList<>();

    public static void init(IEventBus modEventBus) {
        List<IIntegrationModule> allModules = registerModules();

        for (IIntegrationModule module : allModules) {
            if (ModList.get().isLoaded(module.getTargetModId()) && module.isConfigEnabled()) {
                IUAdditions.LOGGER.info("Enabling integration: {}", module.getTargetModId());
                module.init(modEventBus);
                activeModules.add(module);
            } else {
                IUAdditions.LOGGER.debug("Skipping integration: {} (loaded={}, configEnabled={})",
                        module.getTargetModId(),
                        ModList.get().isLoaded(module.getTargetModId()),
                        module.isConfigEnabled());
            }
        }

        // Register lifecycle dispatchers
        modEventBus.addListener(IntegrationManager::onCommonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(IntegrationManager::onClientSetup);
        });
    }

    /**
     * Register all integration modules here.
     * Add new IIntegrationModule instances for each supported mod.
     */
    private static List<IIntegrationModule> registerModules() {
        List<IIntegrationModule> modules = new ArrayList<>();

        // === Add integration modules here ===
        // modules.add(new ForestryIntegration());
        // modules.add(new BotaniaIntegration());

        return modules;
    }

    private static void onCommonSetup(FMLCommonSetupEvent event) {
        for (IIntegrationModule module : activeModules) {
            module.commonSetup();
        }
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        for (IIntegrationModule module : activeModules) {
            module.clientSetup();
        }
    }

    /** Check if a specific integration is active at runtime. */
    public static boolean isIntegrationActive(String targetModId) {
        return activeModules.stream().anyMatch(m -> m.getTargetModId().equals(targetModId));
    }
}
