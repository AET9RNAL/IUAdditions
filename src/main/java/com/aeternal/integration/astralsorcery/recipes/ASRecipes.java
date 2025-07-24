package com.aeternal.integration.astralsorcery.recipes;

import arcaios26.astraladditions.init.BlockInit;
import com.aeternal.Constants;
import com.aeternal.IUAItem;
import com.aeternal.api.recipe.RecipeHelper;
import com.denfop.IUItem;
import com.denfop.api.Recipes;
import com.denfop.recipes.CompressorRecipe;
import com.denfop.tiles.base.TileDoubleMolecular;
import com.denfop.tiles.mechanism.TileGenerationMicrochip;
import hellfirepvp.astralsorcery.common.item.useables.ItemShiftingStar;
import hellfirepvp.astralsorcery.common.lib.Constellations;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ASRecipes {

    private static final ItemStack STAR_METAL = new ItemStack(ItemsAS.craftingComponent, 1, 1);
    private static final ItemStack STAR_PLATE = new ItemStack(IUAItem.astralElements, 1, 0);
    private static final ItemStack STELLAR_IRIDIUM = new ItemStack(IUAItem.astralElements, 1, 1);
    private static final ItemStack REINFORCED_IRIDIUM = new ItemStack(IUAItem.astralElements, 1, 2);
    private static final ItemStack DENSE_REINFORCED_IRIDIUM = new ItemStack(IUAItem.astralElements, 1, 3);
    private static final ItemStack IRIDIUM_ADVANCED = new ItemStack(IUAItem.astralElements, 1, 4);
    private static final ItemStack STELLAR_CAPACITOR = new ItemStack(IUAItem.astralElements, 1, 5);
    private static final ItemStack STELLAR_SOIC = new ItemStack(IUAItem.astralElements, 1, 6);
    private static final ItemStack STELLAR_QFP = new ItemStack(IUAItem.astralElements, 1, 7);
    private static final ItemStack STELLAR_CIRCUIT = new ItemStack(IUAItem.astralElements, 1, 8);

    public static void init() {
        ASRolling();
        ASAdvMolecular();
        ASShapedRecipes();
        ASCompressor();
        ASCircuitManufacturer();
    }

    public static void ASRolling() {
        RecipeHelper.addRolling(STAR_METAL, STAR_PLATE);
    }

    public static void ASAdvMolecular() {
        TileDoubleMolecular.addrecipe(new ItemStack(IUItem.crafting_elements, 1, 275), RecipeHelper.setCount(STAR_METAL, 8), new ItemStack(IUAItem.astralElements, 1, 1), 5000000L);
        if (Constants.ASA_LOADED) {
            TileDoubleMolecular.addrecipe(new ItemStack(IUItem.crafting_elements, 1, 275), new ItemStack(BlockInit.BLOCK_STARMETAL, 1), new ItemStack(IUAItem.astralElements, 1, 1), 1000000L);
        }
    }

    public static void ASShapedRecipes() {

        Recipes.recipe.addRecipe(
                REINFORCED_IRIDIUM,
                "ABA",
                "BCB",
                "ABA",

                ('A'), STELLAR_IRIDIUM,
                ('B'), new ItemStack(IUItem.crafting_elements, 1, 274),
                ('C'), Items.DIAMOND
        );

        Recipes.recipe.addRecipe(
                STELLAR_SOIC,
                "AAA",
                "ABA",
                "AAA",

                ('A'), new ItemStack(IUItem.stik, 1, 31),
                ('B'), STELLAR_CAPACITOR
        );

        Recipes.recipe.addRecipe(
                STELLAR_QFP,
                "AAA",
                "BCB",
                "DDD",

                ('A'), new ItemStack(IUItem.crafting_elements, 1, 479),
                ('B'), STELLAR_CAPACITOR,
                ('C'), new ItemStack(IUItem.crafting_elements, 1, 549),
                ('D'), STAR_PLATE
        );

    }

    public static void ASCompressor() {
        CompressorRecipe.addcompressor(new ItemStack(REINFORCED_IRIDIUM.getItem(), 4, REINFORCED_IRIDIUM.getMetadata()), DENSE_REINFORCED_IRIDIUM);
        CompressorRecipe.addcompressor(new ItemStack(DENSE_REINFORCED_IRIDIUM.getItem(), 9, DENSE_REINFORCED_IRIDIUM.getMetadata()), IRIDIUM_ADVANCED);
    }

    public static void ASCircuitManufacturer() {
        ItemStack shiftingStar = new ItemStack(ItemsAS.shiftingStar, 1, 0);
        ItemShiftingStar.setAttunement(shiftingStar, Constellations.vicio);

        TileGenerationMicrochip.add(
                new ItemStack(IUItem.iuingot, 1, 9),
                new ItemStack(ItemsAS.useableDust, 1, 0),
                IUItem.energiumDust,
                STAR_METAL,
                shiftingStar,
                STELLAR_CAPACITOR,
                (short) 2000,
                true
        );
        TileGenerationMicrochip.add(
                new ItemStack(IUItem.photoniy_ingot, 1),
                new ItemStack(IUItem.iuingot, 1, 39),
                STELLAR_CAPACITOR,
                STELLAR_SOIC,
                STELLAR_QFP,
                STELLAR_CIRCUIT,
                (short) 5000,
                true
        );
    }


//    public static void InitConstellationRecipes(){
//        ItemStack excitednucleus = new ItemStack( IUItem.excitednucleus,1,4);
//        ItemStack coreSingularEx = new ItemStack( IUItem.excitednucleus,1,6);
//        ItemStack coreDiffractiveEx = new ItemStack( IUItem.excitednucleus,1,7);
//        Item coreStellar = (AstralSorceryIntegration.core_stellar);
//        Item iridiumReinforcedAS = (AstralSorceryIntegration.iridium_reinforced_stellar);
//        Item iridiumDenseAS = (AstralSorceryIntegration.iridium_dense_stellar);
//        Item iridiumAdvancedAS = (AstralSorceryIntegration.iridium_advanced_stellar);
//        Item glassInfused = (ItemsAS.infusedGlass);
//        Item shiftingStar = (ItemsAS.shiftingStar);
//        Item treeBeacon = Item.getItemFromBlock((BlocksAS.treeBeacon));
//        Item worldIlluminator = Item.getItemFromBlock((BlocksAS.blockIlluminator));
//        Item circuitStellar = (AstralSorceryIntegration.circuit_stellar);
//        Item celestialGateway = Item.getItemFromBlock((BlocksAS.celestialGateway));
//        ItemStack dustIllum = new ItemStack( ItemsAS.useableDust,1,0);
//        ItemStack dustNocturnal = new ItemStack( ItemsAS.useableDust,1,1);
//        ItemStack lensSpectral = new ItemStack( ItemsAS.coloredLens,1,6);
//        ItemStack brightSCPItem = new ItemStack( AstralSorceryIntegration.blockASSolarPanel,1, BlockAstralSolarPanel.brightscp_solar_panel.getId());
//        ItemStack dimSCPItem = new ItemStack( AstralSorceryIntegration.blockASSolarPanel,1, BlockAstralSolarPanel.dimscp_solar_panel.getId());
//        ItemStack faintSCPItem = new ItemStack( AstralSorceryIntegration.blockASSolarPanel,1, BlockAstralSolarPanel.faintscp_solar_panel.getId());

//        brightSCP = registerConstellationRecipe(newShapedRecipe("internal/altar/brightSCP", AstralSorceryIntegration.blockASSolarPanel)
//                .addPart(excitednucleus,
//                        ShapedRecipeSlot.CENTER)
//                .addPart(coreStellar,
//                        ShapedRecipeSlot.LEFT,
//                        ShapedRecipeSlot.RIGHT)
//                .addPart(glassInfused,
//                        ShapedRecipeSlot.UPPER_CENTER)
//                .addPart(celestialGateway,
//                        ShapedRecipeSlot.LOWER_CENTER)
//                .addPart(iridiumReinforcedAS,
//                        ShapedRecipeSlot.UPPER_LEFT,
//                        ShapedRecipeSlot.UPPER_RIGHT,
//                        ShapedRecipeSlot.LOWER_LEFT,
//                        ShapedRecipeSlot.LOWER_RIGHT)
//                .unregisteredAccessibleShapedRecipe());
//        brightSCP.setCstItem(lensSpectral,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_LEFT_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_RIGHT_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_LEFT_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_RIGHT_RIGHT);
//        brightSCP.setCstItem(circuitStellar,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_RIGHT);
//
//        brightSCP.setAttItem(dustNocturnal,
//                AttunementRecipe.AttunementAltarSlot.UPPER_LEFT,
//                AttunementRecipe.AttunementAltarSlot.LOWER_RIGHT);
//        brightSCP.setAttItem(dustIllum,
//                AttunementRecipe.AttunementAltarSlot.UPPER_RIGHT,
//                AttunementRecipe.AttunementAltarSlot.LOWER_LEFT)

//                ;


//        dimSCP = registerTraitRecipe(newShapedRecipe("internal/altar/dimSCP", dimSCPItem)
//                .addPart(coreSingularEx,
//                        ShapedRecipeSlot.CENTER)
//                .addPart(circuitStellar,
//                        ShapedRecipeSlot.LEFT,
//                        ShapedRecipeSlot.RIGHT)
//                .addPart(treeBeacon,
//                        ShapedRecipeSlot.UPPER_CENTER)
//                .addPart(celestialGateway,
//                        ShapedRecipeSlot.LOWER_CENTER)
//                .addPart(glassInfused,
//                        ShapedRecipeSlot.UPPER_LEFT,
//                        ShapedRecipeSlot.UPPER_RIGHT)
//                .addPart(brightSCPItem,
//                        ShapedRecipeSlot.LOWER_LEFT,
//                        ShapedRecipeSlot.LOWER_RIGHT)
//                .unregisteredAccessibleShapedRecipe());
//        dimSCP.setCstItem(iridiumDenseAS,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_LEFT_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_RIGHT_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_LEFT_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_RIGHT_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_RIGHT);

//        dimSCP.setAttItem(iridiumDenseAS,
//                AttunementRecipe.AttunementAltarSlot.UPPER_LEFT,
//                AttunementRecipe.AttunementAltarSlot.LOWER_RIGHT,
//                AttunementRecipe.AttunementAltarSlot.UPPER_RIGHT,
//                AttunementRecipe.AttunementAltarSlot.LOWER_LEFT
//                );
//        dimSCP.setInnerTraitItem(circuitStellar,
//                TraitRecipe.TraitRecipeSlot.UPPER_CENTER);
//        dimSCP.setInnerTraitItem(coreStellar,
//                TraitRecipe.TraitRecipeSlot.LOWER_CENTER,
//                TraitRecipe.TraitRecipeSlot.LEFT_CENTER,
//                TraitRecipe.TraitRecipeSlot.RIGHT_CENTER);
//        for (int i = 0; i < 4; i++) {
//            dimSCP.addOuterTraitItem(dustNocturnal);
//        }
//        for (int i = 0; i < 2; i++) {
//            dimSCP.addOuterTraitItem(dustIllum);
//        }
//        for (int i = 0; i < 4; i++) {
//            dimSCP.addOuterTraitItem(worldIlluminator);
//        }
//        dimSCP.setPassiveStarlightRequirement(5500);
//        ;



//        faintSCP = registerTraitRecipe(newShapedRecipe("internal/altar/faintSCP", faintSCPItem)
//                .addPart(coreDiffractiveEx,
//                        ShapedRecipeSlot.CENTER)
//                .addPart(circuitStellar,
//                        ShapedRecipeSlot.LEFT,
//                        ShapedRecipeSlot.RIGHT)
//                .addPart(treeBeacon,
//                        ShapedRecipeSlot.UPPER_CENTER)
//                .addPart(celestialGateway,
//                        ShapedRecipeSlot.LOWER_CENTER)
//                .addPart(glassInfused,
//                        ShapedRecipeSlot.UPPER_LEFT,
//                        ShapedRecipeSlot.UPPER_RIGHT)
//                .addPart(dimSCPItem,
//                        ShapedRecipeSlot.LOWER_LEFT,
//                        ShapedRecipeSlot.LOWER_RIGHT)
//                .unregisteredAccessibleShapedRecipe());
//        faintSCP.setCstItem(iridiumAdvancedAS,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_LEFT_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_RIGHT_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_LEFT_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_RIGHT_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_RIGHT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_LEFT,
//                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_RIGHT);

//        faintSCP.setAttItem(iridiumAdvancedAS,
//                AttunementRecipe.AttunementAltarSlot.UPPER_LEFT,
//                AttunementRecipe.AttunementAltarSlot.LOWER_RIGHT,
//                AttunementRecipe.AttunementAltarSlot.UPPER_RIGHT,
//                AttunementRecipe.AttunementAltarSlot.LOWER_LEFT
//        );
//        faintSCP.setInnerTraitItem(circuitStellar,
//                TraitRecipe.TraitRecipeSlot.UPPER_CENTER);
//        faintSCP.setInnerTraitItem(coreStellar,
//                TraitRecipe.TraitRecipeSlot.LOWER_CENTER,
//                TraitRecipe.TraitRecipeSlot.LEFT_CENTER,
//                TraitRecipe.TraitRecipeSlot.RIGHT_CENTER);
//        for (int i = 0; i < 4; i++) {
//            faintSCP.addOuterTraitItem(shiftingStar);
//        }
//        for (int i = 0; i < 2; i++) {
//            faintSCP.addOuterTraitItem(dustIllum);
//        }
//        for (int i = 0; i < 4; i++) {
//            faintSCP.addOuterTraitItem(worldIlluminator);
//        }
//        faintSCP.setPassiveStarlightRequirement(5500);
//        ;
//    }

}
