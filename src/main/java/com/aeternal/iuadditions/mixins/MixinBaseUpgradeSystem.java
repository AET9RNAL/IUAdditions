package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.hooks.BaseUpgradeSystemApplier;
import com.denfop.IUItem;
import com.denfop.api.upgrade.BaseUpgradeSystem;
import com.denfop.api.upgrade.UpgradeModificator;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = BaseUpgradeSystem.class, remap = false)
public abstract class MixinBaseUpgradeSystem {

    private static final Logger LOGGER = LogManager.getLogger("IUAdditions|BaseUpgradeSystemMixin");

    /**
     * Signature: needModificate(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z
     */
    @Inject(method = "needModificate(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z",
            at = @At("HEAD"), cancellable = true)
    private void iuadditions$capModificatorCounts(ItemStack container, ItemStack fill,
                                                  CallbackInfoReturnable<Boolean> cir) {
        // Determine which cap applies (only gate the two we control; let others follow vanilla logic)
        int limit = -1;
        String type = null;

        if (fill.getItem() == IUItem.core && fill.getMetadata() == 7) {
            limit = BaseUpgradeSystemApplier.CORE_MAX;
            type = "core";
        } else if (fill.getItem() == IUItem.neutroniumingot) {
            limit = BaseUpgradeSystemApplier.NEUTRON_MAX;
            type = "neutron";
        }

        if (limit > 0) {
            // Count already installed modificators of the same kind
            List<UpgradeModificator> applied =
                    ((BaseUpgradeSystem) (Object) this).getListModifications(container);

            int count = 0;
            for (UpgradeModificator m : applied) {
                // Prefer the game's own matcher to avoid metadata/NBT pitfalls
                if (m.matches(fill)) {
                    count++;
                }
            }

            boolean allow = count < limit;

            if (BaseUpgradeSystemApplier.DEBUG) {
                LOGGER.info("[MixinBaseUpgradeSystem] needModificate(): type={}, installed={}, limit={}, allow={}",
                        type, count, limit, allow);
            }

            cir.setReturnValue(allow);
            // We override the decision for these two types; others fall through to original logic.
        }
    }
}
