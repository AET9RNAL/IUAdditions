package com.aeternal.iuadditions.api.annotation.processor;

import com.aeternal.iuadditions.Constants;
import com.aeternal.iuadditions.Core;
import com.aeternal.iuadditions.api.annotation.AutoRegisterItem;
import com.aeternal.iuadditions.api.annotation.IntegrationOnly;
import com.aeternal.iuadditions.api.annotation.OreDict;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnnotationRegistryProcessor {

    private static final List<Class<?>> registeredClasses = new ArrayList<>();

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
    public static void init(ASMDataTable asmData) {
        Set<ASMDataTable.ASMData> itemAnnotations = asmData.getAll(AutoRegisterItem.class.getName());

        for (ASMDataTable.ASMData data : itemAnnotations) {
            String className = data.getClassName();
            Map<String, Object> annotationInfo = data.getAnnotationInfo();
            String registryName = (String) annotationInfo.get("value");

            try {
                Class<?> clazz = Class.forName(className);

                IntegrationOnly integrationOnly = clazz.getAnnotation(IntegrationOnly.class);
                if (integrationOnly != null) {
                    boolean allLoaded = true;
                    for (String modId : integrationOnly.value()) {
                        if (!AnnotationProcessor.isModLoaded(modId)) {
                            allLoaded = false;
                            break;
                        }
                    }
                    if (!allLoaded) {
                        Core.LOGGER.debug("Skipping auto-register for {} — required mods not loaded", className);
                        continue;
                    }
                }

                validateClass(clazz, Item.class);

                Item item = (Item) clazz.getDeclaredConstructor().newInstance();
                item.setRegistryName(new ResourceLocation(Constants.MOD_ID, registryName));
                ForgeRegistries.ITEMS.register(item);

                registeredClasses.add(clazz);

                Core.LOGGER.info("Auto-registered item: {} ({})", registryName, className);
            } catch (Exception e) {
                Core.LOGGER.error("Failed to auto-register item from class " + className, e);
            }
        }
    }

    public static void registerOreDict() {
        for (Class<?> clazz : registeredClasses) {
            for (Field field : clazz.getDeclaredFields()) {
                OreDict oreDict = field.getAnnotation(OreDict.class);
                if (oreDict == null) continue;

                try {
                    field.setAccessible(true);
                    Object value = field.get(null);
                    if (value instanceof Item) {
                        for (String oreName : oreDict.value()) {
                            OreDictionary.registerOre(oreName, new ItemStack((Item) value));
                        }
                    } else if (value instanceof ItemStack) {
                        for (String oreName : oreDict.value()) {
                            OreDictionary.registerOre(oreName, (ItemStack) value);
                        }
                    }
                } catch (Exception e) {
                    Core.LOGGER.error("Failed to register OreDict for field " + field.getName() + " in " + clazz.getName(), e);
                }
            }
        }
    }
}
