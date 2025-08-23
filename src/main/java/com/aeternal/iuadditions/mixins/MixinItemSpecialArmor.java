package com.aeternal.iuadditions.mixins;

import com.brandon3055.draconicevolution.items.armor.ICustomArmor;
import com.denfop.ElectricItem;
import com.denfop.api.upgrade.UpgradeSystem;
import com.denfop.items.EnumInfoUpgradeModules;
import com.denfop.items.armour.special.EnumSubTypeArmor;
import com.denfop.items.armour.special.EnumTypeArmor;
import com.denfop.items.armour.special.ItemSpecialArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nonnull;

/**
 * Apply only when Draconic Evolution is present.
 */
@Mixin(value = ItemSpecialArmor.class, remap = false, priority = 1001)
public abstract class MixinItemSpecialArmor implements ICustomArmor {

    // ---- Shadows from ItemSpecialArmor ----
    @Shadow private EnumTypeArmor armor;
    @Shadow private EnumSubTypeArmor subTypeArmor;
    @Shadow public abstract double getMaxEnergy(final ItemStack stack);

    // =============================================================================================
    // ICustomArmor (DE)
    // =============================================================================================

    @Override
    public float getProtectionPoints(@Nonnull ItemStack stack) {
        EnumInfoUpgradeModules es = iuadd_energyShieldModule();
        if (es == null) return 0F; // module not injected yet / not present

        if (!UpgradeSystem.system.hasModules(es, stack)) {
            return 0F;
        }

        int installed = UpgradeSystem.system.getModules(es, stack).number;
        int allowed   = Math.max(1, es.max);
        int maxPts    = iuadd_cfgMaxShieldPoints(armor, subTypeArmor);

        double ratio  = Math.min(1D, Math.max(0D, installed / (double) allowed));
        int points    = (int) Math.floor(maxPts * ratio);
        return (float) Math.max(0, Math.min(points, maxPts));
    }

    @Unique
    private static EnumInfoUpgradeModules iuadd_energyShieldModule() {
        try {
            // Avoids getstatic; resolves after your enum injection runs
            return Enum.valueOf(EnumInfoUpgradeModules.class, "ENERGY_SHIELD");
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public float getRecoveryRate(@Nonnull ItemStack stack) {
        return (float) iuadd_cfgRecoveryRate(armor, subTypeArmor);
    }

    @Override
    public int getEnergyPerProtectionPoint() {
        return iuadd_cfgEnergyPerPoint(armor, subTypeArmor);
    }

    @Override
    public void modifyEnergy(@Nonnull ItemStack stack, int amount) {
        if (amount == 0) return;

        if (amount > 0) {
            double space = Math.max(0D, getMaxEnergy(stack) - ElectricItem.manager.getCharge(stack));
            double toAdd = Math.min(space, (double) amount);
            if (toAdd > 0) {
                // ignoreTransfer=true, simulate=false
                ElectricItem.manager.charge(stack, toAdd, Integer.MAX_VALUE, true, false);
            }
        } else {
            double stored = Math.max(0D, ElectricItem.manager.getCharge(stack));
            double toRem  = Math.min(stored, (double) -amount);
            if (toRem > 0) {
                // ignoreTransfer=true, externally=false, simulate=false
                ElectricItem.manager.discharge(stack, toRem, Integer.MAX_VALUE, true, false, false);
            }
        }
    }

    // =============================================================================================
    // IEnergyContainerItem (RF) bridge to IU energy
    // =============================================================================================

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        if (maxReceive <= 0) return 0;
        double stored   = ElectricItem.manager.getCharge(stack);
        double capacity = getMaxEnergy(stack);
        double space    = Math.max(0D, capacity - stored);
        int accepted    = (int) Math.min((double) maxReceive, Math.floor(space));
        if (!simulate && accepted > 0) {
            ElectricItem.manager.charge(stack, (double) accepted, Integer.MAX_VALUE, true, false);
        }
        return Math.max(0, accepted);
    }

    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        if (maxExtract <= 0) return 0;
        double stored  = ElectricItem.manager.getCharge(stack);
        int extracted  = (int) Math.min((double) maxExtract, Math.floor(stored));
        if (!simulate && extracted > 0) {
            ElectricItem.manager.discharge(stack, (double) extracted, Integer.MAX_VALUE, true, false, false);
        }
        return Math.max(0, extracted);
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        return (int) Math.max(0D, Math.floor(ElectricItem.manager.getCharge(stack)));
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack) {
        return (int) Math.max(0D, Math.floor(getMaxEnergy(stack)));
    }

    // =============================================================================================
    // Other ICustomArmor methods — neutral defaults (keeps behavior unchanged unless you want tweaks)
    // =============================================================================================

    @Override
    public float getSpeedModifier(ItemStack stack, EntityPlayer player) { return 0.0F; }

    @Override
    public float getJumpModifier(ItemStack stack, EntityPlayer player) { return 0; }

    @Override
    public boolean hasHillStep(ItemStack stack, EntityPlayer player) { return false; }

    @Override
    public float getFireResistance(ItemStack stack) { return 0.0F; }

    @Override
    public float getFlightSpeedModifier(ItemStack stack, EntityPlayer player) { return 0; }

    @Override
    public float getFlightVModifier(ItemStack stack, EntityPlayer player) { return 0; }

    @Override
    public boolean[] hasFlight(ItemStack stack) {
        // [ enableFlight, flightLock, inertiaCancel ]
        return new boolean[] { false, false, false };
    }

    // =============================================================================================
    // Config access — @Unique to satisfy mixin member naming rule
    // Replace with your direct config calls when ready
    // =============================================================================================

    @Unique
    private static int iuadd_cfgMaxShieldPoints(EnumTypeArmor type, EnumSubTypeArmor sub) {
        // Reflection path: com.aeternal.iuadditions.Config$Shield.getMaxPoints(EnumTypeArmor, EnumSubTypeArmor)
        Integer val = iuadd_reflectInt("com.aeternal.iuadditions.Config$Shield", "getMaxPoints",
                new Class<?>[]{EnumTypeArmor.class, EnumSubTypeArmor.class}, new Object[]{type, sub});
        if (val != null) return val;

        // Defaults
        switch (type) {
            case NANO:     return (sub == EnumSubTypeArmor.CHESTPLATE ? 32  : 24);
            case ADV_NANO: return (sub == EnumSubTypeArmor.CHESTPLATE ? 56  : 40);
            case QUANTUM:  return (sub == EnumSubTypeArmor.CHESTPLATE ? 96  : 64);
            case SPECTRAL: return (sub == EnumSubTypeArmor.CHESTPLATE ? 120 : 80);
            default:       return 24;
        }
    }

    @Unique
    private static int iuadd_cfgRecoveryRate(EnumTypeArmor type, EnumSubTypeArmor sub) {
        // Reflection path: ...Config$Shield.getRecoveryRate(EnumTypeArmor, EnumSubTypeArmor)
        Integer val = iuadd_reflectInt("com.aeternal.iuadditions.Config$Shield", "getRecoveryRate",
                new Class<?>[]{EnumTypeArmor.class, EnumSubTypeArmor.class}, new Object[]{type, sub});
        if (val != null) return val;

        switch (type) {
            case NANO:     return (sub == EnumSubTypeArmor.CHESTPLATE ? 4 : 3);
            case ADV_NANO: return (sub == EnumSubTypeArmor.CHESTPLATE ? 6 : 4);
            case QUANTUM:  return (sub == EnumSubTypeArmor.CHESTPLATE ? 8 : 6);
            case SPECTRAL: return (sub == EnumSubTypeArmor.CHESTPLATE ? 10 : 8);
            default:       return 3;
        }
    }

    @Unique
    private static int iuadd_cfgEnergyPerPoint(EnumTypeArmor type, EnumSubTypeArmor sub) {
        // Reflection path: ...Config$Shield.getEnergyPerPoint(EnumTypeArmor, EnumSubTypeArmor)
        Integer val = iuadd_reflectInt("com.aeternal.iuadditions.Config$Shield", "getEnergyPerPoint",
                new Class<?>[]{EnumTypeArmor.class, EnumSubTypeArmor.class}, new Object[]{type, sub});
        if (val != null) return val;

        switch (type) {
            case NANO:     return 2000;
            case ADV_NANO: return 4000;
            case QUANTUM:  return 8000;
            case SPECTRAL: return 12000;
            default:       return 2000;
        }
    }

    @Unique
    private static Integer iuadd_reflectInt(String owner, String method, Class<?>[] sig, Object[] args) {
        try {
            Class<?> cls = Class.forName(owner);
            java.lang.reflect.Method m = cls.getMethod(method, sig);
            Object out = m.invoke(null, args);
            if (out instanceof Number) return ((Number) out).intValue();
        } catch (Throwable ignored) { }
        return null;
    }
}
