//---This code is a part of the original IU(Industrial Upgrade) code: https://github.com/ZelGimi/industrialupgrade. Project: https://www.curseforge.com/minecraft/mc-mods/industrial-upgrade.
//---The rights to this code belong to their original authors.---///
//---The usage and modification of it are a subject to the license of the original souce code and discretion of it's author.---///

package com.aeternal.integration.divinerpg;

import com.aeternal.integration.divinerpg.block.BlockDivineSolarPanel;
import com.aeternal.items.IUAItemBase;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.item.Item;

public class DivinerpgIntegration {

    public static BlockTileEntity blockDivineSolarPanel;

    public static Item ingot_shadowphotonium;
    public static Item ingot_moltendemon;
    public static Item ingot_celestial;
    public static Item ingot_hades;
    public static Item ingot_harmonite;
    public static Item ingot_nucleoarlemite;
    public static Item ingot_voidweave;
    public static Item plate_shadowphotonium;
    public static Item plate_moltendemon;
    public static Item plate_celestial;
    public static Item plate_hades;
    public static Item plate_harmonite;
    public static Item plate_nucleoarlemite;
    public static Item plate_voidweave;
    public static Item plate_shadowphotoniumdense;
    public static Item plate_moltendemondense;
    public static Item plate_celestialdense;
    public static Item plate_hadesdense;
    public static Item plate_harmonitedense;
    public static Item plate_nucleoarlemitedense;
    public static Item plate_voidweavedense;



    public static void init() {
        blockDivineSolarPanel = TileBlockCreator.instance.create(BlockDivineSolarPanel.class);

        ingot_shadowphotonium = new IUAItemBase("ingot_shadowphotonium");
        ingot_moltendemon = new IUAItemBase("ingot_moltendemon");
        ingot_celestial = new IUAItemBase("ingot_celestial");
        ingot_hades = new IUAItemBase("ingot_hades");
        ingot_harmonite = new IUAItemBase("ingot_harmonite");
        ingot_nucleoarlemite = new IUAItemBase("ingot_nucleoarlemite");
        ingot_voidweave = new IUAItemBase("ingot_voidweave");
        plate_shadowphotonium = new IUAItemBase("plate_shadowphotonium");
        plate_moltendemon = new IUAItemBase("plate_moltendemon");
        plate_celestial = new IUAItemBase("plate_celestial");
        plate_hades = new IUAItemBase("plate_hades");
        plate_harmonite = new IUAItemBase("plate_harmonite");
        plate_nucleoarlemite = new IUAItemBase("plate_nucleoarlemite");
        plate_voidweave = new IUAItemBase("plate_voidweave");
        plate_shadowphotoniumdense = new IUAItemBase("plate_shadowphotoniumdense");
        plate_moltendemondense = new IUAItemBase("plate_moltendemondense");
        plate_celestialdense = new IUAItemBase("plate_celestialdense");
        plate_hadesdense = new IUAItemBase("plate_hadesdense");
        plate_harmonitedense = new IUAItemBase("plate_harmonitedense");
        plate_nucleoarlemitedense = new IUAItemBase("plate_nucleoarlemitedense");
        plate_voidweavedense = new IUAItemBase("plate_voidweavedense");


    }
}




/*public static void addcompressor(ItemStack input, ItemStack output) {

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


}*/

