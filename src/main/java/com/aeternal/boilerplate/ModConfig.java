package com.aeternal.boilerplate;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

/**
 * Pattern:
 *   1. Define ForgeConfigSpec.XxxValue fields (private)
 *   2. Build the SPEC
 *   3. Expose resolved values as public static fields
 *   4. Resolve in @SubscribeEvent onLoad
 *
 * For integration toggles (replacing Config.DraconicConfirmed etc.),
 * define boolean configs in the "integrations" category.
 */
@Mod.EventBusSubscriber(modid = Boilerplate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // === General Settings ===
    // private static final ForgeConfigSpec.BooleanValue EXAMPLE_TOGGLE = BUILDER
    //         .comment("Example toggle")
    //         .define("general.exampleToggle", true);

    // === Integration Toggles ===
    // Replace the old Config.DraconicConfirmed, Config.BotaniaConfirmed, etc.
    // BUILDER.push("integrations");
    // private static final ForgeConfigSpec.BooleanValue FORESTRY_ENABLED = BUILDER
    //         .comment("Enable Forestry integration (requires Forestry to be installed)")
    //         .define("forestryEnabled", true);
    // BUILDER.pop();

    static final ForgeConfigSpec SPEC = BUILDER.build();

    // === Resolved values (populated after config loads) ===
    // public static boolean exampleToggle;
    // public static boolean forestryEnabled;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        // Resolve config values
        // exampleToggle = EXAMPLE_TOGGLE.get();
        // forestryEnabled = FORESTRY_ENABLED.get();

        // Update Constants confirmation flags
        // Constants.FORESTRY_CONFIRM = forestryEnabled;
    }
}
