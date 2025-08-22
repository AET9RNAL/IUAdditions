package com.aeternal.iuadditions.mixins;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.ILateMixinLoader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.Name("IUAdditionsPlugin")
@IFMLLoadingPlugin.SortingIndex(1001)
@net.minecraftforge.fml.common.Optional.Interface(iface = "zone.rong.mixinbooter.ILateMixinLoader", modid = "mixinbooter")
public class IUAMixinCore implements IFMLLoadingPlugin, ILateMixinLoader {

    private static final String MAIN_CFG = "mixins.iuadditions.json";
    private static final String IU_CFG   = "mixins.iuadditions.json";
    @Override public String[] getASMTransformerClass() { return new String[0]; }
    @Override public String getModContainerClass() { return null; }
    @Nullable @Override public String getSetupClass() { return null; }
    @Override public String getAccessTransformerClass() { return null; }

    @Override
    public void injectData(Map<String, Object> data) {
        // Fallback path when mixinbooter mod is NOT installed
        if (isModPresent("mixinbooter")) return; // MixinBooter will handle late registration

        try {
            // Ensure Mixin is bootstrapped (safe if already done by another tweaker)
            try {
                Class.forName("org.spongepowered.asm.launch.MixinBootstrap")
                        .getMethod("init").invoke(null);
            } catch (Throwable ignored) {}

            if (isModPresent("industrialupgrade")) {
                org.spongepowered.asm.mixin.Mixins.addConfiguration(IU_CFG);
            }
            System.out.println("[IUAdditions] Mixins registered via IFMLLoadingPlugin.injectData (no mixinbooter)");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // ---------- MixinBooter late path ----------
    @Override
    @net.minecraftforge.fml.common.Optional.Method(modid = "mixinbooter")
    public List<String> getMixinConfigs() {
        final ArrayList<String> list = new ArrayList<>();

        if (isModPresent("industrialupgrade")) {
            list.add(IU_CFG);
        }

        System.out.println("[IUAdditions] MixinBooter will load configs: " + list);
        return ImmutableList.copyOf(list);
    }

    private static boolean isModPresent(String modid) {
        try { return Loader.isModLoaded(modid); }
        catch (Throwable t) { return false; }
    }
}
