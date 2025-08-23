// src/main/java/com/aeternal/iuadditions/items/modules/data/EnumUpgradesPatch.java
package com.aeternal.iuadditions.items.modules.data;

import com.denfop.items.EnumInfoUpgradeModules;

/**
 * For each entry here (names MUST match the target EnumUpgrades constants),
 * provide the additional EnumInfoUpgradeModules you want to append.
 *
 * Example shows ENERGY_SHIELD being added to HELMET and BODY.
 * Replace/extend as needed.
 */
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
