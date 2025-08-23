package com.aeternal.iuadditions.items.modules.data;

import net.minecraft.util.text.TextFormatting;

import java.util.HashMap;
import java.util.Map;

/**
 * Declarative map of "extra" cases for UpgradeItemInform#getName().
 * Add entries here to append new cases without touching the original mod.
 *
 * Each entry binds:
 *  - caseName: exact EnumInfoUpgradeModules constant name
 *  - color: TextFormatting to prefix the localized label
 *  - locKey: Localization key passed to com.denfop.Localization.translate(locKey)
 *  - render: how to render numeric suffix based on 'number' field (PLAIN/PERCENT/CUSTOM)
 *  - mul/scale/offset: simple knobs to produce percent-like outputs without per-case code
 *
 * Examples below are placeholders; replace with your actual constants and keys.
 */
public enum ExtraInfoCases {
    ENERGY_SHIELD("ENERGY_SHIELD", TextFormatting.GOLD, "iua.specialupgrade.energy_shield", Render.PLAIN, 0.05D, 100D, 0D),

    ;

    public enum Render {
        /** No numeric suffix; just color + localized text. */
        PLAIN,
        /** Compute (number * mul * scale + offset) and append as " XX%". */
        PERCENT,
        /** Compute (number * mul + offset) and append as " +X" (generic numeric). */
        CUSTOM
    }

    private final String caseName;
    private final TextFormatting color;
    private final String locKey;
    private final Render render;
    private final double mul;
    private final double scale;
    private final double offset;

    ExtraInfoCases(String caseName,
                   TextFormatting color,
                   String locKey,
                   Render render,
                   double mul,
                   double scale,
                   double offset) {
        this.caseName = caseName;
        this.color = color;
        this.locKey = locKey;
        this.render = render;
        this.mul = mul;
        this.scale = scale;
        this.offset = offset;
    }

    public String getCaseName() { return caseName; }
    public TextFormatting getColor() { return color; }
    public String getLocKey() { return locKey; }

    /**
     * Builds a numeric suffix using the configured render strategy.
     * @param number the UpgradeItemInform.number value
     * @return e.g., " 25%" or " +3" or "" depending on render
     */
    public String renderSuffix(int number) {
        if (render == Render.PLAIN) return "";
        double v = (number * mul) + offset;
        if (render == Render.PERCENT) {
            double pct = v * scale; // typical: scale=100 to show "%"
            // Trim trailing .0 for neatness
            String s = (Math.floor(pct) == pct) ? String.valueOf((long) pct) : String.valueOf(pct);
            return " " + s + "%";
        }
        // CUSTOM: generic "+X"
        // Trim trailing .0 for neatness
        String s = (Math.floor(v) == v) ? String.valueOf((long) v) : String.valueOf(v);
        return " +" + s;
    }

    // ------- lookup -------
    private static final Map<String, ExtraInfoCases> BY_NAME = new HashMap<>();
    static {
        for (ExtraInfoCases e : values()) {
            BY_NAME.put(e.caseName, e);
        }
    }

    /**
     * @param enumConstantName the EnumInfoUpgradeModules constant name (upgrade.name())
     */
    public static ExtraInfoCases byCase(String enumConstantName) {
        return BY_NAME.get(enumConstantName);
    }
}
