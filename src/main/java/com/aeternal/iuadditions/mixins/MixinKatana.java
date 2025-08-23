package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.hooks.KatanaApplier;
import com.denfop.items.energy.ItemKatana;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

@Mixin(value = ItemKatana.class, remap = false)
public abstract class MixinKatana {

    @Unique private static final Logger KATANA_LOG = LogManager.getLogger("IUAdditions|Katana");

    @Shadow public int damage1;

    @Unique private static final IAttribute IU_ARMOR_PIERCING =
            (IAttribute) new RangedAttribute(null, "iuadditions.armorpiercingattackdamage", 0.0D, 0.0D, 2048.0D).setShouldWatch(true);
    @Unique private static final IAttribute IU_GOD_SLAYING =
            (IAttribute) new RangedAttribute(null, "iuadditions.godslayingattackdamage", 0.0D, 0.0D, 2048.0D).setShouldWatch(true);
    @Unique private static final IAttribute IU_SOUL_DAMAGE =
            (IAttribute) new RangedAttribute(null, "iuadditions.souldamage", 0.0D, 0.0D, 2048.0D).setShouldWatch(true);

    @Unique private static final UUID UUID_ARMOR_PIERCING = UUID.fromString("0afc0f0c-2a47-4a0d-9a4c-1f8c1b0b0a01");
    @Unique private static final UUID UUID_GOD_SLAYING    = UUID.fromString("5f1e6b6a-0b58-4c65-a687-6e0adf0e3c02");
    @Unique private static final UUID UUID_SOUL_DISPLAY   = UUID.fromString("2ccdc290-a885-473a-973f-cdc5c918773b");
    @Unique private static final UUID UUID_SOUL_DRAIN     = UUID.fromString("b6b6b6b6-b6b6-b6b6-b6b6-b6b6b6b6b6b6");

    @Unique private static final Map<EntityLivingBase, Float> PRE_HP  =
            Collections.synchronizedMap(new WeakHashMap<>());
    @Unique private static final Map<EntityLivingBase, Float> PRE_MAX =
            Collections.synchronizedMap(new WeakHashMap<>());

    @Unique private boolean iuadditions$baseApplied = false;

    @Unique
    private void iuadditions$applyBaseFieldsIfNeeded() {
        if (!iuadditions$baseApplied) {
            this.damage1 = KatanaApplier.CFG_DMG;
            iuadditions$baseApplied = true;
            if (KatanaApplier.CFG_DEBUG) {
                KATANA_LOG.info("[Katana:applyBase] damage1={}", this.damage1);
            }
        }
    }

    @Inject(
            method = "getAttributeModifiers(Lnet/minecraft/inventory/EntityEquipmentSlot;Lnet/minecraft/item/ItemStack;)Lcom/google/common/collect/Multimap;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void iuadditions$attributes(EntityEquipmentSlot slot, ItemStack stack,
                                        CallbackInfoReturnable<Multimap<String, AttributeModifier>> cir) {
        if (slot != EntityEquipmentSlot.MAINHAND) return;
        iuadditions$applyBaseFieldsIfNeeded();

        Multimap<String, AttributeModifier> out = HashMultimap.create(cir.getReturnValue());
        double armorPierce = Math.max(0.0D, KatanaApplier.CFG_PIERCE);
        double godSlay     = Math.max(0.0D, KatanaApplier.CFG_GOD);
        double soulDisplay = KatanaApplier.CFG_SOUL_STEP > 0.0D ? (10.0D / KatanaApplier.CFG_SOUL_STEP) : 0.0D;

        out.put(IU_ARMOR_PIERCING.getName(),
                new AttributeModifier(UUID_ARMOR_PIERCING, "Weapon modifier (armor pierce)", armorPierce, 0));
        out.put(IU_GOD_SLAYING.getName(),
                new AttributeModifier(UUID_GOD_SLAYING,    "Weapon modifier (god slay)",    godSlay,     0));
        out.put(IU_SOUL_DAMAGE.getName(),
                new AttributeModifier(UUID_SOUL_DISPLAY,   "Weapon modifier (soul)",        soulDisplay, 0));

        cir.setReturnValue(out);
    }

    @Inject(
            method = "hitEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/EntityLivingBase;)Z",
            at = @At("HEAD"),
            require = 0
    )
    private void iuadditions$captureHead(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker,
                                         CallbackInfoReturnable<Boolean> cir) {
        if (!KatanaApplier.CFG_DEBUG) return;
        PRE_HP.put(target, target.getHealth());
        IAttributeInstance inst = target.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        PRE_MAX.put(target, inst != null ? (float) inst.getAttributeValue() : target.getMaxHealth());
    }


    @Inject(
            method = "hitEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/EntityLivingBase;)Z",
            at = @At("TAIL"),
            require = 0
    )
    private void iuadditions$afterHit(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker,
                                      CallbackInfoReturnable<Boolean> cir) {
        iuadditions$doExtraAndMaybeLog(stack, attacker, target, "hitEntity");
    }


    @Unique
    private void iuadditions$doExtraAndMaybeLog(ItemStack stack, EntityLivingBase attacker, EntityLivingBase target, String path) {
        if (attacker == null || target == null || attacker.world == null || attacker.world.isRemote) return;
        iuadditions$applyBaseFieldsIfNeeded();

        float startHP = 0, startMax = 0, preExtraHP = 0;
        if (KatanaApplier.CFG_DEBUG) {
            startHP = PRE_HP.containsKey(target) ? PRE_HP.remove(target) : target.getHealth();
            startMax = PRE_MAX.containsKey(target) ? PRE_MAX.remove(target) : target.getMaxHealth();
            preExtraHP = target.getHealth();
        }

        double preMaxAttr = getMaxHealthAttr(target);
        float soulApplied = 0.0F;
        boolean soulAppliedFlag = false;
        if (KatanaApplier.CFG_SOUL_STEP > 0.0D && (KatanaApplier.CFG_E_SOUL <= 0.0D || iuadditions$tryDrain(stack, KatanaApplier.CFG_E_SOUL))) {
            iuadditions$applySoulDrain(target);
            double postMaxAttr = getMaxHealthAttr(target);
            soulApplied = (float) Math.max(0.0D, preMaxAttr - postMaxAttr);
            soulAppliedFlag = soulApplied > 0.0F;
        }

        float cooled = 1.0F;
        if (attacker instanceof EntityPlayer) {
            cooled = ((EntityPlayer) attacker).getCooledAttackStrength(0.0F);
            if (cooled < 0.2F) cooled = 0.2F;
        }
        float godPlanned    = (float) Math.max(0.0D, KatanaApplier.CFG_GOD)    * cooled;
        float piercePlanned = (float) Math.max(0.0D, KatanaApplier.CFG_PIERCE) * cooled;

        int prevInvul = target.hurtResistantTime;
        target.hurtResistantTime = 0;
        double mx = target.motionX, my = target.motionY, mz = target.motionZ;

        float beforeGod = target.getHealth();
        float godApplied = 0.0F;
        if (godPlanned > 0.0F && (KatanaApplier.CFG_E_GOD <= 0.0D || iuadditions$tryDrain(stack, KatanaApplier.CFG_E_GOD))) {
            target.attackEntityFrom(iuadditions$godSlayingSource(attacker), godPlanned);
            godApplied = Math.max(0.0F, beforeGod - target.getHealth());
        }

        float beforePierce = target.getHealth();
        float pierceApplied = 0.0F;
        if (piercePlanned > 0.0F && (KatanaApplier.CFG_E_PIERCE <= 0.0D || iuadditions$tryDrain(stack, KatanaApplier.CFG_E_PIERCE))) {
            target.attackEntityFrom(iuadditions$armorBypassSource(attacker), piercePlanned);
            pierceApplied = Math.max(0.0F, beforePierce - target.getHealth());
        }

        target.motionX = mx; target.motionY = my; target.motionZ = mz;
        target.velocityChanged = true;
        target.hurtResistantTime = Math.max(target.hurtResistantTime, prevInvul);

        if (KatanaApplier.CFG_DEBUG) {
            float baseApplied = Math.max(0.0F, startHP - preExtraHP);
            float finalHP = target.getHealth();
            KATANA_LOG.info(
                    "[Katana:{}] attacker='{}' target='{}' cooldown={}\n" +
                            "  Base:  applied={} (HP {} -> {})\n" +
                            "  Soul:  applied(maxHP)={} (applied={}) N={}\n" +
                            "  God:   applied={} (planned={})\n" +
                            "  Pierce:applied={} (planned={})\n" +
                            "  After all: HP={}",
                    path, safeName(attacker), safeName(target), fmt(cooled),
                    fmt(baseApplied), fmt(startHP), fmt(preExtraHP),
                    fmt(soulApplied), soulAppliedFlag, fmt((float) KatanaApplier.CFG_SOUL_STEP),
                    fmt(godApplied), fmt(godPlanned),
                    fmt(pierceApplied), fmt(piercePlanned),
                    fmt(finalHP)
            );
        }
    }

    @Unique
    private static double getMaxHealthAttr(EntityLivingBase e) {
        IAttributeInstance inst = e.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        return inst != null ? inst.getAttributeValue() : e.getMaxHealth();
    }

    @Unique
    private void iuadditions$applySoulDrain(EntityLivingBase target) {
        final double step = 1.0D / KatanaApplier.CFG_SOUL_STEP;
        final IAttributeInstance inst = target.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        if (inst == null) return;
        double current = 0.0D;
        AttributeModifier existing = inst.getModifier(UUID_SOUL_DRAIN);
        if (existing != null) current = existing.getAmount();
        double next = current - step;
        if (existing != null) inst.removeModifier(existing);
        if (next > -1.0D) {
            inst.applyModifier(new AttributeModifier(UUID_SOUL_DRAIN, "iuadditions.soul_drain", next, 2));
        } else {
            target.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
        }
    }

    @Unique
    private static DamageSource iuadditions$godSlayingSource(EntityLivingBase attacker) {
        EntityDamageSource src = new EntityDamageSource("iuadditions.god_slaying", attacker);
        src.setDamageIsAbsolute();
        src.setDamageAllowedInCreativeMode();
        return src;
    }

    @Unique
    private static DamageSource iuadditions$armorBypassSource(EntityLivingBase attacker) {
        EntityDamageSource src = new EntityDamageSource("iuadditions.armor_piercing", attacker);
        src.setDamageBypassesArmor();
        src.setDamageIsAbsolute();
        return src;
    }

    @Unique private static String safeName(EntityLivingBase e) { return e == null ? "null" : e.getName(); }
    @Unique private static String fmt(float f) { return String.format("%.2f", f); }

    @Unique
    private boolean iuadditions$tryDrain(ItemStack stack, double amount) {
        try {
            return ((ItemKatana)(Object)this).drainSaber(stack, amount);
        } catch (Throwable t) {
            return false;
        }
    }
}
