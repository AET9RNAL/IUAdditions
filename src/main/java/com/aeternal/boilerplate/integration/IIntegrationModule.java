package com.aeternal.boilerplate.integration;

import net.minecraftforge.eventbus.api.IEventBus;

/**
 * Interface for mod integration modules.
 *
 * Replaces the old static init() pattern in XxxIntegration classes.
 * Each integration module is conditionally loaded based on:
 *   1. Target mod being present (ModList.get().isLoaded())
 *   2. Config toggle confirming the integration is enabled
 *
 * Lifecycle matches Forge mod lifecycle:
 *   init()        -> Called during mod construction (register DeferredRegisters, listeners)
 *   commonSetup() -> Called during FMLCommonSetupEvent
 *   clientSetup() -> Called during FMLClientSetupEvent (only on physical client)
 */
public interface IIntegrationModule {

    /** The mod ID of the target mod this integration is for. */
    String getTargetModId();

    /** Whether this integration is enabled in config. Return true if no toggle needed. */
    default boolean isConfigEnabled() {
        return true;
    }

    /** Called during mod construction. Register DeferredRegisters to the bus, add listeners. */
    void init(IEventBus modEventBus);

    /** Called during FMLCommonSetupEvent. */
    default void commonSetup() {}

    /** Called during FMLClientSetupEvent (client side only). */
    default void clientSetup() {}
}
