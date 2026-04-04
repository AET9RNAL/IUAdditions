package com.aeternal.iuadditions.registry;

import com.aeternal.iuadditions.IUAdditions;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Use IForgeMenuType.regular() for server-only menus
 * or IContainerFactory for menus needing extra data.
 */
public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, IUAdditions.MOD_ID);

    // === Example ===
    // public static final RegistryObject<MenuType<ExampleMenu>> EXAMPLE_MENU =
    //         MENUS.register("example_menu",
    //                 () -> IForgeMenuType.regular(ExampleMenu::new));
}
