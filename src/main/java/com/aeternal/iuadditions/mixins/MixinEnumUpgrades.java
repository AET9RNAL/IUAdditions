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

import java.util.List;

@Mixin(value = EnumUpgrades.class, priority = 998) // ensure this runs AFTER your MixinEnumInfoUpgradeModules (e.g., priority 900)
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
            // Force-load the enum we extend so its mixin has time to apply.
            try {
                Class.forName("com.denfop.items.EnumInfoUpgradeModules", true,
                        Thread.currentThread().getContextClassLoader());
            } catch (Throwable ignored) {}

            int totalPatched = 0;

            // Iterate the actual EnumUpgrades constants that exist at runtime.
            for (EnumUpgrades target : EnumUpgrades.values()) {
                final String targetName = target.name();

                // Pull *names* (String[]) lazily from your helper. No hard links here.
                final String[] extraNames = EnumUpgradesPatch.extrasFor(targetName);
                if (extraNames == null || extraNames.length == 0) {
                    IUADD_LOG.info("[IUAdditions] No extras for EnumUpgrades.{}, nothing to append.", targetName);
                    continue;
                }

                // Access the mutable list on the target constant.
                MixinEnumUpgrades access = (MixinEnumUpgrades) (Object) target;
                java.util.List<EnumInfoUpgradeModules> current = access.iuadditions$getList();
                if (current == null) current = new java.util.ArrayList<>();
                final java.util.List<EnumInfoUpgradeModules> mutable =
                        (current instanceof java.util.ArrayList) ? current : new java.util.ArrayList<>(current);

                // Resolve names -> enum values at patch time (after enum has been extended).
                final java.util.List<EnumInfoUpgradeModules> toAppend = new java.util.ArrayList<>();
                for (String name : extraNames) {
                    if (name == null || name.isEmpty()) continue;
                    try {
                        EnumInfoUpgradeModules m = EnumInfoUpgradeModules.valueOf(name);
                        if (!mutable.contains(m)) toAppend.add(m);
                    } catch (IllegalArgumentException notPresent) {
                        IUADD_LOG.warn("[IUAdditions] Extra module '{}' not found on EnumInfoUpgradeModules; skipping.", name);
                    }
                }

                if (!toAppend.isEmpty()) {
                    mutable.addAll(toAppend);
                    access.iuadditions$setList(mutable);
                    totalPatched++;
                    IUADD_LOG.info("[IUAdditions] Patched EnumUpgrades.{}: appended {} module(s): {}",
                            targetName, toAppend.size(), java.util.Arrays.toString(extraNames));
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
