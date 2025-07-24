package com.aeternal;

import net.minecraftforge.fml.common.Loader;

public class Constants {

    public static final String MOD_ID = "iuadditions";
    public static final String MOD_NAME = "IU:Additions";
    public static final String MOD_VERSION = "1.0";
    public static final String MOD_DEPS = "required-after:industrialupgrade;after:astralsorcery;after:forestry;" +
            "after:divinerpg;after:botania;after:thaumcraft;after:draconicevolution";

    public static final String TEXTURES = "iuadditions";
    public static final boolean PU_LOADED = Loader.isModLoaded("powerutils");
    public static final boolean DE_LOADED = Loader.isModLoaded("draconicevolution");
    public static final boolean AVA_LOADED = Loader.isModLoaded("avaritia");
    public static final boolean AE2_LOADED = Loader.isModLoaded("appliedenergistics2");
    public static final boolean BA_LOADED = Loader.isModLoaded("botania");
    public static final boolean BV_LOADED = Loader.isModLoaded("botaniverse");
    public static final boolean BOP_LOADED = Loader.isModLoaded("biomesoplenty");
    public static final boolean BADD_LOADED = Loader.isModLoaded("botanicadds");
    public static final boolean EB_LOADED = Loader.isModLoaded("extrabotany");
    public static final boolean AS_LOADED = Loader.isModLoaded("astralsorcery");
    public static final boolean ASA_LOADED = Loader.isModLoaded("astraladditions");
    public static final boolean DIV_LOADED = Loader.isModLoaded("divinerpg");
    public static final boolean FO_LOADED = Loader.isModLoaded("forestry");
    public static final boolean EXBEES_LOADED = Loader.isModLoaded("extrabees");
    public static final boolean TE_LOADED = Loader.isModLoaded("thermalexpansion");
    public static final boolean DE_CONFIRM = Config.DraconicConfirmed;
    public static final boolean BA_CONFIRM = Config.BotaniaConfirmed;
    public static final boolean AS_CONFIRM = Config.AstralConfirmed;
    public static final boolean DIV_CONFIRM = Config.DivineConfirmed;
    public static final boolean FO_CONFIRM = Config.ForestryConfirmed;
}
