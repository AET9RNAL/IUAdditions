package com.aeternal.iuadditions.mixins;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class DebugPlugin implements IMixinConfigPlugin {

    static {
        // If class loads, this prints even when onLoad never runs (helps diagnose ABI issues vs. JSON not prepared).
        try {
            System.out.println("[IUAdditionsMixins] DebugPlugin <clinit> reached");
        } catch (Throwable ignored) {}
    }

    @Override public void onLoad(String mixinPackage) {
        try {
            System.out.println("[IUAdditionsMixins] mixin config loaded: " + mixinPackage);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override public String getRefMapperConfig() { return null; }

    @Override public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        try {
            System.out.println("[IUAdditionsMixins] consider: " + mixinClassName + " -> " + targetClassName);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return true;
    }

    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override public List<String> getMixins() { return null; } // never return an empty list!

    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
