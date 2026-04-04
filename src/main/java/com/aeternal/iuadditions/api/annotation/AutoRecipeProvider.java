package com.aeternal.iuadditions.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as an auto-registering recipe provider.
 * Classes annotated with this must either define zero-arg public methods annotated
 * with some internal init system, or more commonly just let the framework instantiate
 * the class during setup phase to execute static blocks/initializers.
 * OR
 * A public static method can be defined to execute initialization logic if needed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoRecipeProvider {
}
