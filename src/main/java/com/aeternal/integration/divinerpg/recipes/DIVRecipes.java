//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.divinerpg.recipes;


import com.aeternal.integration.divinerpg.DivinerpgIntegration;
import com.denfop.IUItem;
import com.denfop.recipes.CompressorRecipe;
import com.denfop.recipes.MetalFormerRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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


//    public static void DIVBaseRecipe() {
//        Recipes.recipe.addRecipe(qfp_eden,
//                "CCC",
//                "EQE",
//                "AAA",
//
//                ('C'), ("plateCarbon"),
//                ('E'), capacitor_eden,
//                ('Q'), IUItem.cirsuitQuantum,
//                ('A'), ("chunkEden")
//        );
//        Recipes.recipe.addRecipe(qfp_wildwood,
//                "CCC",
//                "EQE",
//                "AAA",
//
//                ('C'), ("plateCarbon"),
//                ('E'), capacitor_wildwood,
//                ('Q'), IUItem.cirsuitQuantum,
//                ('A'), ("chunkWildwood")
//        );
//        Recipes.recipe.addRecipe(qfp_apalachia,
//                "CCC",
//                "EQE",
//                "AAA",
//
//                ('C'), ("plateCarbon"),
//                ('E'), capacitor_apalachia,
//                ('Q'), IUItem.cirsuitQuantum,
//                ('A'), ("chunkApalachia")
//        );
//        Recipes.recipe.addRecipe(qfp_skythern,
//                "CCC",
//                "EQE",
//                "AAA",
//
//                ('C'), compressedCarbon,
//                ('E'), capacitor_skythern,
//                ('Q'), IUItem.circuitSpectral,
//                ('A'), ("chunkSkythern")
//        );
//        Recipes.recipe.addRecipe(qfp_mortum,
//                "CCC",
//                "EQE",
//                "AAA",
//
//                ('C'), compressedCarbon,
//                ('E'), capacitor_mortum,
//                ('Q'), IUItem.circuitSpectral,
//                ('A'), ("chunkMortum")
//        );
//        Recipes.recipe.addRecipe(qfp_arcana,
//                "CCC",
//                "EQE",
//                "AAA",
//
//                ('C'), compressedCarbon,
//                ('E'), capacitor_arcana,
//                ('Q'), IUItem.circuitSpectral,
//                ('A'), ItemRegistry.arcanium
//        );
//        Recipes.recipe.addRecipe(soic_eden,
//                "SSS",
//                "SCS",
//                "SSS",
//
//                ('S'), ("stickElectrum"),
//                ('C'), capacitor_eden
//        );
//        Recipes.recipe.addRecipe(soic_wildwood,
//                "SSS",
//                "SCS",
//                "SSS",
//
//                ('S'), ("stickChromium"),
//                ('C'), capacitor_wildwood
//        );
//        Recipes.recipe.addRecipe(soic_apalachia,
//                "SSS",
//                "SCS",
//                "SSS",
//
//                ('S'), ("stickSpinel"),
//                ('C'), capacitor_apalachia
//        );
//        Recipes.recipe.addRecipe(soic_skythern,
//                "SSS",
//                "SCS",
//                "SSS",
//
//                ('S'), ("stickIridium"),
//                ('C'), capacitor_skythern
//        );
//        Recipes.recipe.addRecipe(soic_mortum,
//                "SSS",
//                "SCS",
//                "SSS",
//
//                ('S'), ("stickCaravky"),
//                ('C'), capacitor_mortum
//        );
//        Recipes.recipe.addRecipe(soic_arcana,
//                "SSS",
//                "SCS",
//                "SSS",
//
//                ('S'), ("stickManganese"),
//                ('C'), capacitor_arcana
//        )
//
//        ;
//    }




//    public static void MicrochipRecipe(){
//        add(
//                new ItemStack(IUItem.energiumDust.getItem(),1,24),
//                new ItemStack(IUItem.iuingot,1,2),
//                new ItemStack(ItemRegistry.edenGem),
//                new ItemStack(IUItem.iuingot,1,13),
//                new ItemStack(IUItem.iuingot,1,5),
//                new ItemStack(DivinerpgIntegration.capacitor_eden),
//                (short) 2500, true
//        );
//
//        add(
//                new ItemStack(IUItem.energiumDust.getItem(),1,24),
//                new ItemStack(IUItem.iuingot,1,2),
//                new ItemStack(ItemRegistry.wildwoodGem),
//                new ItemStack(IUItem.iuingot,1,13),
//                new ItemStack(IUItem.iuingot,1,5),
//                new ItemStack(DivinerpgIntegration.capacitor_wildwood),
//                (short) 2500, true
//        );
//
//        add(
//                new ItemStack(IUItem.energiumDust.getItem(),1,24),
//                new ItemStack(IUItem.iuingot,1,2),
//                new ItemStack(ItemRegistry.apalachiaGem),
//                new ItemStack(IUItem.iuingot,1,13),
//                new ItemStack(IUItem.iuingot,1,5),
//                new ItemStack(DivinerpgIntegration.capacitor_apalachia),
//                (short) 3000, true
//        );
//
//        add(
//                new ItemStack(IUItem.energiumDust.getItem(),1,24),
//                new ItemStack(IUItem.iuingot,1,2),
//                new ItemStack(ItemRegistry.skythernGem),
//                new ItemStack(IUItem.iuingot,1,13),
//                new ItemStack(IUItem.iuingot,1,5),
//                new ItemStack(DivinerpgIntegration.capacitor_skythern),
//                (short) 4000, true
//        );
//
//        add(
//                new ItemStack(IUItem.energiumDust.getItem(),1,24),
//                new ItemStack(IUItem.iuingot,1,2),
//                new ItemStack(ItemRegistry.mortumGem),
//                new ItemStack(IUItem.iuingot,1,13),
//                new ItemStack(IUItem.iuingot,1,5),
//                new ItemStack(DivinerpgIntegration.capacitor_mortum),
//                (short) 4000, true
//        );
//
//        add(
//                new ItemStack(IUItem.energiumDust.getItem(),1,24),
//                new ItemStack(IUItem.iuingot,1,2),
//                new ItemStack(ItemRegistry.collectorFragments),
//                new ItemStack(IUItem.iuingot,1,13),
//                new ItemStack(IUItem.iuingot,1,5),
//                new ItemStack(DivinerpgIntegration.capacitor_arcana),
//                (short) 5000, true
//        );
//
//
//    }

    public static void DIVCompressorRecipe() {
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_shadowphotonium), 9, new ItemStack(DivinerpgIntegration.plate_shadowphotoniumdense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_moltendemon), 9, new ItemStack(DivinerpgIntegration.plate_moltendemondense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_hades), 9, new ItemStack(DivinerpgIntegration.plate_hadesdense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_harmonite), 9, new ItemStack(DivinerpgIntegration.plate_harmonitedense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_nucleoarlemite), 9, new ItemStack(DivinerpgIntegration.plate_nucleoarlemitedense));
        CompressorRecipe.addcompressor(new ItemStack(DivinerpgIntegration.plate_celestial), 9, new ItemStack(DivinerpgIntegration.plate_celestialdense));

    }

    public static void DIVRollingRecipe() {

        MetalFormerRecipe.addmolot("ingotShadowPhotonium", new ItemStack(DivinerpgIntegration.plate_shadowphotonium));
        MetalFormerRecipe.addmolot("ingotDemonic", new ItemStack(DivinerpgIntegration.plate_moltendemon));
        MetalFormerRecipe.addmolot("ingotHades", new ItemStack(DivinerpgIntegration.plate_hades));
        MetalFormerRecipe.addmolot("ingotHarmonite", new ItemStack(DivinerpgIntegration.plate_harmonite));
        MetalFormerRecipe.addmolot("ingotNucleoArlemite", new ItemStack(DivinerpgIntegration.plate_nucleoarlemite));

    }

}
