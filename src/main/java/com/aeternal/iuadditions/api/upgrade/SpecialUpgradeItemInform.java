package com.aeternal.iuadditions.api.upgrade;

import com.aeternal.iuadditions.items.modules.data.EnumSpecialModules;
import com.denfop.Localization;
import net.minecraft.util.text.TextFormatting;

public class SpecialUpgradeItemInform {

    public final EnumSpecialModules upgrade;
    public final int number;

    public SpecialUpgradeItemInform(EnumSpecialModules modules, int number) {
        this.upgrade = modules;
        this.number = number;

    }

    public boolean matched(EnumSpecialModules modules) {
        return this.upgrade.name.equals(modules.name);
    }

    public int getInformation(EnumSpecialModules modules) {
        if (this.upgrade.name.equals(modules.name)) {
            return this.number;
        }
        return 0;
    }

    public String getName() {
        switch (this.upgrade) {
            case ENERGY_SHIELD:
                return TextFormatting.YELLOW + Localization.translate("energy_shield") + TextFormatting.GREEN + this.number;

        }
        return "";
    }


}
