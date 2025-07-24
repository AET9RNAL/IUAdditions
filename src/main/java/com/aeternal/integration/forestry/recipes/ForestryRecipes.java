package com.aeternal.integration.forestry.recipes;

import binnie.extrabees.modules.ModuleCore;
import cofh.thermalexpansion.util.managers.machine.TransposerManager;
import binnie.extrabees.items.ItemHoneyComb;
import com.aeternal.Constants;
import com.aeternal.Core;
import com.aeternal.IUAItem;
import com.aeternal.api.recipe.RecipeHelper;
import com.aeternal.integration.forestry.items.HoneyCrystal;
import com.aeternal.integration.forestry.items.HoneyGlass;
import com.aeternal.integration.forestry.items.HoneyPlate;
import com.brandon3055.draconicevolution.lib.RecipeManager;
import com.denfop.IUItem;
import com.denfop.api.Recipes;
import com.denfop.tiles.mechanism.TileEntityFluidIntegrator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;

import java.math.BigInteger;

import static com.denfop.recipes.CompressorRecipe.addcompressor;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class ForestryRecipes {

    private static final Fluid FORESTRY_HONEY = FluidRegistry.getFluid("for.honey");
    private static final Fluid IU_HONEY = FluidRegistry.getFluid("iufluidhoney");
    private static final Fluid WATER = FluidRegistry.WATER;

    private static final ItemStack EXOTIC_MATERIAL = new ItemStack(IUItem.crafting_elements, 1, 434);
    private static final ItemStack COM_IRIDIUM_PLATE = new ItemStack(IUItem.compressIridiumplate, 1);

    public static void init() {
        FOCompressorRecipe();
        FOFluidIntegrator();
        if (Constants.TE_LOADED) {
            FOFluidTransposer();
        }
        FOShapedRecipes();
        FOAlloySmelter();
        FOPerfectAlloySmelter();
        if (Constants.DE_LOADED) {
            FODEInfusion();
        } else FORefractiveGlassAlternative();
    }

    public static void FOCompressorRecipe() {

        addcompressor(ItemHoneyComb.VanillaComb.SIMMERING.get(), 32, new ItemStack(IUAItem.compressedHoneycomb, 1, 0));
        addcompressor(new ItemStack(ModuleCore.comb, 32, 82), new ItemStack(IUAItem.compressedHoneycomb, 1, 0));
        addcompressor(new ItemStack(ModuleCore.comb, 32, 21), new ItemStack(IUAItem.compressedHoneycomb, 1, 1));
        addcompressor(new ItemStack(ModuleCore.comb, 32, 28), new ItemStack(IUAItem.compressedHoneycomb, 1, 2));
        addcompressor(new ItemStack(ModuleCore.comb, 32, 81), new ItemStack(IUAItem.compressedHoneycomb, 1, 3));
        addcompressor(new ItemStack(ModuleCore.comb, 32, 14), new ItemStack(IUAItem.compressedHoneycomb, 1, 4));
        addcompressor(ItemHoneyComb.VanillaComb.DRIPPING.get(), 32, new ItemStack(IUAItem.compressedHoneycomb, 1, 5));

    }

    public static void FOFluidIntegrator() {

        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 0), new ItemStack(IUAItem.honeyCrystal, 1, 0), new FluidStack(FORESTRY_HONEY, 2500), new FluidStack(WATER, 2500));
        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 0), new ItemStack(IUAItem.honeyCrystal, 1, 0), new FluidStack(IU_HONEY      , 2500), new FluidStack(WATER, 2500));

        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 1), new ItemStack(IUAItem.honeyCrystal, 1, 1), new FluidStack(FORESTRY_HONEY, 2500), new FluidStack(WATER, 2500));
        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 1), new ItemStack(IUAItem.honeyCrystal, 1, 1), new FluidStack(IU_HONEY      , 2500), new FluidStack(WATER, 2500));

        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 2), new ItemStack(IUAItem.honeyCrystal, 1, 2), new FluidStack(FORESTRY_HONEY, 2500), new FluidStack(WATER, 2500));
        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 2), new ItemStack(IUAItem.honeyCrystal, 1, 2), new FluidStack(IU_HONEY      , 2500), new FluidStack(WATER, 2500));

        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 3), new ItemStack(IUAItem.honeyCrystal, 1, 3), new FluidStack(FORESTRY_HONEY, 2500), new FluidStack(WATER, 2500));
        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 3), new ItemStack(IUAItem.honeyCrystal, 1, 3), new FluidStack(IU_HONEY      , 2500), new FluidStack(WATER, 2500));

        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 4), new ItemStack(IUAItem.honeyCrystal, 1, 4), new FluidStack(FORESTRY_HONEY, 2500), new FluidStack(WATER, 2500));
        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 4), new ItemStack(IUAItem.honeyCrystal, 1, 4), new FluidStack(IU_HONEY      , 2500), new FluidStack(WATER, 2500));

        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 5), new ItemStack(IUAItem.honeyCrystal, 1, 5), new FluidStack(FORESTRY_HONEY, 2500), new FluidStack(WATER, 2500));
        TileEntityFluidIntegrator.addRecipe(new ItemStack(IUAItem.compressedHoneycomb, 1, 5), new ItemStack(IUAItem.honeyCrystal, 1, 5), new FluidStack(IU_HONEY      , 2500), new FluidStack(WATER, 2500));
    }

    public static void FOFluidTransposer() {
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 0), new ItemStack(IUAItem.honeyCrystal, 1, 0), new FluidStack(FORESTRY_HONEY, 2500), false);
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 0), new ItemStack(IUAItem.honeyCrystal, 1, 0), new FluidStack(IU_HONEY      , 2500), false);

        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 1), new ItemStack(IUAItem.honeyCrystal, 1, 1), new FluidStack(FORESTRY_HONEY, 2500), false);
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 1), new ItemStack(IUAItem.honeyCrystal, 1, 1), new FluidStack(IU_HONEY      , 2500), false);

        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 2), new ItemStack(IUAItem.honeyCrystal, 1, 2), new FluidStack(FORESTRY_HONEY, 2500), false);
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 2), new ItemStack(IUAItem.honeyCrystal, 1, 2), new FluidStack(IU_HONEY      , 2500), false);

        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 3), new ItemStack(IUAItem.honeyCrystal, 1, 3), new FluidStack(FORESTRY_HONEY, 2500), false);
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 3), new ItemStack(IUAItem.honeyCrystal, 1, 3), new FluidStack(IU_HONEY      , 2500), false);

        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 4), new ItemStack(IUAItem.honeyCrystal, 1, 4), new FluidStack(FORESTRY_HONEY, 2500), false);
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 4), new ItemStack(IUAItem.honeyCrystal, 1, 4), new FluidStack(IU_HONEY      , 2500), false);

        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 5), new ItemStack(IUAItem.honeyCrystal, 1, 5), new FluidStack(FORESTRY_HONEY, 2500), false);
        TransposerManager.addFillRecipe(800, new ItemStack(IUAItem.compressedHoneycomb, 1, 5), new ItemStack(IUAItem.honeyCrystal, 1, 5), new FluidStack(IU_HONEY      , 2500), false);
    }

    public static void FOShapedRecipes() {

        Recipes.recipe.addRecipe(
                new ItemStack(IUAItem.honeyPlate, 1, 0),
                "ABC",
                "BDB",
                "CBA",

                ('A'), EXOTIC_MATERIAL,
                ('B'), new ItemStack(IUAItem.honeyCrystal, 1, 0),
                ('C'), COM_IRIDIUM_PLATE,
                ('D'), forestry.core.ModuleCore.getItems().impregnatedCasing.getItemStack()
        );

        for (int i = 1; i < HoneyCrystal.HoneyCrystalTypes.getLength(); i++) {
            Recipes.recipe.addRecipe(
                    new ItemStack(IUAItem.honeyPlate, 1, i),
                    "ABC",
                    "BDB",
                    "CBA",

                    ('A'), EXOTIC_MATERIAL,
                    ('B'), new ItemStack(IUAItem.honeyCrystal, 1, i),
                    ('C'), COM_IRIDIUM_PLATE,
                    ('D'), new ItemStack(IUAItem.honeyPlate, 1, i - 1)
                    );
        }

    }

    public static void FOAlloySmelter() {
        for (int i = 0; i < (HoneyGlass.HoneyGlassTypes.getLength()); i++) {
            RecipeHelper.addAlloySmelter(new ItemStack(IUAItem.honeyPlate, 1, i), new ItemStack(IUItem.sunnarium, 1, 2), new ItemStack(IUAItem.honeyGlass, 1, i), 5000);
        }
    }

    public static void FOPerfectAlloySmelter() {
        RecipeHelper.addPerfectAlloySmelter(
                new ItemStack(IUAItem.honeyPlate, 1, HoneyPlate.HoneyPlateTypes.getLength() - 5),
                new ItemStack(IUAItem.honeyPlate, 1, HoneyPlate.HoneyPlateTypes.getLength() - 4),
                new ItemStack(IUAItem.honeyPlate, 1, HoneyPlate.HoneyPlateTypes.getLength() - 3),
                new ItemStack(IUAItem.honeyPlate, 1, HoneyPlate.HoneyPlateTypes.getLength() - 2),
                new ItemStack(IUAItem.honeyPlate, 1, HoneyPlate.HoneyPlateTypes.getLength() - 1),
                new ItemStack(IUAItem.refractive, 1, 0),
                5000
        );
    }

    public static void FODEInfusion() {
        ItemStack[] items = new ItemStack[]{
                new ItemStack(IUAItem.honeyGlass, 1, HoneyPlate.HoneyPlateTypes.getLength() - 6),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyPlate.HoneyPlateTypes.getLength() - 5),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyPlate.HoneyPlateTypes.getLength() - 4),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyPlate.HoneyPlateTypes.getLength() - 3),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyPlate.HoneyPlateTypes.getLength() - 2),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyPlate.HoneyPlateTypes.getLength() - 1)
        };
        RecipeHelper.addDEFusion(new ItemStack(IUAItem.refractive, 1, 1), new ItemStack(IUItem.photonglass, 1, 13), 15000000000L, 3, (Object[]) items);
    }

    public static void FORefractiveGlassAlternative() {
        RecipeHelper.addPerfectAlloySmelter(
                new ItemStack(IUAItem.honeyGlass, 1, HoneyGlass.HoneyGlassTypes.getLength() - 5),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyGlass.HoneyGlassTypes.getLength() - 4),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyGlass.HoneyGlassTypes.getLength() - 3),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyGlass.HoneyGlassTypes.getLength() - 2),
                new ItemStack(IUAItem.honeyGlass, 1, HoneyGlass.HoneyGlassTypes.getLength() - 1),
                new ItemStack(IUAItem.refractive, 1, 1),
                5000
        );
    }

}
