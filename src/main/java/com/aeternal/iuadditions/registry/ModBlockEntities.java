package com.aeternal.iuadditions.registry;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IUAdditions.MOD_ID);

    // === Example ===
    // public static final RegistryObject<BlockEntityType<ExampleBlockEntity>> EXAMPLE_BE =
    //         BLOCK_ENTITIES.register("example_be",
    //                 () -> BlockEntityType.Builder.of(ExampleBlockEntity::new,
    //                         ModBlocks.EXAMPLE_BLOCK.get()).build(null));
}
