package com.aeternal.iuadditions.hooks;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.mixins.accessors.AccessorItemKatana;
import com.denfop.items.energy.ItemKatana;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public final class KatanaApplier {
    public static volatile boolean CFG_DEBUG    = false;
    public static volatile int     CFG_DMG      = 13;
    public static volatile float   CFG_EFF      = 1f;
    public static volatile double  CFG_GOD      = 0.0;
    public static volatile double  CFG_PIERCE   = 0.0;
    public static volatile double  CFG_SOUL_STEP= 100.0;
    public static volatile double  CFG_E_GOD    = 0.0;
    public static volatile double  CFG_E_PIERCE = 0.0;
    public static volatile double  CFG_E_SOUL   = 0.0;

    private KatanaApplier() {}

    /** Call once after your Config is loaded and items are registered (postInit/loadComplete). */
    public static void applyNowIfConfigured() {
        CFG_DEBUG     = Config.katanaDebug;
        CFG_DMG       = Math.max(0, (int) Config.katanaDmg);
        CFG_EFF       = (float) Math.max(0.0, Config.katanaEff);
        CFG_GOD       = Math.max(0.0, Config.katanaGodSlay);
        CFG_PIERCE    = Math.max(0.0, Config.katanaArmorPierce);
        CFG_SOUL_STEP = Config.katanaSoulStep > 0.0 ? Config.katanaSoulStep : 100.0;
        CFG_E_GOD     = Math.max(0.0, Config.godDmgEnergy);
        CFG_E_PIERCE  = Math.max(0.0, Config.armorPierceDmgEnergy);
        CFG_E_SOUL    = Math.max(0.0, Config.soulDmgEnergy);

        // Eagerly sync fields on existing ItemKatana instances
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item instanceof ItemKatana) {
                AccessorItemKatana acc = (AccessorItemKatana) (Object) item;
                acc.iuadditions$setDamage1(CFG_DMG);
               // acc.iuadditions$setEfficiency(CFG_EFF);
            }
        }
    }
}
