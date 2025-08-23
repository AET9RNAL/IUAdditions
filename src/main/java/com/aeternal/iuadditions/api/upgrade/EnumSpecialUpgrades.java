package com.aeternal.iuadditions.api.upgrade;



import com.aeternal.iuadditions.items.modules.data.EnumSpecialModules;

import java.util.Arrays;
import java.util.List;

public enum EnumSpecialUpgrades {
    SOLAR_HELMET(
            EnumSpecialModules.ENERGY_SHIELD
    ),
    HELMET(
            EnumSpecialModules.ENERGY_SHIELD
    ),
    BODY(
            EnumSpecialModules.ENERGY_SHIELD
    ),
    LEGGINGS(
            EnumSpecialModules.ENERGY_SHIELD
    ),
    BOOTS(
            EnumSpecialModules.ENERGY_SHIELD
    ),

    ;
    public List<EnumSpecialModules> list;

    EnumSpecialUpgrades(EnumSpecialModules... updates) {
        this.list = Arrays.asList(updates);
    }
}
