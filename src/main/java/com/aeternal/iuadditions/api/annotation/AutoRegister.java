package com.aeternal.iuadditions.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for automatic registration patterns.
 *
 * Can be processed at runtime via AnnotationScanner (using Forge's
 * ModFileScanData)
 * or at compile-time via AutoRegisterProcessor (javax.annotation.processing).
 *
 * DeferredRegister + RegistryObject already eliminates most boilerplate,
 * but this is useful for custom patterns like auto-discovering integration
 * modules
 * or auto-registering handler classes.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface AutoRegister {
    /** The registry name for this element. */
    String value() default "";
}
