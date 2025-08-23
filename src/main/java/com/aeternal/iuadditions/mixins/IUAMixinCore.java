package com.aeternal.iuadditions.mixins;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.ILateMixinLoader;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;
import java.util.function.BooleanSupplier;

import com.aeternal.iuadditions.Config;

/**
 * IU:Additions mixin core loader (1.12.2, Forge 2860, Java 8, MixinBooter 10.6).
 *
 * - MoreMeka-style registry: Map<mixinConfigPath, condition()>
 * - Each condition combines: (dependency present) && (Config toggle == true).
 * - Reads booleans from com.aeternal.iuadditions.Config, but ensures that
 *   Config is loaded EARLY by calling Config.loadNormalConfig(...) once,
 *   because ILateMixinLoader runs before your @Mod class typically calls it.
 *
 * You can change candidate config filenames below if your mod uses a custom name.
 */
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.Name("IUAdditionsPlugin")
@IFMLLoadingPlugin.SortingIndex(1001)
@SuppressWarnings({"unused"})
public class IUAMixinCore implements IFMLLoadingPlugin, ILateMixinLoader {

    private static final String LOG_PREFIX = "[IUAdditionsMixins] ";

    /** Preserve insertion order for stable logs. */
    private static final Map<String, BooleanSupplier> MIXIN_CONFIGS = new LinkedHashMap<>();

    /** Ensure we only attempt to load the config file once. */
    private static boolean CONFIG_ATTEMPTED = false;

    // --- Mixin config paths ---
    private static final String CFG_AUTOHUNTER     = "mixins.iuadditions.autohunter.json";
    private static final String CFG_KATANA         = "mixins.iuadditions.katana.json";
    private static final String CFG_UPGRADEMODULES = "mixins.iuadditions.upgrademodules.json";
    private static final String CFG_DE_ITEMSPECIALARMOR = "mixins.iuadditions.draconicevolution.itemspecialarmor.json";

    static {
        // Register mixin configs with (mod present) && (toggle true in Config)
        addModdedMixinCFG(CFG_AUTOHUNTER, "industrialupgrade", () -> Config.AutomaticHunterMixins);
        addModdedMixinCFG(CFG_KATANA, "industrialupgrade", () -> Config.KatanaMixins);
        addModdedMixinCFG(CFG_UPGRADEMODULES, "industrialupgrade", () -> Config.UpgradeModulesMixins);
        addModdedMixinCFG(CFG_DE_ITEMSPECIALARMOR, "draconicevolution", () -> Config.itemSpecialArmorMixins);
    }

    // ------------------------------------------------------------
    // ILateMixinLoader
    // ------------------------------------------------------------

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(MIXIN_CONFIGS.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(final String mixinConfig) {
        final BooleanSupplier cond = MIXIN_CONFIGS.get(mixinConfig);
        final boolean ok = cond != null && safeGet(cond);
        if (ok) {
            log("Queueing mixin config: " + mixinConfig);
        } else {
            log("Skipping mixin config: " + mixinConfig + " (dependency off or disabled in Config)");
        }
        return ok;
    }

    // ------------------------------------------------------------
    // Registration helpers
    // ------------------------------------------------------------

    /** 3-arg version: queues only if (mod present) && (enabledFromConfig == true). */
    private static void addModdedMixinCFG(final String mixinConfig,
                                          final String modID,
                                          final BooleanSupplier enabledFromConfig) {
        addMixinCFG(mixinConfig, () -> modLoaded(modID) && enabledByConfig(enabledFromConfig));
    }

    private static void addMixinCFG(final String mixinConfig, final BooleanSupplier conditions) {
        MIXIN_CONFIGS.put(mixinConfig, conditions);
    }

    // ------------------------------------------------------------
    // Config handling
    // ------------------------------------------------------------

    /** Returns the current value of a Config boolean, after attempting an early config load once. */
    private static boolean enabledByConfig(final BooleanSupplier supplier) {
        ensureConfigLoadedEarly();
        return safeGet(supplier);
    }

    /**
     * ILateMixinLoader runs very early. Many mods load their Forge configs later (e.g., in preInit).
     * To reflect the user's toggles here, we try to load the same config file once, using common
     * candidate file names. If none are found, we'll proceed with whatever defaults exist in
     * com.aeternal.iuadditions.Config.
     */
    private static synchronized void ensureConfigLoadedEarly() {
        if (CONFIG_ATTEMPTED) return;
        CONFIG_ATTEMPTED = true;

        try {
            final File cfgDir = Loader.instance().getConfigDir();

            // Primary candidates — change these to your actual filename if needed
            final String[] candidates = new String[]{
                    "Config.cfg",
                    "iuadditions.cfg"
            };

            File chosen = null;

            // 1) Direct candidates
            for (String name : candidates) {
                final File f = new File(cfgDir, name);
                if (f.isFile()) { chosen = f; break; }
            }

            // 2) Fallback scan (first *.cfg containing "iua" or "iuaddition")
            if (chosen == null) {
                final File[] list = cfgDir.listFiles();
                if (list != null) {
                    for (File f : list) {
                        final String n = f.getName().toLowerCase(Locale.ENGLISH);
                        if (n.endsWith(".cfg") && (n.contains("iua") || n.contains("iuaddition"))) {
                            chosen = f;
                            break;
                        }
                    }
                }
            }

            if (chosen != null) {
                Config.loadNormalConfig(chosen);
                log("Early-loaded config: " + chosen.getName());
            } else {
                log("No IUAdditions config file found yet. Using Config defaults.");
            }
        } catch (Throwable t) {
            log("Failed to early-load IUAdditions config. Using Config defaults. " + t);
        }
    }

    // ------------------------------------------------------------
    // Utils
    // ------------------------------------------------------------

    private static boolean modLoaded(final String modid) {
        try { return Loader.isModLoaded(modid); }
        catch (Throwable t) { return false; }
    }

    private static boolean safeGet(final BooleanSupplier s) {
        try { return s.getAsBoolean(); }
        catch (Throwable t) { return false; }
    }

    private static void log(final String msg) {
        System.out.println(LOG_PREFIX + msg);
    }

    // ------------------------------------------------------------
    // IFMLLoadingPlugin stubs
    // ------------------------------------------------------------
    @Nullable
    @Override
    public String[] getASMTransformerClass() { return new String[0]; }

    @Nullable
    @Override
    public String getModContainerClass() { return null; }

    @Nullable
    @Override
    public String getSetupClass() { return null; }

    @Override
    public void injectData(final Map<String, Object> data) { /* no-op */ }

    @Nullable
    @Override
    public String getAccessTransformerClass() { return null; }
}
