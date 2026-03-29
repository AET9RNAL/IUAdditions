package com.aeternal.boilerplate.registry;

import com.aeternal.boilerplate.Boilerplate;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Boilerplate.MOD_ID);

    // === Example ===
    // public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
    //         () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
    //                 .strength(3.0f, 3.0f)
    //                 .requiresCorrectToolForDrops()));
}
