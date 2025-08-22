package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.Config;
import com.denfop.items.EnumInfoUpgradeModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnumInfoUpgradeModules.class)
public abstract class MixinEnumInfoUpgradeModules {

    private static final Logger LOGGER = LogManager.getLogger("IUAdditions|Mixins");

    @Shadow @Final @Mutable public int max;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void iuadditions$afterClinit(CallbackInfo ci) {
        try {
            LOGGER.info("[EnumInfoUpgradeModules] <clinit> hook reached (mixin applied).");
        } catch (Throwable ignored) {}
    }

    /**
     * Maps each enum constant to Config.Module<NAME>maxCount.
     * If a config value looks invalid (<=0), we fall back to the original value to avoid breaking gameplay.
     */
    private static int iuadditions$getConfiguredMax(EnumInfoUpgradeModules m, int original) {
        int v;
        switch (m) {
            case GENDAY:           v = Config.ModuleGENDAYmaxCount; break;
            case GENNIGHT:         v = Config.ModuleGENNIGHTmaxCount; break;
            case PROTECTION:       v = Config.ModulePROTECTIONmaxCount; break;
            case EFFICIENCY:       v = Config.ModuleEFFICIENCYmaxCount; break;
            case BOWENERGY:        v = Config.ModuleBOWENERGYmaxCount; break;
            case SABERENERGY:      v = Config.ModuleSABERENERGYmaxCount; break;
            case DIG_DEPTH:        v = Config.ModuleDIG_DEPTHmaxCount; break;
            case SPEED:            v = Config.ModuleSPEEDmaxCount; break;
            case JUMP:             v = Config.ModuleJUMPmaxCount; break;
            case BOWDAMAGE:        v = Config.ModuleBOWDAMAGEmaxCount; break;
            case SABER_DAMAGE:     v = Config.ModuleSABER_DAMAGEmaxCount; break;
            case AOE_DIG:          v = Config.ModuleAOE_DIGmaxCount; break;
            case FLYSPEED:         v = Config.ModuleFLYSPEEDmaxCount; break;
            case STORAGE:          v = Config.ModuleSTORAGEmaxCount; break;
            case ENERGY:           v = Config.ModuleENERGYmaxCount; break;
            case VAMPIRES:         v = Config.ModuleVAMPIRESmaxCount; break;
            case RESISTANCE:       v = Config.ModuleRESISTANCEmaxCount; break;
            case LOOT:             v = Config.ModuleLOOTmaxCount; break;
            case FIRE:             v = Config.ModuleFIREmaxCount; break;
            case LUCKY:            v = Config.ModuleLUCKYmaxCount; break;
            case EFFICIENT:        v = Config.ModuleEFFICIENTmaxCount; break;
            case THORNS:           v = Config.ModuleTHORNSmaxCount; break;
            case LAPPACK_ENERGY:   v = Config.ModuleLAPPACK_ENERGYmaxCount; break;
            default:               v = original; // untouched modules
        }
        // Allow zero to "disable" only if you really want that; otherwise keep original as fallback.
        return (v > 0) ? v : original;
    }
}
