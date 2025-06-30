package com.aeternal.integration.divinerpg;

import com.aeternal.integration.divinerpg.block.BlockDivineSolarPanel;
import com.aeternal.items.IUAItemBase;
import com.denfop.blocks.BlockTileEntity;
import com.denfop.blocks.TileBlockCreator;
import net.minecraft.item.Item;

public class DivinerpgIntegration {

    public static BlockTileEntity blockDivineSolarPanel;
    public static Item core_eden;
    public static Item core_wildwood;
    public static Item core_apalachia;
    public static Item core_skythern;
    public static Item core_mortum;
    public static Item core_arcana;
    public static Item core_vethea;
    public static Item core_crossdimensional;
    public static Item circuit_eden;
    public static Item circuit_wildwood;
    public static Item circuit_apalachia;
    public static Item circuit_skythern;
    public static Item circuit_mortum;
    public static Item circuit_arcana;
    public static Item qfp_eden;
    public static Item qfp_wildwood;
    public static Item qfp_apalachia;
    public static Item qfp_skythern;
    public static Item qfp_mortum;
    public static Item qfp_arcana;
    public static Item soic_eden;
    public static Item soic_wildwood;
    public static Item soic_apalachia;
    public static Item soic_skythern;
    public static Item soic_mortum;
    public static Item soic_arcana;
    public static Item capacitor_eden;
    public static Item capacitor_wildwood;
    public static Item capacitor_apalachia;
    public static Item capacitor_skythern;
    public static Item capacitor_mortum;
    public static Item capacitor_arcana;
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

        core_eden = new IUAItemBase("core_eden");
        core_wildwood = new IUAItemBase("core_wildwood");
        core_apalachia = new IUAItemBase("core_apalachia");
        core_skythern = new IUAItemBase("core_skythern");
        core_mortum = new IUAItemBase("core_mortum");
        core_arcana = new IUAItemBase("core_arcana");
        core_vethea = new IUAItemBase("core_vethea");
        core_crossdimensional = new IUAItemBase("core_crossdimensional");
        circuit_eden = new IUAItemBase("circuit_eden");
        circuit_wildwood = new IUAItemBase("circuit_wildwood");
        circuit_apalachia = new IUAItemBase("circuit_apalachia");
        circuit_skythern = new IUAItemBase("circuit_skythern");
        circuit_mortum = new IUAItemBase("circuit_mortum");
        circuit_arcana = new IUAItemBase("circuit_arcana");
        qfp_eden = new IUAItemBase("qfp_eden");
        qfp_wildwood = new IUAItemBase("qfp_wildwood");
        qfp_apalachia = new IUAItemBase("qfp_apalachia");
        qfp_skythern = new IUAItemBase("qfp_skythern");
        qfp_mortum = new IUAItemBase("qfp_mortum");
        qfp_arcana = new IUAItemBase("qfp_arcana");
        soic_eden = new IUAItemBase("soic_eden");
        soic_wildwood = new IUAItemBase("soic_wildwood");
        soic_apalachia = new IUAItemBase("soic_apalachia");
        soic_skythern = new IUAItemBase("soic_skythern");
        soic_mortum = new IUAItemBase("soic_mortum");
        soic_arcana = new IUAItemBase("soic_arcana");
        capacitor_eden = new IUAItemBase("capacitor_eden");
        capacitor_wildwood = new IUAItemBase("capacitor_wildwood");
        capacitor_apalachia = new IUAItemBase("capacitor_apalachia");
        capacitor_skythern = new IUAItemBase("capacitor_skythern");
        capacitor_mortum = new IUAItemBase("capacitor_mortum");
        capacitor_arcana = new IUAItemBase("capacitor_arcana");
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

