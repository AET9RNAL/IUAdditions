package com.aeternal.iuadditions.items.modules.data;

import com.denfop.items.EnumInfoUpgradeModules;

public final class EnumUpgradesPatch {
    // only names here; no touching IU classes in <clinit>
    private static final java.util.Map<String, String[]> NAMES = new java.util.LinkedHashMap<>();
    static {
        NAMES.put("HELMET",   new String[]{"ENERGY_SHIELD"});
        NAMES.put("BODY",     new String[]{"ENERGY_SHIELD"});
        NAMES.put("LEGGINGS", new String[]{"ENERGY_SHIELD"});
        NAMES.put("BOOTS",    new String[]{"ENERGY_SHIELD"});
    }

    public static String[] extrasFor(String targetEnumName) {
        return NAMES.getOrDefault(targetEnumName, new String[0]);
    }
}
