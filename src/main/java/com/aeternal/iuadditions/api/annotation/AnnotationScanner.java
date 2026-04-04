package com.aeternal.iuadditions.api.annotation;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraftforge.fml.ModList;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Runtime annotation scanner using Forge's ModFileScanData.
 *
 * Forge already scans all mod classes during loading and provides
 * results via ModFileScanData. No custom classpath scanning needed.
 *
 * Usage during FMLCommonSetupEvent:
 *   List<Class<?>> classes = AnnotationScanner.findAnnotatedClasses(AutoRegister.class);
 */
public final class AnnotationScanner {

    /**
     * Find all classes in loaded mods annotated with the given annotation.
     */
    public static List<Class<?>> findAnnotatedClasses(Class<? extends Annotation> annotationClass) {
        Type annotationType = Type.getType(annotationClass);

        return ModList.get().getAllScanData().stream()
                .flatMap(scanData -> scanData.getAnnotations().stream())
                .filter(a -> annotationType.equals(a.annotationType()))
                .map(a -> {
                    try {
                        return Class.forName(a.memberName());
                    } catch (ClassNotFoundException e) {
                        IUAdditions.LOGGER.error("Failed to load annotated class: {}", a.memberName(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private AnnotationScanner() {}
}
