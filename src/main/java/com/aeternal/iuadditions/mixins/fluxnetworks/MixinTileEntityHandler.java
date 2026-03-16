package com.aeternal.iuadditions.mixins.fluxnetworks;

import com.aeternal.iuadditions.integration.fluxnetworks.IUEFEnergyHandler;
import com.aeternal.iuadditions.integration.fluxnetworks.IUQEEnergyHandler;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sonar.fluxnetworks.common.handler.TileEntityHandler;

@Mixin(value = TileEntityHandler.class, remap = false)
public class MixinTileEntityHandler {

    @Inject(method = "registerEnergyHandler", at = @At("RETURN"), require = 0)
    private static void iua$registerIUEFHandler(CallbackInfo ci) {
        if (Loader.isModLoaded("industrialupgrade")) {
            TileEntityHandler.tileEnergyHandlers.add(IUEFEnergyHandler.INSTANCE);
            TileEntityHandler.tileEnergyHandlers.add(IUQEEnergyHandler.INSTANCE);
        }
    }
}
