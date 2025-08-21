package com.aeternal.iuadditions.mixins.accessors;

import com.denfop.api.upgrade.BaseUpgradeSystem;
import com.denfop.api.upgrade.UpgradeModificator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * Access the private list of modificators so our mixin can read/append safely.
 */
@Mixin(value = BaseUpgradeSystem.class, remap = false)
public interface AccessorBaseUpgradeSystem {
    @Accessor(value = "list_modificators", remap = false)
    List<UpgradeModificator> iuadditions$getListModificators();
}
