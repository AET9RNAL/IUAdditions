package com.aeternal.iuadditions.mixins;

import com.denfop.Localization;
import com.denfop.items.EnumInfoUpgradeModules;
import com.denfop.api.upgrade.UpgradeItemInform;
import com.aeternal.iuadditions.items.modules.data.ExtraInfoCases;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Appends data-driven cases to UpgradeItemInform#getName() from the end of the original switch.
 * - Leaves all original cases intact.
 * - If vanilla switch would return "", we inject a value from ExtraInfoCases.
 *
 * Env: MC 1.12.2, Forge 14.23.5.2860, Java 8, MixinBooter 10.6
 */
@Mixin(value = UpgradeItemInform.class, remap = false, priority = 997)
public abstract class MixinUpgradeItemInform {

    @Shadow @Final public EnumInfoUpgradeModules upgrade;
    @Shadow @Final public int number;

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void iuadditions$appendExtraCases(CallbackInfoReturnable<String> cir) {
        String ret = cir.getReturnValue();
        if (ret != null && !ret.isEmpty()) {
            // Original switch returned a value; do not touch.
            return;
        }

        // Fallback path: try to construct from our data enum
        if (this.upgrade == null) {
            return;
        }

        ExtraInfoCases extra = ExtraInfoCases.byCase(this.upgrade.name());
        if (extra == null) {
            return; // no mapping for this case name
        }

        TextFormatting color = extra.getColor();
        String base = Localization.translate(extra.getLocKey());
        String suffix = extra.renderSuffix(this.number); // may be empty

        cir.setReturnValue((color != null ? color.toString() : "") + base + suffix);
    }
}
