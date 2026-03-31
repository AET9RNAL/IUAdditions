package com.aeternal.iuadditions.api.annotation.processor;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.api.annotation.IntegrationOnly;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    /**
     * Executes a method only if all required mods from @IntegrationOnly are loaded
     */
    public static void runIfModsLoaded(Object target, Method method) throws Exception {
        IntegrationOnly annotation = method.getAnnotation(IntegrationOnly.class);
        if (annotation != null) {
            for (String modId : annotation.value()) {
                if (!isModLoaded(modId)) {
                    return;
                }
            }
        }
        method.invoke(target);
    }

    /**
     * Check if mod is loaded (maps friendly names to Constants fields)
     */
    static boolean isModLoaded(String modName) {
        switch (modName.toLowerCase()) {
            case "astralsorcery": return Constants.AS_LOADED && Constants.AS_CONFIRM;
            case "divinerpg": return Constants.DIV_LOADED && Constants.DIV_CONFIRM;
            case "forestry": return Constants.FO_LOADED && Constants.FO_CONFIRM;
            case "draconicevolution": return Constants.DE_LOADED && Constants.DE_CONFIRM;
            case "botania": return Constants.BA_LOADED && Constants.BA_CONFIRM;
            case "plustic": return Constants.PU_LOADED;
            default: return false;
        }
    }
}
