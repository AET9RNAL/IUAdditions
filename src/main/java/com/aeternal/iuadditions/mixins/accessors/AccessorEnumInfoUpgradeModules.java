package com.aeternal.iuadditions.mixins.accessors;

import com.denfop.items.EnumInfoUpgradeModules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accessor interface to set the (now mutable) 'max' field.
 */
@Mixin(EnumInfoUpgradeModules.class)
public interface AccessorEnumInfoUpgradeModules {
    @Accessor("max")
    void setMax(int value);
}
