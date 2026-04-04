package com.aeternal.iuadditions.registry;

import com.aeternal.iuadditions.IUAdditions;
import com.aeternal.iuadditions.api.annotation.AutoRegisterBlock;
import com.aeternal.iuadditions.api.annotation.AutoRegisterBlockEntity;
import com.aeternal.iuadditions.api.annotation.AutoRegisterItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationRegistryProcessor {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            IUAdditions.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            IUAdditions.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, IUAdditions.MOD_ID);

    private static void validateClass(Class<?> clazz, Class<?> expectedSuperClass) {
        if (!expectedSuperClass.isAssignableFrom(clazz)) {
            throw new RuntimeException(
                    "Auto-registered class " + clazz.getName() + " must extend " + expectedSuperClass.getSimpleName());
        }
        try {
            clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(
                    "Auto-registered class " + clazz.getName() + " is missing a zero-argument constructor.");
        }
    }

    @SuppressWarnings("unchecked")
    public static void init(IEventBus bus) {
        Type itemType = Type.getType(AutoRegisterItem.class);
        Type blockType = Type.getType(AutoRegisterBlock.class);
        Type beType = Type.getType(AutoRegisterBlockEntity.class);

        ModList.get().getAllScanData().stream()
                .flatMap(scanData -> scanData.getAnnotations().stream())
                .forEach(annotationData -> {
                    try {
                        if (annotationData.annotationType().equals(itemType)) {
                            Class<?> clazz = Class.forName(annotationData.clazz().getClassName());
                            validateClass(clazz, Item.class);
                            String name = (String) annotationData.annotationData().get("value");
                            ITEMS.register(name, () -> {
                                try {
                                    return (Item) clazz.getDeclaredConstructor().newInstance();
                                } catch (Exception e) {
                                    throw new RuntimeException("Failed to instantiate item " + name, e);
                                }
                            });
                        } else if (annotationData.annotationType().equals(blockType)) {
                            Class<?> clazz = Class.forName(annotationData.clazz().getClassName());
                            validateClass(clazz, Block.class);
                            String name = (String) annotationData.annotationData().get("value");
                            Boolean hasItem = (Boolean) annotationData.annotationData().get("hasItem");
                            if (hasItem == null)
                                hasItem = true; // default

                            RegistryObject<Block> blockReg = BLOCKS.register(name, () -> {
                                try {
                                    return (Block) clazz.getDeclaredConstructor().newInstance();
                                } catch (Exception e) {
                                    throw new RuntimeException("Failed to instantiate block " + name, e);
                                }
                            });

                            if (hasItem) {
                                ITEMS.register(name, () -> {
                                    Block blockInst = blockReg.get();
                                    Item.Properties props = new Item.Properties();
                                    try {
                                        Method method = clazz.getMethod("getItemProperties");
                                        props = (Item.Properties) method.invoke(blockInst);
                                    } catch (NoSuchMethodException e) {
                                        props.tab(ModCreativeTabs.MOD_TAB); // Fallback
                                    } catch (Exception e) {
                                        IUAdditions.LOGGER
                                                .error("Error calling getItemProperties on " + clazz.getName(), e);
                                        props.tab(ModCreativeTabs.MOD_TAB);
                                    }
                                    return new BlockItem(blockInst, props);
                                });
                            }
                        } else if (annotationData.annotationType().equals(beType)) {
                            Class<?> clazz = Class.forName(annotationData.clazz().getClassName());
                            validateClass(clazz, BlockEntity.class);
                            String name = (String) annotationData.annotationData().get("value");
                            List<Type> validBlockTypes = (List<Type>) annotationData.annotationData()
                                    .get("validBlocks");

                            BLOCK_ENTITIES.register(name, () -> {
                                try {
                                    List<Block> validBlocks = new ArrayList<>();
                                    for (Type type : validBlockTypes) {
                                        Class<?> blockClass = Class.forName(type.getClassName());
                                        for (RegistryObject<Block> ro : BLOCKS.getEntries()) {
                                            if (blockClass.isInstance(ro.get())) {
                                                validBlocks.add(ro.get());
                                            }
                                        }
                                    }

                                    BlockEntityType.BlockEntitySupplier<BlockEntity> supplier = (pos, state) -> {
                                        try {
                                            return (BlockEntity) clazz
                                                    .getDeclaredConstructor(net.minecraft.core.BlockPos.class,
                                                            net.minecraft.world.level.block.state.BlockState.class)
                                                    .newInstance(pos, state);
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    };

                                    return BlockEntityType.Builder.of(supplier, validBlocks.toArray(new Block[0]))
                                            .build(null);
                                } catch (Exception e) {
                                    throw new RuntimeException("Failed to register BlockEntity " + name, e);
                                }
                            });
                        }
                    } catch (Exception e) {
                        IUAdditions.LOGGER
                                .error("Failed to process annotation for " + annotationData.clazz().getClassName(), e);
                    }
                });

        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }
}
