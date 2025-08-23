package com.aeternal.iuadditions;


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
//import com.powerutils.IModelRender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scala.xml.dtd.impl.Base;

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

    public static ResourceLocation getIdentifier(final String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }


    @Mod.EventHandler
    public void load(final FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Config.loadNormalConfig(event.getSuggestedConfigurationFile());

        proxy.preInit(event);
        if(Constants.DE_LOADED && Constants.DE_CONFIRM && Constants.PU_LOADED) {
            itemSpectralPowerConverter = TileBlockCreator.instance.create(BlockSpectralConverter.class);
            itemSpectralQEConverter = TileBlockCreator.instance.create(BlockSpectralQEConverter.class);
        }
        if(Constants.BA_LOADED && Constants.BA_CONFIRM && Constants.PU_LOADED) {
            itemManaConverter = TileBlockCreator.instance.create(BlockManaConverter.class);
        }
        if (event.getSide() == Side.CLIENT) {
            if(Constants.AS_LOADED && Constants.AS_CONFIRM) {
                blockASSolarPanel.registerModels();
            }
            if(Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
                blockDivineSolarPanel.registerModels();
            }
            if(Constants.FO_LOADED && Constants.FO_CONFIRM) {
                blockForestrySolarPanel.registerModels();
            }
            if(Constants.DE_LOADED && Constants.DE_CONFIRM && Constants.PU_LOADED) {
                itemSpectralPowerConverter.registerModels();
                itemSpectralQEConverter.registerModels();
            }
            if(Constants.BA_LOADED && Constants.BA_CONFIRM && Constants.PU_LOADED) {
                itemManaConverter.registerModels();
            }

        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        registerOreDict();
    }

    @Mod.EventHandler
    public void enqueueMixins(final FMLPostInitializationEvent e){
        if (Config.KatanaMixins && IUAMixinCore.wasQueued(IUAMixinCore.CFG_KATANA)) {
            KatanaApplier.applyNowIfConfigured();
        }
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

        if (Constants.DIV_LOADED && Constants.DIV_CONFIRM) {
            OreDictionary.registerOre("ingotShadowPhotonium", new ItemStack(DivinerpgIntegration.ingot_shadowphotonium, 1));
            OreDictionary.registerOre("ingotDemonic", new ItemStack(DivinerpgIntegration.ingot_moltendemon, 1));
            OreDictionary.registerOre("ingotCelestial", new ItemStack(DivinerpgIntegration.ingot_celestial, 1));
            OreDictionary.registerOre("ingotHades", new ItemStack(DivinerpgIntegration.ingot_hades, 1));
            OreDictionary.registerOre("ingotHarmonite", new ItemStack(DivinerpgIntegration.ingot_harmonite, 1));
            OreDictionary.registerOre("ingotNucleoArlemite", new ItemStack(DivinerpgIntegration.ingot_nucleoarlemite, 1));
            OreDictionary.registerOre("ingotVoidweave", new ItemStack(DivinerpgIntegration.ingot_voidweave, 1));
        }
    }
}