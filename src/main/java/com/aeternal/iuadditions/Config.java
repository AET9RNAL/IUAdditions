package com.aeternal.iuadditions;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public final class Config {

    public static double edenGenDay;
    public static double edenStorage;
    public static double edenOutput;
    public static double wildwoodGenDay;
    public static double wildwoodStorage;
    public static double wildwoodOutput;

    public static double apalachiaGenDay;
    public static double apalachiaStorage;
    public static double apalachiaOutput;

    public static double skythernGenDay;
    public static double skythernStorage;
    public static double skythernOutput;

    public static double mortumGenDay;
    public static double mortumStorage;
    public static double mortumOutput;

    public static double arcanaGenDay;
    public static double arcanaStorage;
    public static double arcanaOutput;

    public static double vetheaGenDay;
    public static double vetheaStorage;
    public static double vetheaOutput;

    public static double crossdimentionalGenDay;
    public static double crossdimentionalStorage;
    public static double crossdimentionalOutput;

    public static double brightstarlightcollectorGenDay;
    public static double brightstarlightcollectorStorage;
    public static double brightstarlightcollectorOutput;

    public static double dimstarlightcollectorGenDay;
    public static double dimstarlightcollectorStorage;
    public static double dimstarlightcollectorOutput;

    public static double faintstarlightcollectorGenDay;
    public static double faintstarlightcollectorStorage;
    public static double faintstarlightcollectorOutput;

    public static double mythrilliumGenDay;
    public static double mythrilliumStorage;
    public static double mythrilliumOutput;

    public static double mythminiteGenDay;
    public static double mythminiteStorage;
    public static double mythminiteOutput;

    public static double pulsatingGenDay;
    public static double pulsatingStorage;
    public static double pulsatingOutput;

    public static double adaminiteGenDay;
    public static double adaminiteStorage;
    public static double adaminiteOutput;
    public static double cyanbeekeeperGenDay;
    public static double cyanbeekeeperStorage;
    public static double cyanbeekeeperOutput;
    public static double bluebeekeeperGenDay;
    public static double bluebeekeeperStorage;
    public static double bluebeekeeperOutput;
    public static double greenbeekeeperGenDay;
    public static double greenbeekeeperStorage;
    public static double greenbeekeeperOutput;
    public static double staticbeekeeperGenDay;
    public static double staticbeekeeperStorage;
    public static double staticbeekeeperOutput;
    public static double purplebeekeeperGenDay;
    public static double purplebeekeeperStorage;
    public static double purplebeekeeperOutput;
    public static double amberbeekeeperGenDay;
    public static double amberbeekeeperStorage;
    public static double amberbeekeeperOutput;
    public static double refractivebeekeeperGenDay;
    public static double refractivebeekeeperStorage;
    public static double refractivebeekeeperOutput;
    public static boolean promt;
    public static boolean AstralConfirmed;
    public static boolean ForestryConfirmed;
    public static boolean DivineConfirmed;
    public static boolean BotaniaConfirmed;
    public static boolean DraconicConfirmed;

    public static boolean registerAstralPanels;
    public static boolean disableUpdateCheck;

    public static int coefficientqe;
    public static int coefficientrf;
    public static double coefficienteftomana;
    public static double coefficientmanatoef;
    public static int manatransferrate;
    public static byte coreSearchRange;

    public static boolean allowEfToManaConversion;
    public static boolean allowManaToEfConversion;

    //Hunter CFG
    public static boolean allowBoss;
    public static boolean debugHunter;

    //Katana CFG
    public static boolean katanaDebug;
    public static int katanaDmg;
    public static int katanaEff;
    public static int katanaArmorPierce;
    public static int katanaGodSlay;
    public static int katanaSoulStep;
    public static double godDmgEnergy;
    public static double armorPierceDmgEnergy;
    public static double soulDmgEnergy;

    //Modules CFG
    public static boolean UpgradeModulesMixin;
    public static boolean DebugEnum;
    public static byte ModuleGENDAYmaxCount;
    public static byte ModuleGENNIGHTmaxCount;
    public static byte ModulePROTECTIONmaxCount;
    public static byte ModuleEFFICIENCYmaxCount;
    public static byte ModuleBOWENERGYmaxCount;
    public static byte ModuleSABERENERGYmaxCount;
    public static byte ModuleDIG_DEPTHmaxCount;
    public static byte ModuleSPEEDmaxCount;
    public static byte ModuleJUMPmaxCount;
    public static byte ModuleBOWDAMAGEmaxCount;
    public static byte ModuleSABER_DAMAGEmaxCount;
    public static byte ModuleAOE_DIGmaxCount;
    public static byte ModuleFLYSPEEDmaxCount;
    public static byte ModuleSTORAGEmaxCount;
    public static byte ModuleENERGYmaxCount;
    public static byte ModuleVAMPIRESmaxCount;
    public static byte ModuleRESISTANCEmaxCount;
    public static byte ModuleLOOTmaxCount;
    public static byte ModuleFIREmaxCount;
    public static byte ModuleLUCKYmaxCount;
    public static byte ModuleEFFICIENTmaxCount;
    public static byte ModuleTHORNSmaxCount;
    public static byte ModuleLAPPACK_ENERGYmaxCount;

    public static int CoreModifier_maxCount = 1;
    public static int NeutronModifier_maxCount = 1;
    public static void loadNormalConfig(final File configFile) {
        Core.LOGGER.info("Loading IUA Config from " + configFile.getAbsolutePath());

        final Configuration config = new Configuration(configFile);
        try {
            AstralConfirmed = config.getBoolean("Astral confirm","Integrations",true,"Are you sure you want to enable Astral integrations?");
            ForestryConfirmed = config.getBoolean("Forestry confirm","Integrations",true,"Are you sure you want to enable Forestry integrations?");
            DivineConfirmed = config.getBoolean("Divine confirm","Integrations",true,"Are you sure you want to enable Divine integrations?");
            BotaniaConfirmed = config.getBoolean("Botania confirm","Integrations",true,"Are you sure you want to enable Botania integrations?");
            DraconicConfirmed = config.getBoolean("Draconic confirm","Integrations",true,"Are you sure you want to enable Draconic Evolution integrations?");

            //CustomSolarPanelsCFG
            edenGenDay = config.get("Configuration Solar Panels", "EdenGenDay", 81920).getDouble(81920.0);
            edenOutput = config.get("Configuration Solar Panels", "EdenOutput", 163840).getDouble(163840.0);
            edenOutput = config.get("Configuration Solar Panels", "EdenOutput", 163840).getDouble(163840.0);
            edenStorage = config.get("Configuration Solar Panels", "EdenStorage", 1.5E9).getDouble(1.5E9);
            wildwoodGenDay = config.get("Configuration Solar Panels", "WildwoodGenDay", 327680).getDouble(327680.0);
            wildwoodOutput = config.get("Configuration Solar Panels", "WildwoodOutput", 655360).getDouble(655360.0);
            wildwoodStorage = config.get("Configuration Solar Panels", "WildwoodStorage", 5.0E9).getDouble(5.0E9);
            apalachiaGenDay = config.get("Configuration Solar Panels", "ApalachiaGenDay", 1310720).getDouble(1310720.0);
            apalachiaOutput = config.get("Configuration Solar Panels", "ApalachiaOutput", 2621440).getDouble(2621440.0);
            apalachiaStorage = config.get("Configuration Solar Panels", "ApalachiaStorage", 6.5E9).getDouble(6.5E9);
            skythernGenDay = config.get("Configuration Solar Panels", "SkythernGenDay", 5242880).getDouble(5242880.0);
            skythernOutput = config.get("Configuration Solar Panels", "SkythernOutput", 10485760).getDouble(1.048576E7);
            skythernStorage = config.get("Configuration Solar Panels", "SkythernStorage", 1.0E10).getDouble(1.0E10);
            mortumGenDay = config.get("Configuration Solar Panels", "MortumGenDay", 20971520).getDouble(2.097152E7);
            mortumOutput = config.get("Configuration Solar Panels", "MortumOutput", 41943040).getDouble(4.194304E7);
            mortumStorage = config.get("Configuration Solar Panels", "MortumStorage", 2.5E10).getDouble(2.5E10);
            arcanaGenDay = config.get("Configuration Solar Panels", "ArcanaGenDay", 83886080).getDouble(8.388608E7);
            arcanaOutput = config.get("Configuration Solar Panels", "ArcanaOutput", 167772160).getDouble(1.6777216E8);
            arcanaStorage = config.get("Configuration Solar Panels", "ArcanaStorage", 2.5E11).getDouble(2.5E11);
            vetheaGenDay = config.get("Configuration Solar Panels", "VatheaGenDay", 335544320).getDouble(3.3554432E8);
            vetheaOutput = config.get("Configuration Solar Panels", "VatheaOutput", 671088640).getDouble(6.7108864E8);
            vetheaStorage = config.get("Configuration Solar Panels", "VatheaStorage", 2.5E12).getDouble(2.5E12);
            crossdimentionalGenDay = config.get("Configuration Solar Panels", "CrossdimentionalGenDay", 671088640).getDouble(6.7108864E8);
            crossdimentionalOutput = config.get("Configuration Solar Panels", "CrossdimentionalOutput", 1342177280).getDouble(1.34217728E9);
            crossdimentionalStorage = config.get("Configuration Solar Panels", "CrossdimentionalStorage", 2.5E12).getDouble(2.5E12);
            brightstarlightcollectorGenDay =
                    config.get("Configuration Solar Panels", "BrightstarlightcollectorGenDay", 16128).getDouble(16128);
            brightstarlightcollectorOutput =
                    config.get("Configuration Solar Panels", "BrightstarlightcollectorOutput", 32256).getDouble(32256);
            brightstarlightcollectorStorage = config.get("Configuration Solar Panels", "BrightstarlightcollectorStorage", 1.0E9).getDouble(1.0E9);

            dimstarlightcollectorGenDay =
                    config.get("Configuration Solar Panels", "DimstarlightcollectorGenDay", 258048).getDouble(258048);
            dimstarlightcollectorOutput =
                    config.get("Configuration Solar Panels", "DimstarlightcollectorOutput", 516096).getDouble(516096);
            dimstarlightcollectorStorage = config.get("Configuration Solar Panels", "DimstarlightcollectorStorage", 5.0E9).getDouble(5.0E9);

            faintstarlightcollectorGenDay =
                    config.get("Configuration Solar Panels", "FaintstarlightcollectorGenDay", 2064384).getDouble(2064384);
            faintstarlightcollectorOutput =
                    config.get("Configuration Solar Panels", "FaintstarlightcollectorOutput", 4128768).getDouble(4128768);
            faintstarlightcollectorStorage = config.get("Configuration Solar Panels", "FaintstarlightcollectorStorage", 5.0E9).getDouble(5.0E9);

            cyanbeekeeperGenDay = config.get("Configuration Solar Panels", "CyanbeekeeperGenDay", 20480).getDouble(20480.0);
            cyanbeekeeperOutput = config.get("Configuration Solar Panels", "CyanbeekeeperOutput", 40960).getDouble(40960.0);
            cyanbeekeeperStorage = config.get("Configuration Solar Panels", "CyanbeekeeperStorage", 1.0E9).getDouble(1.0E9);

            bluebeekeeperGenDay = config.get("Configuration Solar Panels", "BluebeekeeperGenDay", 245760).getDouble(245760);
            bluebeekeeperOutput = config.get("Configuration Solar Panels", "BluebeekeeperOutput", 491520).getDouble(491520);
            bluebeekeeperStorage = config.get("Configuration Solar Panels", "BluebeekeeperStorage", 5.0E9).getDouble(5.0E9);

            greenbeekeeperGenDay = config.get("Configuration Solar Panels", "GreenbeekeeperGenDay", 1228800).getDouble(1228800);
            greenbeekeeperOutput = config.get("Configuration Solar Panels", "GreenbeekeeperOutput", 5898240).getDouble(5898240);
            greenbeekeeperStorage = config.get("Configuration Solar Panels", "GreenbeekeeperStorage", 6.5E9).getDouble(6.5E9);

            staticbeekeeperGenDay = config.get("Configuration Solar Panels", "StaticbeekeeperGenDay", 6144000).getDouble(6144000);
            staticbeekeeperOutput = config.get("Configuration Solar Panels", "StaticbeekeeperOutput", 12288000).getDouble(12288000);
            staticbeekeeperStorage = config.get("Configuration Solar Panels", "StaticbeekeeperStorage", 1.0E10).getDouble(1.0E10);

            purplebeekeeperGenDay = config.get("Configuration Solar Panels", "PurplebeekeeperGenDay", 30720000).getDouble(30720000);
            purplebeekeeperOutput = config.get("Configuration Solar Panels", "PurplebeekeeperOutput", 61440000).getDouble(61440000);
            purplebeekeeperStorage = config.get("Configuration Solar Panels", "PurplebeekeeperStorage", 2.5E10).getDouble(2.5E10);

            amberbeekeeperGenDay =
                    config.get("Configuration Solar Panels", "AmberbeekeeperGenDay", 153600000).getDouble(153600000);
            amberbeekeeperOutput = config.get("Configuration Solar Panels", "AmberbeekeeperOutput", 307200000).getDouble(307200000);
            amberbeekeeperStorage = config.get("Configuration Solar Panels", "AmberbeekeeperStorage", 2.5E11).getDouble(2.5E11);

            refractivebeekeeperGenDay =
                    config.get("Configuration Solar Panels", "RefractivebeekeeperGenDay", 460800000).getDouble(460800000);
            refractivebeekeeperOutput =
                    config.get("Configuration Solar Panels", "RefractivebeekeeperOutput", 921600000).getDouble(921600000);
            refractivebeekeeperStorage = config.get("Configuration Solar Panels", "RefractivebeekeeperStorage", 2.5E12).getDouble(2.5E12);

            //CustomSolarPanelsCFG_END

            coefficientqe = config.get("Converters", "coefficient qe", 64).getInt(64);
            if (coefficientqe < 1) {
                coefficientqe = 64;
            }
            coefficientrf = config.get("Converters", "coefficient rf", 4).getInt(4);
            if (coefficientrf < 1) {
                coefficientrf = 4;
            }
            coreSearchRange = (byte) config.get("Converters", "The area in blocks where spectral converter will attempt to locate the energy core (max 128)", 16).getInt(16);
            if (coreSearchRange < 1) {
                coreSearchRange = 16;
            }
            allowEfToManaConversion = config.getBoolean("EF to MANA", "Converters", false, "Allow EF to Mana Conversion");
            allowManaToEfConversion = config.getBoolean("MANA to EF", "Converters", true, "Allow Mana to Ef Conversion");
            coefficienteftomana = config.get("Converters", "coefficient ef to mana", 0.02).getDouble(0.02);
            coefficientmanatoef = config.get("Converters", "coefficient mana to ef", 0.02).getDouble(0.02);
            manatransferrate = config.get("Converters", "mana I/O transfer rate", 50000).getInt(50000);

            debugHunter = config.getBoolean("Enable Automatic Hunter Mixin Debug", "Hunter Mixins",true, "");
            allowBoss = config.getBoolean("Enable boss spawning in Automatic Hunter", "Hunter Mixins",true, "");

            katanaDebug = config.getBoolean("Katana debugger","Katana Mixins",true,"Enable debugger");
            katanaDmg = config.get("Katana Mixins", "Katana base damage modifier",14).getInt(14);
            katanaArmorPierce = config.get("Katana Mixins", "Katana armor pierce damage modifier",80).getInt(80);
            katanaGodSlay = config.get("Katana Mixins", "Katana god damage modifier",10).getInt(10);
            katanaSoulStep = config.get("Katana Mixins", "Katana soul damage modifier",30).getInt(30);
            godDmgEnergy = config.get("Katana Mixins", "Katana god energy drain",1000.00).getDouble(1000.00);
            armorPierceDmgEnergy = config.get("Katana Mixins", "Katana armor piercing damage energy drain",500.00).getDouble(500.00);
            soulDmgEnergy = config.get("Katana Mixins", "Katana soul damage energy drain",800.00).getDouble(800.00);

            UpgradeModulesMixin            = config.getBoolean("Enable Mixin", "Upgrade Modules Mixins",true, "");
            DebugEnum                      = config.getBoolean("Mixin Debugger", "Upgrade Modules Mixins",true, "");
            ModuleGENDAYmaxCount           = (byte) config.get("Upgrade Modules Mixins", "Module GenDay max count (def:2)",           2).getInt(2);
            ModuleGENDAYmaxCount           = (byte) config.get("Upgrade Modules Mixins", "Module GenDay max count (def:2)",           2).getInt(2);
            ModuleGENNIGHTmaxCount         = (byte) config.get("Upgrade Modules Mixins", "Module GenNight max count (def:2)",         2).getInt(2);
            ModulePROTECTIONmaxCount       = (byte) config.get("Upgrade Modules Mixins", "Module Protection max count (def:4)",       4).getInt(4);
            ModuleEFFICIENCYmaxCount       = (byte) config.get("Upgrade Modules Mixins", "Module Efficiency max count (def:2)",       2).getInt(2);
            ModuleBOWENERGYmaxCount        = (byte) config.get("Upgrade Modules Mixins", "Module BowEnergy max count (def:2)",        2).getInt(2);
            ModuleSABERENERGYmaxCount      = (byte) config.get("Upgrade Modules Mixins", "Module SaberEnergy max count (def:2)",      2).getInt(2);
            ModuleDIG_DEPTHmaxCount        = (byte) config.get("Upgrade Modules Mixins", "Module DigDepth max count (def:2)",         2).getInt(2);
            ModuleSPEEDmaxCount            = (byte) config.get("Upgrade Modules Mixins", "Module Speed max count (def:1)",            1).getInt(1);
            ModuleJUMPmaxCount             = (byte) config.get("Upgrade Modules Mixins", "Module Jump max count (def:1)",             1).getInt(1);
            ModuleBOWDAMAGEmaxCount        = (byte) config.get("Upgrade Modules Mixins", "Module BowDamage max count (def:2)",        2).getInt(2);
            ModuleSABER_DAMAGEmaxCount     = (byte) config.get("Upgrade Modules Mixins", "Module SaberDamage max count (def:2)",      2).getInt(2);
            ModuleAOE_DIGmaxCount          = (byte) config.get("Upgrade Modules Mixins", "Module AOEDig max count (def:2)",           2).getInt(2);
            ModuleFLYSPEEDmaxCount         = (byte) config.get("Upgrade Modules Mixins", "Module FlySpeed max count (def:2)",         2).getInt(2);
            ModuleSTORAGEmaxCount          = (byte) config.get("Upgrade Modules Mixins", "Module Storage max count (def:2)",          2).getInt(2);
            ModuleENERGYmaxCount           = (byte) config.get("Upgrade Modules Mixins", "Module Energy max count (def:2)",           2).getInt(2);
            ModuleVAMPIRESmaxCount         = (byte) config.get("Upgrade Modules Mixins", "Module Vampires max count (def:3)",         3).getInt(3);
            ModuleRESISTANCEmaxCount       = (byte) config.get("Upgrade Modules Mixins", "Module Resistance max count (def:3)",       3).getInt(3);
            ModuleLOOTmaxCount             = (byte) config.get("Upgrade Modules Mixins", "Module Loot max count (def:3)",             3).getInt(3);
            ModuleFIREmaxCount             = (byte) config.get("Upgrade Modules Mixins", "Module Fire max count (def:2)",             2).getInt(2);
            ModuleLUCKYmaxCount            = (byte) config.get("Upgrade Modules Mixins", "Module Lucky max count (def:3)",            3).getInt(3);
            ModuleEFFICIENTmaxCount        = (byte) config.get("Upgrade Modules Mixins", "Module Efficient max count (def:3)",        3).getInt(3);
            ModuleTHORNSmaxCount           = (byte) config.get("Upgrade Modules Mixins", "Module Thorns max count (def:3)",           3).getInt(3);
            ModuleLAPPACK_ENERGYmaxCount   = (byte) config.get("Upgrade Modules Mixins", "Module LappackEnergy max count (def:3)",    3).getInt(3);

            CoreModifier_maxCount          = (byte) config.get("Upgrade Modules Mixins", "Diffraction cores max count (def:1)",    3).getInt(3);
            NeutronModifier_maxCount       = (byte) config.get("Upgrade Modules Mixins", "Neutron ingots max count (def:1)",    2).getInt(2);
        } catch (Exception e) {
            Core.LOGGER.fatal("Fatal error reading config file.", e);
            throw new RuntimeException(e);
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }

    }
}





