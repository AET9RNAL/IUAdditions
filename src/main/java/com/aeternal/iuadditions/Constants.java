package com.aeternal.iuadditions;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraftforge.fml.ModList;

/**
 * In 1.19.2, Loader.isModLoaded() is replaced by ModList.get().isLoaded().
 * These are evaluated lazily because ModList may not be populated at class init time.
 */
public final class Constants {

    public static final String MOD_ID = IUAdditions.MOD_ID;

    // === Mod-loaded flags ===
    // Add cached flags per integration target. Example:
    //
    // private static Boolean cachedForestryLoaded = null;
    // public static boolean isForestryLoaded() {
    //     if (cachedForestryLoaded == null) {
    //         cachedForestryLoaded = ModList.get().isLoaded("forestry");
    //     }
    //     return cachedForestryLoaded;
    // }

    /**
     * Check if a mod is loaded. Use this for one-off checks.
     * For hot-path checks, add a cached field above.
     */
    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    // === Config confirmation flags ===
    // Populated after config loads. Mirror the old pattern:
    //   Constants.DE_CONFIRM = Config.DraconicConfirmed
    //
    // public static boolean FORESTRY_CONFIRM = false;
    // public static boolean BOTANIA_CONFIRM = false;

    private Constants() {}
}
