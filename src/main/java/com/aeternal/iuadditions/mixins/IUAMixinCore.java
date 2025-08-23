package com.aeternal.iuadditions.mixins;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.ILateMixinLoader;

import com.aeternal.iuadditions.Config;

import java.io.File;
import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * Loads IU:Additions mixin configs based on early-loaded config toggles and present mods.
 * Also exposes which mixin configs actually got queued so runtime appliers can guard themselves.
 *
 * MC: 1.12.2
 * Forge: 14.23.5.2860
 * Java: 8
 * MixinBooter: 10.6
 */
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.Name("IUAdditionsMixinCore")
@Optional.Interface(iface = "zone.rong.mixinbooter.ILateMixinLoader", modid = "mixinbooter")
public class IUAMixinCore implements IFMLLoadingPlugin, ILateMixinLoader {

    // === Adjust these to your actual mixin JSON filenames ===
    public static final String CFG_AUTOHUNTER      = "mixins.iuadditions.autohunter.json";
    public static final String CFG_KATANA          = "mixins.iuadditions.katana.json";
    public static final String CFG_UPGRADEMODULES  = "mixins.iuadditions.upgrademodules.json";
    public static final String CFG_DE_SPEC_ARMOR   = "mixins.iuadditions.draconicevolution.itemspecialarmor.json";

    // Map of mixin-config -> condition supplier (mod present + toggle)
    private static final Map<String, BooleanSupplier> MIXIN_CONFIGS = new LinkedHashMap<>();
    // Which configs actually queued (decision made in shouldMixinConfigQueue)
    private static final Map<String, Boolean> QUEUED = new HashMap<>();
    // Ensure we only try to load the config once
    private static boolean CONFIG_ATTEMPTED = false;

    static {
        addModdedMixinCFG(CFG_AUTOHUNTER,     "industrialupgrade", () -> Config.AutomaticHunterMixins);
        addModdedMixinCFG(CFG_KATANA,         "industrialupgrade", () -> Config.KatanaMixins);
        addModdedMixinCFG(CFG_UPGRADEMODULES, "industrialupgrade", () -> Config.UpgradeModulesMixins);
        addModdedMixinCFG(CFG_DE_SPEC_ARMOR,  "draconicevolution",  () -> Config.itemSpecialArmorMixins);
    }

    // --- ILateMixinLoader ---

    @Override
    public List<String> getMixinConfigs() {
        // Return all; the per-config decision happens in shouldMixinConfigQueue
        return new ArrayList<>(MIXIN_CONFIGS.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(final String mixinConfig) {
        final BooleanSupplier cond = MIXIN_CONFIGS.get(mixinConfig);
        final boolean ok = cond != null && enabledByConfig(cond);
        QUEUED.put(mixinConfig, ok);
        log((ok ? "Queueing" : "Skipping") + " mixin config: " + mixinConfig);
        return ok;
    }

    /**
     * Runtime helpers: your appliers can call this to see if the corresponding mixin JSON
     * was actually queued (e.g., guard casts to accessor interfaces).
     */
    public static boolean wasQueued(String cfg) {
        final Boolean b = QUEUED.get(cfg);
        return b != null && b;
    }

    // --- Internal gating helpers ---

    private static void addModdedMixinCFG(String config, String modid, BooleanSupplier enabled) {
        MIXIN_CONFIGS.put(config, () -> Loader.isModLoaded(modid) && enabledByConfig(enabled));
    }

    private static boolean enabledByConfig(BooleanSupplier s) {
        ensureConfigLoadedEarly();
        try {
            return s.getAsBoolean();
        } catch (Throwable t) {
            log("Error evaluating config toggle: " + t);
            return false;
        }
    }

    /**
     * Create & load the config BEFORE MixinBooter decides which mixins to queue.
     * This prevents first-run races where the file doesn't exist yet.
     *
     * Make sure Config.loadNormalConfig(file) creates/saves defaults if the file is missing.
     */
    private static synchronized void ensureConfigLoadedEarly() {
        if (CONFIG_ATTEMPTED) return;
        CONFIG_ATTEMPTED = true;
        try {
            // Adjust filename if your mod uses a different one.
            File cfg = new File(Loader.instance().getConfigDir(), "iuadditions.cfg");
            Config.loadNormalConfig(cfg); // must save if missing
            log("Early-loaded config: " + cfg.getAbsolutePath());
        } catch (Throwable t) {
            log("Failed to early-load config; using built-in defaults. " + t);
        }
    }

    private static void log(String msg) {
        System.out.println("[IUAdditionsMixins] " + msg);
    }

    // --- IFMLLoadingPlugin boilerplate ---

    public IUAMixinCore() {}

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // no-op
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
