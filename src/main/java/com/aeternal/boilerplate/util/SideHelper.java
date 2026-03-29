package com.aeternal.boilerplate.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Primary mechanisms in 1.19.2:
 * - DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ...) for conditional execution
 * - @Mod.EventBusSubscriber(value = Dist.CLIENT) for client-only event handlers
 * - FMLEnvironment.dist for compile-time environment checks
 */
public final class SideHelper {

    /**
     * Run a callable on the client side only, returning a result.
     * The supplier lambda must not capture any client-only types in its closure.
     */
    public static <T> T runOnClient(Supplier<Callable<T>> clientWork) {
        return DistExecutor.unsafeCallWhenOn(Dist.CLIENT, clientWork);
    }

    /**
     * Run a runnable on the client side only.
     */
    public static void executeOnClient(Supplier<Runnable> clientWork) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, clientWork);
    }

    /**
     * Check if we are on the physical client.
     */
    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    /**
     * Check if we are on the physical server.
     */
    public static boolean isServer() {
        return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
    }

    private SideHelper() {}
}
