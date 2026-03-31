package com.aeternal.iuadditions;


//import com.aeternal.iuadditions.api.annotation.processor.AnnotationRegistryProcessor;
import com.aeternal.iuadditions.hooks.BaseUpgradeSystemApplier;
import com.aeternal.iuadditions.hooks.KatanaApplier;
import com.aeternal.iuadditions.integration.divinerpg.DivinerpgIntegration;
import com.aeternal.iuadditions.hooks.EnumUpgradeModulesApplier;
import com.aeternal.iuadditions.mixins.IUAMixinCore;
import com.aeternal.iuadditions.proxy.CommonProxy;
import com.aeternal.iuadditions.spectralconverters.blocks.BlockManaConverter;
import com.aeternal.iuadditions.spectralconverters.blocks.BlockSpectralConverter;
import com.aeternal.iuadditions.spectralconverters.blocks.BlockSpectralQEConverter;
import com.aeternal.iuadditions.tabs.TabCore;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.aeternal.iuadditions.integration.astralsorcery.AstralSorceryIntegration.blockASSolarPanel;
import static com.aeternal.iuadditions.integration.divinerpg.DivinerpgIntegration.blockDivineSolarPanel;
import static com.aeternal.iuadditions.integration.forestry.ForestryIntegration.blockForestrySolarPanel;


@SuppressWarnings({"ALL", "UnnecessaryFullyQualifiedName"})
@Mod.EventBusSubscriber
@Mod(modid = Constants.MOD_ID,
        name = Constants.MOD_NAME,
        dependencies = Constants.MOD_DEPS,
        version = Constants.MOD_VERSION,
        acceptedMinecraftVersions = "[1.12,1.12.2]")
public final class Core {

    public static final CreativeTabs IUATab = new TabCore(0, "IU:AdditionsTab");

    public static final List<ItemStack> list = new ArrayList<>();
    public static BlockTileEntity itemSpectralPowerConverter;
    public static BlockTileEntity itemSpectralQEConverter;
    public static BlockTileEntity itemManaConverter;

    public static final Logger LOGGER = LogManager.getLogger(Constants.MOD_ID);

    @SidedProxy(
            clientSide = "com.aeternal.iuadditions.proxy.ClientProxy",
            serverSide = "com.aeternal.iuadditions.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("iuadditions")
    public static Core instance;

    public static final ResourceLocation getIdentifier(final String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }


    @Mod.EventHandler
    public void load(final FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Config.loadNormalConfig(event.getSuggestedConfigurationFile());

        proxy.preInit(event);
        if(Constants.isActive("draconicevolution") && Constants.isActive("powerutils")) {
            itemSpectralPowerConverter = TileBlockCreator.instance.create(BlockSpectralConverter.class);
            itemSpectralQEConverter = TileBlockCreator.instance.create(BlockSpectralQEConverter.class);
        }
        if(Constants.isActive("botania") && Constants.isActive("powerutils")) {
            itemManaConverter = TileBlockCreator.instance.create(BlockManaConverter.class);
        }
        if (event.getSide() == Side.CLIENT) {
            if(Constants.isActive("astralsorcery")) {
                blockASSolarPanel.registerModels();
            }
            if(Constants.isActive("divinerpg")) {
                blockDivineSolarPanel.registerModels();
            }
            if(Constants.isActive("forestry")) {
                blockForestrySolarPanel.registerModels();
            }
            if(Constants.isActive("draconicevolution") && Constants.isActive("powerutils")) {
                itemSpectralPowerConverter.registerModels();
                itemSpectralQEConverter.registerModels();
            }
            if(Constants.isActive("botania") && Constants.isActive("powerutils")) {
                itemManaConverter.registerModels();
            }

        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
//        AnnotationRegistryProcessor.registerOreDict();
        registerOreDict();
    }

    @Mod.EventHandler
    public void enqueueMixins(final FMLPostInitializationEvent e){
        if (Config.KatanaMixins && IUAMixinCore.wasQueued(IUAMixinCore.CFG_KATANA)) { KatanaApplier.applyNowIfConfigured(); }
        if (Config.UpgradeModulesMixins && IUAMixinCore.wasQueued(IUAMixinCore.CFG_UPGRADEMODULES)) {
            EnumUpgradeModulesApplier.applyNowIfConfigured();
            BaseUpgradeSystemApplier.applyFromConfig(
                    Config.CoreModifier_maxCount,
                    Config.NeutronModifier_maxCount,
                    Config.DebugEnum
            );
        }

    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static void registerOreDict() {
        if (Constants.isActive("divinerpg")) {
            registerOres(
                "ingotShadowPhotonium", DivinerpgIntegration.ingot_shadowphotonium,
                "ingotDemonic", DivinerpgIntegration.ingot_moltendemon,
                "ingotCelestial", DivinerpgIntegration.ingot_celestial,
                "ingotHades", DivinerpgIntegration.ingot_hades,
                "ingotHarmonite", DivinerpgIntegration.ingot_harmonite,
                "ingotNucleoArlemite", DivinerpgIntegration.ingot_nucleoarlemite,
                "ingotVoidweave", DivinerpgIntegration.ingot_voidweave
            );
        }
    }

    private static void registerOres(Object... pairs) {
        for (int i = 0; i < pairs.length; i += 2) {
            String oreName = (String) pairs[i];
            Item item = (Item) pairs[i + 1];
            OreDictionary.registerOre(oreName, new ItemStack(item, 1));
        }
    }
}