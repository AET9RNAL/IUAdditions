package com.aeternal.iuadditions.api.annotation;

import net.minecraft.world.level.block.Block;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoRegisterBlockEntity {
    String value();

    Class<? extends Block>[] validBlocks();
}
