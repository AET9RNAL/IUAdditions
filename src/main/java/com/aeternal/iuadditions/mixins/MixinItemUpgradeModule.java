package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.items.modules.data.UpgradeModuleExtra;
import com.denfop.items.modules.ItemUpgradeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.denfop.items.modules.ItemUpgradeModule$Types", remap = false, priority = 996)
public abstract class MixinItemUpgradeModule {

    private static final Logger LOG = LogManager.getLogger("IUAdditions");

    // Synthetic enum array holding all constants
    @Shadow @Final @Mutable
    private static ItemUpgradeModule.Types[] $VALUES;

    // Invoker to call the private enum constructor: (String name, int ordinal, int ID)
    @Invoker("<init>")
    private static ItemUpgradeModule.Types iuadditions$new(String name, int ordinal, int id) {
        throw new AssertionError();
    }

    /**
     * Append our extras AFTER the base enum has finished initializing.
     * Preserves original ordinals/IDs and lets getFromID() naturally see the new entries.
     */
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void iuadditions$appendExtras(CallbackInfo ci) {
        final UpgradeModuleExtra[] extras = UpgradeModuleExtra.values();
        if (extras.length == 0) {
            LOG.debug("[IUAdditions] No extra upgrade modules declared; skipping injection.");
            return;
        }

        final ItemUpgradeModule.Types[] base = $VALUES;
        final int baseLen = base.length;

        final ItemUpgradeModule.Types[] merged = new ItemUpgradeModule.Types[baseLen + extras.length];
        System.arraycopy(base, 0, merged, 0, baseLen);

        int i = 0;
        for (UpgradeModuleExtra extra : extras) {
            final String enumName = extra.name(); // e.g., "upgrademodule47"
            final int id = extra.id();

            final ItemUpgradeModule.Types created =
                    iuadditions$new(enumName, baseLen + i, id);

            merged[baseLen + i] = created;

            LOG.info("[IUAdditions] Injected ItemUpgradeModule.Types: name='{}', id={}, ordinal={}",
                    enumName, id, baseLen + i);
            i++;
        }

        $VALUES = merged;
        LOG.info("[IUAdditions] Types extended: base={}, added={}, total={}", baseLen, extras.length, merged.length);
    }
}
