package com.aeternal.boilerplate.registry;

import com.aeternal.boilerplate.Boilerplate;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Use IForgeMenuType.regular() for server-only menus
 * or IContainerFactory for menus needing extra data.
 */
public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Boilerplate.MOD_ID);

    // === Example ===
    // public static final RegistryObject<MenuType<ExampleMenu>> EXAMPLE_MENU =
    //         MENUS.register("example_menu",
    //                 () -> IForgeMenuType.regular(ExampleMenu::new));
}
