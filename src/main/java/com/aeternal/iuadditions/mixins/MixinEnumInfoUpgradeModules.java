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

/**
 * Mixin target: com.denfop.items.EnumInfoUpgradeModules
 *
 * Strategy:
 * 1) Hook <clinit> at RETURN → apply configured max values once ALL enum constants exist.
 * 2) Provide applyNowIfConfigured() so we can re-apply after Config is loaded (recommended).
 */
@Mixin(EnumInfoUpgradeModules.class)
public abstract class MixinEnumInfoUpgradeModules {

    private static final Logger LOGGER = LogManager.getLogger("IUAdditions|Mixins");

    // We need to be able to write this after instances are created.
    @Shadow @Final @Mutable public int max;

    /**
     * Runs once after enum static init finishes creating all constants.
     * This is EARLY; your Config might not be loaded yet.
     * Therefore we both (a) do a best-effort here, and (b) expose applyNowIfConfigured() for a late pass.
     */
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void iuadditions$afterClinit(CallbackInfo ci) {
        // Always emit a single proof-of-life message so you can see that the mixin actually ran.
        // (Not gated by Config so you can verify in latest.log even before Config loads.)
        try {
            LogManager.getLogger("IUAdditions|Mixins")
                    .info("[EnumInfoUpgradeModules] <clinit> hook reached (early pass).");
        } catch (Throwable ignored) {}

        // Early pass: only applies if the switch is already true and config fields are non-default.
        applyNowIfConfigured();
    }

    /**
     * Call this from your mod AFTER your Config is loaded (e.g., end of preInit/init).
     * Safe to call multiple times.
     */
    private static void applyNowIfConfigured() {
        if (!Config.UpgradeModulesMixin) {
            if (Config.DebugEnum) {
                LOGGER.info("[EnumInfoUpgradeModules] Global switch OFF — leaving vanilla max values.");
            }
            return;
        }

        for (EnumInfoUpgradeModules m : EnumInfoUpgradeModules.values()) {
            // Read the current (possibly vanilla) value
            int original = ((MixinEnumInfoUpgradeModules)(Object)m).max;

            // Compute configured value with sensible fallback
            int configured = iuadditions$getConfiguredMax(m, original);

            if (configured != original) {
                ((MixinEnumInfoUpgradeModules)(Object)m).max = configured;
                if (Config.DebugEnum) {
                    LOGGER.info("[EnumInfoUpgradeModules] {}: max {} -> {}", m.name(), original, configured);
                }
            } else if (Config.DebugEnum) {
                LOGGER.info("[EnumInfoUpgradeModules] {}: max unchanged {}", m.name(), original);
            }
        }
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
