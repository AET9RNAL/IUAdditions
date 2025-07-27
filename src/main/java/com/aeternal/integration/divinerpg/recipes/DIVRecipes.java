package com.aeternal.integration.divinerpg.recipes;


import com.aeternal.IUAItem;
import com.aeternal.integration.divinerpg.DivinerpgIntegration;
import com.aeternal.integration.divinerpg.item.DivItems;
import com.denfop.IUItem;
import com.denfop.api.Recipes;
import com.denfop.recipes.CompressorRecipe;
import com.denfop.recipes.MetalFormerRecipe;
import divinerpg.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.denfop.tiles.mechanism.TileGenerationMicrochip.add;

public class DIVRecipes {


    public static Item ingot_shadowphotonium = (DivinerpgIntegration.ingot_shadowphotonium);
    public static Item ingot_moltendemon = (DivinerpgIntegration.ingot_moltendemon);
    public static Item ingot_celestial = (DivinerpgIntegration.ingot_celestial);
    public static Item ingot_hades = (DivinerpgIntegration.ingot_hades);
    public static Item ingot_harmonite = (DivinerpgIntegration.ingot_harmonite);
    public static Item ingot_nucleoarlemite = (DivinerpgIntegration.ingot_nucleoarlemite);
    public static Item ingot_voidweave = (DivinerpgIntegration.ingot_voidweave);
    public static Item plate_shadowphotonium = (DivinerpgIntegration.plate_shadowphotonium);
    public static Item plate_moltendemon = (DivinerpgIntegration.plate_moltendemon);
    public static Item plate_celestial = (DivinerpgIntegration.plate_celestial);
    public static Item plate_hades = (DivinerpgIntegration.plate_hades);
    public static Item plate_harmonite = (DivinerpgIntegration.plate_harmonite);
    public static Item plate_nucleoarlemite = (DivinerpgIntegration.plate_nucleoarlemite);
    public static Item plate_voidweave = (DivinerpgIntegration.plate_voidweave);
    public static Item plate_shadowphotoniumdense = (DivinerpgIntegration.plate_shadowphotoniumdense);
    public static Item plate_moltendemondense = (DivinerpgIntegration.plate_moltendemondense);
    public static Item plate_celestialdense = (DivinerpgIntegration.plate_celestialdense);
    public static Item plate_hadesdense = (DivinerpgIntegration.plate_hadesdense);
    public static Item plate_harmonitedense = (DivinerpgIntegration.plate_harmonitedense);
    public static Item plate_nucleoarlemitedense = (DivinerpgIntegration.plate_nucleoarlemitedense);
    public static Item plate_voidweavedense = (DivinerpgIntegration.plate_voidweavedense);

    public static Item circuitQuantum = IUItem.cirsuitQuantum.getItem();
    public static ItemStack circuitSpectral = IUItem.circuitSpectral;
    public static Item compressedCarbon = IUItem.compresscarbon;
    public static final ItemStack eden_capacitor = new ItemStack(IUAItem.divItems, 1, 0);
    public static final ItemStack wildwood_capacitor = new ItemStack(IUAItem.divItems, 1, 1);
    public static final ItemStack apalachia_capacitor = new ItemStack(IUAItem.divItems, 1, 2);
    public static final ItemStack skythern_capacitor = new ItemStack(IUAItem.divItems, 1, 3);
    public static final ItemStack mortum_capacitor = new ItemStack(IUAItem.divItems, 1, 4);
    public static final ItemStack arcana_capacitor = new ItemStack(IUAItem.divItems, 1, 5);
    public static final ItemStack vethea_capacitor = new ItemStack(IUAItem.divItems, 1, 6);
    public static final ItemStack eden_soic = new ItemStack(IUAItem.divItems, 1, 7);
    public static final ItemStack wildwood_soic = new ItemStack(IUAItem.divItems, 1, 8);
    public static final ItemStack apalachia_soic = new ItemStack(IUAItem.divItems, 1, 9);
    public static final ItemStack skythern_soic = new ItemStack(IUAItem.divItems, 1, 10);
    public static final ItemStack mortum_soic = new ItemStack(IUAItem.divItems, 1, 11);
    public static final ItemStack arcana_soic = new ItemStack(IUAItem.divItems, 1, 12);
    public static final ItemStack vethea_soic = new ItemStack(IUAItem.divItems, 1, 13);
    public static final ItemStack eden_qfp = new ItemStack(IUAItem.divItems, 1, 14);
    public static final ItemStack wildwood_qfp = new ItemStack(IUAItem.divItems, 1, 15);
    public static final ItemStack apalachia_qfp = new ItemStack(IUAItem.divItems, 1, 16);
    public static final ItemStack skythern_qfp = new ItemStack(IUAItem.divItems, 1, 17);
    public static final ItemStack mortum_qfp = new ItemStack(IUAItem.divItems, 1, 18);
    public static final ItemStack arcana_qfp = new ItemStack(IUAItem.divItems, 1, 19);
    public static final ItemStack vethea_qfp = new ItemStack(IUAItem.divItems, 1, 20);
    public static final ItemStack eden_circuit = new ItemStack(IUAItem.divItems, 1, 21);
    public static final ItemStack wildwood_circuit = new ItemStack(IUAItem.divItems, 1, 22);
    public static final ItemStack apalachia_circuit = new ItemStack(IUAItem.divItems, 1, 23);
    public static final ItemStack skythern_circuit = new ItemStack(IUAItem.divItems, 1, 24);
    public static final ItemStack mortum_circuit = new ItemStack(IUAItem.divItems, 1, 25);
    public static final ItemStack arcana_circuit = new ItemStack(IUAItem.divItems, 1, 26);
    public static final ItemStack vethea_circuit = new ItemStack(IUAItem.divItems, 1, 27);
    public static final ItemStack eden_core = new ItemStack(IUAItem.divItems, 1, 28);
    public static final ItemStack wildwood_core = new ItemStack(IUAItem.divItems, 1, 29);
    public static final ItemStack apalachia_core = new ItemStack(IUAItem.divItems, 1, 30);
    public static final ItemStack skythern_core = new ItemStack(IUAItem.divItems, 1, 31);
    public static final ItemStack mortum_core = new ItemStack(IUAItem.divItems, 1, 32);
    public static final ItemStack arcana_core = new ItemStack(IUAItem.divItems, 1, 33);
    public static final ItemStack vethea_core = new ItemStack(IUAItem.divItems, 1, 34);
    public static final ItemStack crossdimensional_core = new ItemStack(IUAItem.divItems, 1, 35);


    public static void init() {
        DIVBaseRecipe();
        MicrochipRecipe();
        DIVCompressorRecipe();
        DIVRollingRecipe();
    }


    public static void DIVBaseRecipe() {
        Recipes.recipe.addRecipe(eden_qfp,
                "CCC",
                "EQE",
                "AAA",

                ('C'), ("plateCarbon"),
                ('E'), eden_capacitor,
                ('Q'), IUItem.cirsuitQuantum,
                ('A'), ("chunkEden")
        );
        Recipes.recipe.addRecipe(wildwood_qfp,
                "CCC",
                "EQE",
                "AAA",

                ('C'), ("plateCarbon"),
                ('E'), wildwood_capacitor,
                ('Q'), IUItem.cirsuitQuantum,
                ('A'), ("chunkWildwood")
        );
        Recipes.recipe.addRecipe(apalachia_qfp,
                "CCC",
                "EQE",
                "AAA",

                ('C'), ("plateCarbon"),
                ('E'), apalachia_capacitor,
                ('Q'), IUItem.cirsuitQuantum,
                ('A'), ("chunkApalachia")
        );
        Recipes.recipe.addRecipe(skythern_qfp,
                "CCC",
                "EQE",
                "AAA",

                ('C'), compressedCarbon,
                ('E'), skythern_capacitor,
                ('Q'), IUItem.circuitSpectral,
                ('A'), ("chunkSkythern")
        );
        Recipes.recipe.addRecipe(mortum_qfp,
                "CCC",
                "EQE",
                "AAA",

                ('C'), compressedCarbon,
                ('E'), mortum_capacitor,
                ('Q'), IUItem.circuitSpectral,
                ('A'), ("chunkMortum")
        );
        Recipes.recipe.addRecipe(arcana_qfp,
                "CCC",
                "EQE",
                "AAA",

                ('C'), compressedCarbon,
                ('E'), arcana_capacitor,
                ('Q'), IUItem.circuitSpectral,
                ('A'), ItemRegistry.arcanium
        );
        Recipes.recipe.addRecipe(eden_soic,
                "SSS",
                "SCS",
                "SSS",

                ('S'), ("stickElectrum"),
                ('C'), eden_capacitor
        );
        Recipes.recipe.addRecipe(wildwood_soic,
                "SSS",
                "SCS",
                "SSS",

                ('S'), ("stickChromium"),
                ('C'), wildwood_capacitor
        );
        Recipes.recipe.addRecipe(apalachia_soic,
                "SSS",
                "SCS",
                "SSS",

                ('S'), ("stickSpinel"),
                ('C'), apalachia_capacitor
        );
        Recipes.recipe.addRecipe(skythern_soic,
                "SSS",
                "SCS",
                "SSS",

                ('S'), ("stickIridium"),
                ('C'), skythern_capacitor
        );
        Recipes.recipe.addRecipe(mortum_soic,
                "SSS",
                "SCS",
                "SSS",

                ('S'), ("stickCaravky"),
                ('C'), mortum_capacitor
        );
        Recipes.recipe.addRecipe(arcana_soic,
                "SSS",
                "SCS",
                "SSS",

                ('S'), ("stickManganese"),
                ('C'), arcana_capacitor
        )

        ;
    }




    public static void MicrochipRecipe(){
        add(
                new ItemStack(IUItem.energiumDust.getItem(),1,24),
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(ItemRegistry.edenGem),
                new ItemStack(IUItem.iuingot,1,13),
                new ItemStack(IUItem.iuingot,1,5),
                new ItemStack(IUAItem.divItems, 1, DivItems.DivItemsTypes.eden_capacitor.getId()),
                (short) 2500, true
        );

        add(
                new ItemStack(IUItem.energiumDust.getItem(),1,24),
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(ItemRegistry.wildwoodGem),
                new ItemStack(IUItem.iuingot,1,13),
                new ItemStack(IUItem.iuingot,1,5),
                new ItemStack(IUAItem.divItems, 1, DivItems.DivItemsTypes.wildwood_capacitor.getId()),
                (short) 2500, true
        );

        add(
                new ItemStack(IUItem.energiumDust.getItem(),1,24),
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(ItemRegistry.apalachiaGem),
                new ItemStack(IUItem.iuingot,1,13),
                new ItemStack(IUItem.iuingot,1,5),
                new ItemStack(IUAItem.divItems, 1, DivItems.DivItemsTypes.apalachia_capacitor.getId()),
                (short) 3000, true
        );

        add(
                new ItemStack(IUItem.energiumDust.getItem(),1,24),
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(ItemRegistry.skythernGem),
                new ItemStack(IUItem.iuingot,1,13),
                new ItemStack(IUItem.iuingot,1,5),
                new ItemStack(IUAItem.divItems, 1, DivItems.DivItemsTypes.skythern_capacitor.getId()),
                (short) 4000, true
        );

        add(
                new ItemStack(IUItem.energiumDust.getItem(),1,24),
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(ItemRegistry.mortumGem),
                new ItemStack(IUItem.iuingot,1,13),
                new ItemStack(IUItem.iuingot,1,5),
                new ItemStack(IUAItem.divItems, 1, DivItems.DivItemsTypes.mortum_capacitor.getId()),
                (short) 4000, true
        );

        add(
                new ItemStack(IUItem.energiumDust.getItem(),1,24),
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(ItemRegistry.collectorFragments),
                new ItemStack(IUItem.iuingot,1,13),
                new ItemStack(IUItem.iuingot,1,5),
                new ItemStack(IUAItem.divItems, 1, DivItems.DivItemsTypes.arcana_capacitor.getId()),
                (short) 5000, true
        );


    }

    public static void DIVCompressorRecipe() {
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_shadowphotonium), 9, new ItemStack(DivinerpgIntegration.plate_shadowphotoniumdense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_moltendemon), 9, new ItemStack(DivinerpgIntegration.plate_moltendemondense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_hades), 9, new ItemStack(DivinerpgIntegration.plate_hadesdense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_harmonite), 9, new ItemStack(DivinerpgIntegration.plate_harmonitedense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_nucleoarlemite), 9, new ItemStack(DivinerpgIntegration.plate_nucleoarlemitedense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_celestial), 9, new ItemStack(DivinerpgIntegration.plate_celestialdense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_voidweave), 9, new ItemStack(DivinerpgIntegration.plate_voidweavedense));

    }

    public static void DIVRollingRecipe() {

        MetalFormerRecipe.addmolot("ingotShadowPhotonium", new ItemStack(DivinerpgIntegration.plate_shadowphotonium));
        MetalFormerRecipe.addmolot("ingotDemonic", new ItemStack(DivinerpgIntegration.plate_moltendemon));
        MetalFormerRecipe.addmolot("ingotCelestial", new ItemStack(DivinerpgIntegration.plate_celestial));
        MetalFormerRecipe.addmolot("ingotHades", new ItemStack(DivinerpgIntegration.plate_hades));
        MetalFormerRecipe.addmolot("ingotHarmonite", new ItemStack(DivinerpgIntegration.plate_harmonite));
        MetalFormerRecipe.addmolot("ingotNucleoArlemite", new ItemStack(DivinerpgIntegration.plate_nucleoarlemite));
        MetalFormerRecipe.addmolot("ingotVoidweave", new ItemStack(DivinerpgIntegration.plate_voidweave));

    }

}