//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.astralsorcery.recipes;

import com.aeternal.integration.astralsorcery.block.BlockAstralSolarPanel;
import com.aeternal.integration.astralsorcery.tile.AstralSorceryIntegration;
import com.denfop.IUItem;
import com.denfop.api.Recipes;
import com.denfop.api.recipe.BaseMachineRecipe;
import com.denfop.api.recipe.Input;
import com.denfop.api.recipe.RecipeOutput;
import com.denfop.recipe.IInputHandler;
import hellfirepvp.astralsorcery.common.crafting.altar.recipes.AttunementRecipe;
import hellfirepvp.astralsorcery.common.crafting.altar.recipes.ConstellationRecipe;
import hellfirepvp.astralsorcery.common.crafting.altar.recipes.TraitRecipe;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipeSlot;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static com.denfop.tiles.mechanism.TileGenerationMicrochip.add;
import static hellfirepvp.astralsorcery.common.crafting.altar.AltarRecipeRegistry.registerConstellationRecipe;
import static hellfirepvp.astralsorcery.common.crafting.altar.AltarRecipeRegistry.registerTraitRecipe;
import static hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipe.Builder.newShapedRecipe;

public class ASRecipes {
    public static ConstellationRecipe brightSCP;
    public static TraitRecipe dimSCP;
    public static TraitRecipe faintSCP;
    public static void ASBaseRecipe(){
        Recipes.recipe.addRecipe(AstralSorceryIntegration.soic_stellar,
                "SSS",
                       "SCS",
                       "SSS",

                ('S'), ("stickPlatinum"),
                ('C'), AstralSorceryIntegration.capacitor_stellar
        );
        Recipes.recipe.addRecipe(AstralSorceryIntegration.qfp_stellar,
                "CCC",
                "AQA",
                "III",

                ('C'), ("plateCarbon"),
                ('A'), AstralSorceryIntegration.capacitor_stellar,
                ('Q'), IUItem.cirsuitQuantum,
                ('I'), ("ingotAstralStarmetal")
        );
        Recipes.recipe.addRecipe(AstralSorceryIntegration.iridium_reinforced_stellar,
                "ACA",
                "CDC",
                "ACA",

                ('A'), AstralSorceryIntegration.iridium_stellar,
                ('C'), ("ore:plateAdvancedAlloy"),
                ('D'), Items.DIAMOND
        );
    }



    public static void InitConstellationRecipes(){
        ItemStack excitednucleus = new ItemStack( IUItem.excitednucleus,1,4);
        ItemStack coreSingularEx = new ItemStack( IUItem.excitednucleus,1,6);
        ItemStack coreDiffractiveEx = new ItemStack( IUItem.excitednucleus,1,7);
        Item coreStellar = (AstralSorceryIntegration.core_stellar);
        Item iridiumReinforcedAS = (AstralSorceryIntegration.iridium_reinforced_stellar);
        Item iridiumDenseAS = (AstralSorceryIntegration.iridium_dense_stellar);
        Item iridiumAdvancedAS = (AstralSorceryIntegration.iridium_advanced_stellar);
        Item glassInfused = (ItemsAS.infusedGlass);
        Item shiftingStar = (ItemsAS.shiftingStar);
        Item treeBeacon = Item.getItemFromBlock((BlocksAS.treeBeacon));
        Item worldIlluminator = Item.getItemFromBlock((BlocksAS.blockIlluminator));
        Item circuitStellar = (AstralSorceryIntegration.circuit_stellar);
        Item celestialGateway = Item.getItemFromBlock((BlocksAS.celestialGateway));
        ItemStack dustIllum = new ItemStack( ItemsAS.useableDust,1,0);
        ItemStack dustNocturnal = new ItemStack( ItemsAS.useableDust,1,1);
        ItemStack lensSpectral = new ItemStack( ItemsAS.coloredLens,1,6);
        ItemStack brightSCPItem = new ItemStack( AstralSorceryIntegration.blockASSolarPanel,1, BlockAstralSolarPanel.brightscp_solar_panel.getId());
        ItemStack dimSCPItem = new ItemStack( AstralSorceryIntegration.blockASSolarPanel,1, BlockAstralSolarPanel.dimscp_solar_panel.getId());
        ItemStack faintSCPItem = new ItemStack( AstralSorceryIntegration.blockASSolarPanel,1, BlockAstralSolarPanel.faintscp_solar_panel.getId());

        brightSCP = registerConstellationRecipe(newShapedRecipe("internal/altar/brightSCP", AstralSorceryIntegration.blockASSolarPanel)
                .addPart(excitednucleus,
                        ShapedRecipeSlot.CENTER)
                .addPart(coreStellar,
                        ShapedRecipeSlot.LEFT,
                        ShapedRecipeSlot.RIGHT)
                .addPart(glassInfused,
                        ShapedRecipeSlot.UPPER_CENTER)
                .addPart(celestialGateway,
                        ShapedRecipeSlot.LOWER_CENTER)
                .addPart(iridiumReinforcedAS,
                        ShapedRecipeSlot.UPPER_LEFT,
                        ShapedRecipeSlot.UPPER_RIGHT,
                        ShapedRecipeSlot.LOWER_LEFT,
                        ShapedRecipeSlot.LOWER_RIGHT)
                .unregisteredAccessibleShapedRecipe());
        brightSCP.setCstItem(lensSpectral,
                ConstellationRecipe.ConstellationAtlarSlot.UP_LEFT_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_RIGHT_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_LEFT_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_RIGHT_RIGHT);
        brightSCP.setCstItem(circuitStellar,
                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_RIGHT);

        brightSCP.setAttItem(dustNocturnal,
                AttunementRecipe.AttunementAltarSlot.UPPER_LEFT,
                AttunementRecipe.AttunementAltarSlot.LOWER_RIGHT);
        brightSCP.setAttItem(dustIllum,
                AttunementRecipe.AttunementAltarSlot.UPPER_RIGHT,
                AttunementRecipe.AttunementAltarSlot.LOWER_LEFT)

                ;


        dimSCP = registerTraitRecipe(newShapedRecipe("internal/altar/dimSCP", dimSCPItem)
                .addPart(coreSingularEx,
                        ShapedRecipeSlot.CENTER)
                .addPart(circuitStellar,
                        ShapedRecipeSlot.LEFT,
                        ShapedRecipeSlot.RIGHT)
                .addPart(treeBeacon,
                        ShapedRecipeSlot.UPPER_CENTER)
                .addPart(celestialGateway,
                        ShapedRecipeSlot.LOWER_CENTER)
                .addPart(glassInfused,
                        ShapedRecipeSlot.UPPER_LEFT,
                        ShapedRecipeSlot.UPPER_RIGHT)
                .addPart(brightSCPItem,
                        ShapedRecipeSlot.LOWER_LEFT,
                        ShapedRecipeSlot.LOWER_RIGHT)
                .unregisteredAccessibleShapedRecipe());
        dimSCP.setCstItem(iridiumDenseAS,
                ConstellationRecipe.ConstellationAtlarSlot.UP_LEFT_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_RIGHT_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_LEFT_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_RIGHT_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_RIGHT);

        dimSCP.setAttItem(iridiumDenseAS,
                AttunementRecipe.AttunementAltarSlot.UPPER_LEFT,
                AttunementRecipe.AttunementAltarSlot.LOWER_RIGHT,
                AttunementRecipe.AttunementAltarSlot.UPPER_RIGHT,
                AttunementRecipe.AttunementAltarSlot.LOWER_LEFT
                );
        dimSCP.setInnerTraitItem(circuitStellar,
                TraitRecipe.TraitRecipeSlot.UPPER_CENTER);
        dimSCP.setInnerTraitItem(coreStellar,
                TraitRecipe.TraitRecipeSlot.LOWER_CENTER,
                TraitRecipe.TraitRecipeSlot.LEFT_CENTER,
                TraitRecipe.TraitRecipeSlot.RIGHT_CENTER);
        for (int i = 0; i < 4; i++) {
            dimSCP.addOuterTraitItem(dustNocturnal);
        }
        for (int i = 0; i < 2; i++) {
            dimSCP.addOuterTraitItem(dustIllum);
        }
        for (int i = 0; i < 4; i++) {
            dimSCP.addOuterTraitItem(worldIlluminator);
        }
        dimSCP.setPassiveStarlightRequirement(5500);
        ;



        faintSCP = registerTraitRecipe(newShapedRecipe("internal/altar/faintSCP", faintSCPItem)
                .addPart(coreDiffractiveEx,
                        ShapedRecipeSlot.CENTER)
                .addPart(circuitStellar,
                        ShapedRecipeSlot.LEFT,
                        ShapedRecipeSlot.RIGHT)
                .addPart(treeBeacon,
                        ShapedRecipeSlot.UPPER_CENTER)
                .addPart(celestialGateway,
                        ShapedRecipeSlot.LOWER_CENTER)
                .addPart(glassInfused,
                        ShapedRecipeSlot.UPPER_LEFT,
                        ShapedRecipeSlot.UPPER_RIGHT)
                .addPart(dimSCPItem,
                        ShapedRecipeSlot.LOWER_LEFT,
                        ShapedRecipeSlot.LOWER_RIGHT)
                .unregisteredAccessibleShapedRecipe());
        faintSCP.setCstItem(iridiumAdvancedAS,
                ConstellationRecipe.ConstellationAtlarSlot.UP_LEFT_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_RIGHT_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_LEFT_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_RIGHT_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.UP_UP_RIGHT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_LEFT,
                ConstellationRecipe.ConstellationAtlarSlot.DOWN_DOWN_RIGHT);

        faintSCP.setAttItem(iridiumAdvancedAS,
                AttunementRecipe.AttunementAltarSlot.UPPER_LEFT,
                AttunementRecipe.AttunementAltarSlot.LOWER_RIGHT,
                AttunementRecipe.AttunementAltarSlot.UPPER_RIGHT,
                AttunementRecipe.AttunementAltarSlot.LOWER_LEFT
        );
        faintSCP.setInnerTraitItem(circuitStellar,
                TraitRecipe.TraitRecipeSlot.UPPER_CENTER);
        faintSCP.setInnerTraitItem(coreStellar,
                TraitRecipe.TraitRecipeSlot.LOWER_CENTER,
                TraitRecipe.TraitRecipeSlot.LEFT_CENTER,
                TraitRecipe.TraitRecipeSlot.RIGHT_CENTER);
        for (int i = 0; i < 4; i++) {
            faintSCP.addOuterTraitItem(shiftingStar);
        }
        for (int i = 0; i < 2; i++) {
            faintSCP.addOuterTraitItem(dustIllum);
        }
        for (int i = 0; i < 4; i++) {
            faintSCP.addOuterTraitItem(worldIlluminator);
        }
        faintSCP.setPassiveStarlightRequirement(5500);
        ;
    }



    public static void ASCompressorRecipe() {
        addcompressor(new ItemStack(AstralSorceryIntegration.iridium_reinforced_stellar), 4, new ItemStack(AstralSorceryIntegration.iridium_dense_stellar));
        addcompressor(new ItemStack(AstralSorceryIntegration.iridium_dense_stellar), 9, new ItemStack(AstralSorceryIntegration.iridium_advanced_stellar));

    }
    public static void MicrochipRecipe(){
        add(
                new ItemStack(IUItem.iuingot,1,2),
                new ItemStack(IUItem.iuingot,1,9),
                new ItemStack(AstralSorceryIntegration.capacitor_stellar),
                new ItemStack(AstralSorceryIntegration.soic_stellar),
                new ItemStack(AstralSorceryIntegration.qfp_stellar),
                new ItemStack(AstralSorceryIntegration.circuit_stellar),
                (short) 5000, true
        );
    }
    public static void ASDoubleMolecularRecipe(){
        com.denfop.tiles.base.TileDoubleMolecular.addrecipe(
                new ItemStack(IUItem.crafting_elements, 1,275),
                new ItemStack(ItemsAS.craftingComponent,8,1),
                new ItemStack(
                        AstralSorceryIntegration.iridium_stellar, 1
                ),
                5000000

        );
    }

    public static void addcompressor(ItemStack input, int n, ItemStack output) {
        input = input.copy();
        input.setCount(n);
        final IInputHandler input1 = Recipes.inputFactory;
        com.denfop.api.Recipes.recipes.addRecipe(
                "compressor",
                new BaseMachineRecipe(
                        new Input(
                                input1.getInput(input)
                        ),
                        new RecipeOutput(null, output)
                )
        );


    }

    public static void addcompressor(String input, int n, ItemStack output) {
        final IInputHandler input1 = Recipes.inputFactory;
        com.denfop.api.Recipes.recipes.addRecipe(
                "compressor",
                new BaseMachineRecipe(
                        new Input(
                                input1.getInput(input, n)
                        ),
                        new RecipeOutput(null, output)
                )
        );
    }

    public static void addcompressor(String input, int n, String output) {
        final IInputHandler input1 = Recipes.inputFactory;
        com.denfop.api.Recipes.recipes.addRecipe(
                "compressor",
                new BaseMachineRecipe(
                        new Input(
                                input1.getInput(input, n)
                        ),
                        new RecipeOutput(null, OreDictionary.getOres(output).get(0))
                )
        );
    }

    public static void addcompressor(ItemStack input, ItemStack output) {
        final IInputHandler input1 = Recipes.inputFactory;
        com.denfop.api.Recipes.recipes.addRecipe(
                "compressor",
                new BaseMachineRecipe(
                        new Input(
                                input1.getInput(input)
                        ),
                        new RecipeOutput(null, output)
                )
        );
    }


}
