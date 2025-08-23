package com.aeternal.iuadditions.items.modules.data;

import com.aeternal.iuadditions.IUAItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumSpecialModules {
    ENERGY_SHIELD(8, "energy_shield"), // 0

    ;
    public final int max;
    public final String name;
    public final List<Integer> list;

    EnumSpecialModules(int max, String name, Integer... enumSpecislModules) {
        this.max = max;
        this.name = name;
        this.list = Arrays.asList(enumSpecislModules);
        IUAItem.list.add(name);
    }

    EnumSpecialModules(int max, String name) {
        this.max = max;
        this.name = name;
        this.list = new ArrayList<>();
        IUAItem.list.add(name);
    }

    public static EnumSpecialModules getFromID(final int ID) {
        return values()[ID % values().length];
    }

}
