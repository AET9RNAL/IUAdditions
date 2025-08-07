package com.aeternal.iuadditions.spectralconverters.recipes;

import com.aeternal.iuadditions.Constants;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public final class SRecipes {


    public static ResourceLocation recipeSpectralConverter;

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
        IForgeRegistry<IRecipe> r = evt.getRegistry();
    }
    public static void init() {
        recipeSpectralConverter = path("spectralconverter");
    }


    private static ResourceLocation path(String path) {
        return new ResourceLocation(Constants.MOD_ID, path);
    }

    private static List<ResourceLocation> allOfGroup(ResourceLocation group) {
        String jsonGroup = group.toString();

        return ForgeRegistries.RECIPES.getEntries().stream()
                .filter(e -> jsonGroup.equals(e.getValue().getGroup()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
