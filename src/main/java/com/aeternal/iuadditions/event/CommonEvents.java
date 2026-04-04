package com.aeternal.iuadditions.event;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Event handlers on the FORGE event bus (game events).
 * These fire on both client and server sides.
 */
@Mod.EventBusSubscriber(modid = IUAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        IUAdditions.LOGGER.info("{} server starting", IUAdditions.MOD_ID);
    }
}
