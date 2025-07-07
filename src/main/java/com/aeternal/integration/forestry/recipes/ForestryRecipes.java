//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.forestry.recipes;

import binnie.extrabees.items.ItemHoneyComb;
import com.aeternal.integration.forestry.ForestryIntegration;
import com.denfop.IUItem;
import com.denfop.api.Recipes;
import net.minecraft.item.ItemStack;

import static com.aeternal.integration.forestry.ForestryIntegration.*;
import static com.denfop.recipes.CompressorRecipe.addcompressor;


public class ForestryRecipes {

   static ItemStack iridiumIndustrial = new ItemStack( IUItem.crafting_elements,1,285);
    static ItemStack enrichedSunnarium = new ItemStack( IUItem.sunnarium,1,1);
    public static void FOBaseRecipe() {
        Recipes.recipe.addRecipe(plate_shimmering,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_shimmering,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );

        Recipes.recipe.addRecipe(plate_radioactive,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_radioactive,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );

        Recipes.recipe.addRecipe(plate_venomous,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_venomous,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );

        Recipes.recipe.addRecipe(plate_certus,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_certus,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );

        Recipes.recipe.addRecipe(plate_static,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_static,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );

        Recipes.recipe.addRecipe(plate_dripping,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_dripping,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );

        /*Recipes.recipe.addRecipe(plate_refractive,
                "CIC",
                "ISI",
                "CIC",

                ('C'), crystal_dripping,
                ('I'), iridiumIndustrial,
                ('S'), enrichedSunnarium
        );*/
    }

    public static void FOCompressorRecipe() {

        addcompressor(ItemHoneyComb.VanillaComb.SIMMERING.get(), 32, new ItemStack(ForestryIntegration.compressed_shimmeringhoneycomb));
     //   addcompressor(String.valueOf(EnumHoneyComb.URANIUM), 32, ForestryIntegration.compressed_radioactivehoneycomb.getDefaultInstance());

    }





















}
