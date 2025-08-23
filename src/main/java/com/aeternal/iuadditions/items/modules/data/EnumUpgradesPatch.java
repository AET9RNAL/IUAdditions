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
public enum EnumUpgradesPatch {
    HELMET(EnumInfoUpgradeModules.ENERGY_SHIELD),
    BODY(EnumInfoUpgradeModules.ENERGY_SHIELD),
    LEGGINGS(EnumInfoUpgradeModules.ENERGY_SHIELD),
    BOOTS(EnumInfoUpgradeModules.ENERGY_SHIELD),
    ;

    private final EnumInfoUpgradeModules[] extraModules;

    EnumUpgradesPatch(EnumInfoUpgradeModules... extraModules) {
        this.extraModules = extraModules;
    }

    public EnumInfoUpgradeModules[] getExtraModules() {
        return extraModules;
    }
}
