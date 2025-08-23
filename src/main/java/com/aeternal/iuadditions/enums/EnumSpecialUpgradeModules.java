package com.aeternal.iuadditions.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Data-only enum describing additional upgrade modules to append to
 * com.denfop.items.EnumInfoUpgradeModules via Mixin patching.
 *
 * Mirror the target's shape: (int max, String displayName, Integer... extra).
 *
 * Add your entries below, e.g.:
 *     SPEED_PLUS(2, "speed_plus"),
 *     GENERATOR_PLUS(1, "generator_plus", 36, 35, 28);
 */
public enum EnumSpecialUpgradeModules {
    // TODO: add your entries here
    ENERGY_SHIELD(1,"energy_shield")
    ;

    public final int max;
    public final String displayName;
    public final List<Integer> extraList;

    EnumSpecialUpgradeModules(int max, String displayName, Integer... extra) {
        this.max = max;
        this.displayName = displayName;
        List<Integer> tmp = new ArrayList<>();
        if (extra != null) {
            tmp.addAll(Arrays.asList(extra));
        }
        this.extraList = Collections.unmodifiableList(tmp);
    }

    EnumSpecialUpgradeModules(int max, String displayName) {
        this.max = max;
        this.displayName = displayName;
        this.extraList = Collections.emptyList();
    }

    public int getMax() {
        return max;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Integer> getExtraList() {
        return extraList;
    }
}
