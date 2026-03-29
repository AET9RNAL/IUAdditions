package com.aeternal.boilerplate.event;

import com.aeternal.boilerplate.Boilerplate;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Event handlers on the FORGE event bus (game events).
 * These fire on both client and server sides.
 */
@Mod.EventBusSubscriber(modid = Boilerplate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        Boilerplate.LOGGER.info("{} server starting", Boilerplate.MOD_ID);
    }
}
