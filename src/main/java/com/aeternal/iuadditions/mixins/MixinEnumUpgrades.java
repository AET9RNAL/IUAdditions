package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.items.modules.data.EnumUpgradesPatch;
import com.denfop.api.upgrade.EnumUpgrades;
import com.denfop.items.EnumInfoUpgradeModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Mixin(value = EnumUpgrades.class, priority = 800) // ensure this runs AFTER your MixinEnumInfoUpgradeModules (e.g., priority 900)
public abstract class MixinEnumUpgrades {

    @Unique private static final Logger IUADD_LOG = LogManager.getLogger("IUAdditions");

    // === Accessors for the instance field on each EnumUpgrades constant ===
    @Accessor("list")
    public abstract List<EnumInfoUpgradeModules> iuadditions$getList();

    @Accessor("list")
    public abstract void iuadditions$setList(List<EnumInfoUpgradeModules> v);

    /**
     * Patch existing EnumUpgrades constants at the end of class initialization.
     * We replace each constant's fixed-size list (from Arrays.asList) with a mutable ArrayList,
     * then append extra modules from EnumUpgradesPatch, de-duplicated.
     */
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void iuadditions$patchExistingConstants(CallbackInfo ci) {
        try {
            int totalPatched = 0;

            for (EnumUpgradesPatch patch : EnumUpgradesPatch.values()) {
                final String targetName = patch.name();

                EnumUpgrades target;
                try {
                    target = EnumUpgrades.valueOf(targetName);
                } catch (IllegalArgumentException missing) {
                    // No such constant in the target enum; skip silently but note it.
                    LogManager.getLogger("IUAdditions")
                            .warn("[IUAdditions] EnumUpgrades has no constant '{}'; skipping patch entry.", targetName);
                    continue;
                }

                // Cast the target constant to this mixin type to access @Accessor methods.
                MixinEnumUpgrades access = (MixinEnumUpgrades) (Object) target;

                List<EnumInfoUpgradeModules> current = access.iuadditions$getList();
                if (current == null) {
                    current = new ArrayList<>();
                }

                // Ensure we have a mutable list (Arrays.asList returns fixed-size).
                List<EnumInfoUpgradeModules> mutable = (current instanceof ArrayList)
                        ? current
                        : new ArrayList<>(current);

                EnumInfoUpgradeModules[] extras = patch.getExtraModules();
                if (extras == null || extras.length == 0) {
                    IUADD_LOG.info("[IUAdditions] No extras for EnumUpgrades.{}, nothing to append.", targetName);
                    // Still set to a mutable list if it wasn't.
                    if (mutable != current) access.iuadditions$setList(mutable);
                    continue;
                }

                // De-dup and append in order.
                HashSet<EnumInfoUpgradeModules> seen = new HashSet<>(mutable);
                int added = 0;
                for (EnumInfoUpgradeModules m : extras) {
                    if (m == null) continue;
                    if (seen.add(m)) {
                        mutable.add(m);
                        added++;
                    }
                }

                // Replace with the mutable list (even if added==0, to ensure future flexibility).
                access.iuadditions$setList(mutable);

                if (added > 0) {
                    totalPatched++;
                    IUADD_LOG.info("[IUAdditions] Patched EnumUpgrades.{}: appended {} module(s): {}",
                            targetName, added, Arrays.toString(extras));
                } else {
                    IUADD_LOG.info("[IUAdditions] EnumUpgrades.{} unchanged (all extras already present).", targetName);
                }
            }

            IUADD_LOG.info("[IUAdditions] EnumUpgrades patch complete. Targets changed: {}", totalPatched);
        } catch (Throwable t) {
            IUADD_LOG.error("[IUAdditions] Failed to patch EnumUpgrades constants.", t);
        }
    }
}
