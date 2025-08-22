package com.aeternal.iuadditions.coremod;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.Name("IUAdditionsCoremod")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.TransformerExclusions({
        "com.aeternal.iuadditions.coremod"
})
public final class IUAdditionsCoremod implements IFMLLoadingPlugin {


    @Override
    public void injectData(java.util.Map<String, Object> data) {
        try {
            // Make sure Mixin is bootstrapped (safe even if another booter did it)
            try {
                Class<?> bootstrap = Class.forName("org.spongepowered.asm.launch.MixinBootstrap");
                bootstrap.getMethod("init").invoke(null);
            } catch (Throwable ignored) {
            }

            // Now it won't be dropped
            org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.iuadditions.json");
            System.out.println("[IUAdditions] Registered mixins.iuadditions.json in injectData()");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // return empty/no-ops for the other IFMLLoadingPlugin methods…
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void acceptOptions(java.util.Map<String, String> options) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}