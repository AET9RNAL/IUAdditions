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

    static {
        try {
            Mixins.addConfiguration("mixins.iuadditions.json");
            System.out.println("[IUAdditionsCoremod] Registered mixins.iuadditions.json");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override public String[] getASMTransformerClass() { return new String[0]; }
    @Override public String getModContainerClass() { return null; }
    @Override public String getSetupClass() { return null; }
    @Override public void injectData(Map<String, Object> data) { }
    @Override public String getAccessTransformerClass() { return null; }
}
