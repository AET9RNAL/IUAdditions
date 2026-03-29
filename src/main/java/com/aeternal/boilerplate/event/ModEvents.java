package com.aeternal.boilerplate.event;

import com.aeternal.boilerplate.Boilerplate;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

/**
 * Event handlers on the MOD event bus (lifecycle events).
 * Use for InterModComms and other mod lifecycle hooks.
 */
@Mod.EventBusSubscriber(modid = Boilerplate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void onInterModEnqueue(InterModEnqueueEvent event) {
        // Send InterModComms messages
    }

    @SubscribeEvent
    public static void onInterModProcess(InterModProcessEvent event) {
        // Process received InterModComms messages
    }
}
