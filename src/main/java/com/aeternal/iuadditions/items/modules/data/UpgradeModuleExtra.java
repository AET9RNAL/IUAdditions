package com.aeternal.iuadditions.items.modules.data;

/**
 * Declare additional upgrade module entries for IUAdditions.
 * Naming must mirror the target enum's convention. Example: upgrademodule47(47).
 */
public enum UpgradeModuleExtra {
    upgrademodule47(47),
    ;

    private final int id;

    UpgradeModuleExtra(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
