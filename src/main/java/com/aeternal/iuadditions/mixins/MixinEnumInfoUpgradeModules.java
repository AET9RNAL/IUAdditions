package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.enums.EnumSpecialUpgradeModules;
import com.denfop.items.EnumInfoUpgradeModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(value = EnumInfoUpgradeModules.class, priority = 100)
public abstract class MixinEnumInfoUpgradeModules {

    private static final Logger LOGGER = LogManager.getLogger("IUAdditions|Mixins");

    @Shadow @Final @Mutable public int max;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void iuadditions$afterClinit(CallbackInfo ci) {
        try {
            LOGGER.info("[IUAdditionsMixins] <clinit> hook reached (mixin applied).");
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

    // === IUAdditions: enum extension support ===
    @Shadow @Final @Mutable
    private static EnumInfoUpgradeModules[] $VALUES;

    /**
     * Invoker for the (String name, int ordinal, int max, String displayName, Integer... extra) enum constructor.
     */
    @Invoker("<init>")
    private static EnumInfoUpgradeModules iuadditions$invokeInit(String enumName, int ordinal, int max, String displayName, Integer[] extra) {
        throw new AssertionError();
    }

    /**
     * Invoker for the (String name, int ordinal, int max, String displayName) enum constructor.
     */
    @Invoker("<init>")
    private static EnumInfoUpgradeModules iuadditions$invokeInitNoExtra(String enumName, int ordinal, int max, String displayName) {
        throw new AssertionError();
    }

    @Unique
    private static boolean iuadditions$patched;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void iuadditions$appendSpecials(CallbackInfo ci) {
        if (iuadditions$patched) {
            return;
        }
        iuadditions$patched = true;

        // Start from current enum constants
        List<EnumInfoUpgradeModules> values = new ArrayList<>(Arrays.asList($VALUES));

        // Append from our data enum
        for (EnumSpecialUpgradeModules spec : EnumSpecialUpgradeModules.values()) {
            String displayName = spec.getDisplayName();

            // Skip if an existing entry already uses this displayName
            boolean duplicateByName = false;
            for (EnumInfoUpgradeModules existing : values) {
                if (existing.name.equals(displayName)) {
                    duplicateByName = true;
                    break;
                }
            }
            if (duplicateByName) {
                System.out.println("[IUAdditions] EnumInfoUpgradeModules PATCH: skip special '" + spec.name()
                        + "' due to duplicate display name '" + displayName + "'.");
                continue;
            }

            String enumSymbol = /*"SPECIAL_" +*/ spec.name();
            int ordinal = values.size();

            EnumInfoUpgradeModules created;
            List<Integer> extra = spec.getExtraList();
            if (extra != null && !extra.isEmpty()) {
                created = iuadditions$invokeInit(enumSymbol, ordinal, spec.getMax(), displayName, extra.toArray(new Integer[0]));
            } else {
                created = iuadditions$invokeInitNoExtra(enumSymbol, ordinal, spec.getMax(), displayName);
            }

            values.add(created);
            System.out.println("[IUAdditions] EnumInfoUpgradeModules PATCH: added " + enumSymbol
                    + " (name='" + displayName + "', max=" + spec.getMax() + ", extra=" + extra + ")");
        }

        $VALUES = values.toArray(new EnumInfoUpgradeModules[0]);
    }
// === /enum extension support ===

}
