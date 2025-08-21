package com.aeternal.iuadditions.mixins.accessors;

import com.denfop.items.energy.ItemKatana;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** Accessor to set base fields after config loads. */
@Mixin(value = ItemKatana.class, remap = false)
public interface AccessorItemKatana {
    @Accessor("damage1") void iuadditions$setDamage1(int value);
    //@Accessor("efficiency") void iuadditions$setEfficiency(float value);
}
