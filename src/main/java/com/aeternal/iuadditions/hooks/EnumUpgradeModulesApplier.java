package com.aeternal.iuadditions.hooks;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.mixins.accessors.AccessorEnumInfoUpgradeModules;
import com.denfop.items.EnumInfoUpgradeModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Normal (non-mixin) helper you can call anytime after Config is loaded.
 */
public final class EnumUpgradeModulesApplier {
    private static final Logger LOGGER = LogManager.getLogger("IUAdditions|Mixins");

    private EnumUpgradeModulesApplier() {}

    public static void applyNowIfConfigured() {
        if (!Config.UpgradeModulesMixin) {
            if (Config.DebugEnum) {
                LOGGER.info("[EnumInfoUpgradeModules] Global switch OFF — leaving vanilla values.");
            }
            return;
        }

        for (EnumInfoUpgradeModules m : EnumInfoUpgradeModules.values()) {
            int original = m.max; // field is public in target; already de-finalized by mixin
            int configured = getConfiguredMax(m, original);

            if (configured != original) {
                ((AccessorEnumInfoUpgradeModules) (Object) m).setMax(configured);
                if (Config.DebugEnum) {
                    LOGGER.info("[EnumInfoUpgradeModules] {}: max {} -> {}", m.name(), original, configured);
                }
            } else if (Config.DebugEnum) {
                LOGGER.info("[EnumInfoUpgradeModules] {}: max unchanged {}", m.name(), original);
            }
        }
    }

    private static int getConfiguredMax(EnumInfoUpgradeModules m, int original) {
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
            default:               v = original;
        }
        // Allow 0 if you want to disable a module; if not, use (v > 0 ? v : original)
        return (v >= 0) ? v : original;
    }
}
