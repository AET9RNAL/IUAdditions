package com.aeternal.iuadditions.registry;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IUAdditions.MOD_ID);

    // === Example ===
    // public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
    //         () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
    //                 .strength(3.0f, 3.0f)
    //                 .requiresCorrectToolForDrops()));
}
