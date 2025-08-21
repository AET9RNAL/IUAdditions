package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.Config;
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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

// import your real Config class here:
// import com.aeternal.iuadditions.config.Config;

/**
 * MixinKatana (Forge 1.12.2)
 * - Replaces hardcoded fields with Config values.
 * - Adds armor-piercing, god-slaying and soul damage (LawSword-style).
 * - Applies extra divine & armor-bypass damage and progressive soul drain on hit.
 */
@Mixin(value = ItemKatana.class, remap = false)
public abstract class MixinKatana {

    /* ====== Shadows (match your ItemKatana + ItemTool) ====== */
    @Shadow public int damage1;          // declared in ItemKatana
    //@Shadow protected float efficiency;  // declared in ItemTool (super)

    /* ====== Custom attributes (namespaced) ====== */
    @Unique private static final IAttribute IU_ARMOR_PIERCING =
            (IAttribute) new RangedAttribute(null, "iuadditions.armorpiercingattackdamage", 0.0D, 0.0D, 2048.0D).setShouldWatch(true);
    @Unique private static final IAttribute IU_GOD_SLAYING =
            (IAttribute) new RangedAttribute(null, "iuadditions.godslayingattackdamage", 0.0D, 0.0D, 2048.0D).setShouldWatch(true);
    @Unique private static final IAttribute IU_SOUL_DAMAGE =
            (IAttribute) new RangedAttribute(null, "iuadditions.souldamage", 0.0D, 0.0D, 2048.0D).setShouldWatch(true);

    @Unique private static final UUID UUID_ARMOR_PIERCING = UUID.fromString("0afc0f0c-2a47-4a0d-9a4c-1f8c1b0b0a01");
    @Unique private static final UUID UUID_GOD_SLAYING    = UUID.fromString("5f1e6b6a-0b58-4c65-a687-6e0adf0e3c02");
    @Unique private static final UUID UUID_SOUL_DISPLAY   = UUID.fromString("2ccdc290-a885-473a-973f-cdc5c918773b"); // shown on item
    @Unique private static final UUID UUID_SOUL_DRAIN     = UUID.fromString("b6b6b6b6-b6b6-b6b6-b6b6-b6b6b6b6b6b6"); // applied to targets

    /* ====== Constructor: override hardcoded fields after super completes ====== */
    @Inject(method = "<init>(Ljava/lang/String;)V", at = @At("RETURN"))
    private void iuadditions$init(String name, CallbackInfo ci) {
        this.damage1 = Math.max(0, (int) Config.katanaDmg);
       // this.efficiency = (float) Math.max(0.0, Config.katanaEff);
    }

    /* ====== Attribute modifiers: append our custom ones for MAINHAND ====== */
    @Inject(
            method = "getAttributeModifiers(Lnet/minecraft/inventory/EntityEquipmentSlot;Lnet/minecraft/item/ItemStack;)Lcom/google/common/collect/Multimap;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void iuadditions$attributes(EntityEquipmentSlot slot, ItemStack stack,
                                        CallbackInfoReturnable<Multimap<String, AttributeModifier>> cir) {
        if (slot != EntityEquipmentSlot.MAINHAND) return;

        Multimap<String, AttributeModifier> base = cir.getReturnValue();
        Multimap<String, AttributeModifier> out = HashMultimap.create(base);

        double armorPierce = Math.max(0.0D, Config.katanaArmorPierce);
        double godSlay     = Math.max(0.0D, Config.katanaGodSlay);
        double soulDisplay = (Config.katanaSoulStep > 0.0D) ? (10.0D / Config.katanaSoulStep) : 0.0D;

        out.put(IU_ARMOR_PIERCING.getName(),
                new AttributeModifier(UUID_ARMOR_PIERCING, "Weapon modifier (armor pierce)", armorPierce, 0));
        out.put(IU_GOD_SLAYING.getName(),
                new AttributeModifier(UUID_GOD_SLAYING, "Weapon modifier (god slay)", godSlay, 0));
        out.put(IU_SOUL_DAMAGE.getName(),
                new AttributeModifier(UUID_SOUL_DISPLAY, "Weapon modifier (soul)", soulDisplay, 0));

        cir.setReturnValue(out);
    }

    /* ====== Extra hits & soul drain: works whether hitEntity or onLeftClickEntity is used ====== */

    // Your ItemKatana DOES have this method:
    // public boolean hitEntity(ItemStack, EntityLivingBase, EntityLivingBase)
    @Inject(
            method = "hitEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/EntityLivingBase;)Z",
            at = @At("TAIL")
    )
    private void iuadditions$afterHit(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker,
                                      CallbackInfoReturnable<Boolean> cir) {
        iuadditions$doExtra(attacker, target);
    }

    // Safety net for variants that use onLeftClickEntity instead (optional — harmless if absent)
    @Inject(method = "onLeftClickEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/Entity;)Z",
            at = @At("TAIL"), require = 0)
    private void iuadditions$afterLeft(ItemStack stack, EntityPlayer player, Entity e, CallbackInfoReturnable<Boolean> cir) {
        if (e instanceof EntityLivingBase) iuadditions$doExtra(player, (EntityLivingBase) e);
    }

    /* ====== Impl ====== */

    @Unique
    private void iuadditions$doExtra(EntityLivingBase attacker, EntityLivingBase target) {
        if (attacker == null || target == null || attacker.world == null || attacker.world.isRemote) return;

        // 1) Soul drain (MAX_HEALTH multiplicative debuff)
        iuadditions$applySoulDrain(target);

        // 2) Extra damage, scaled by player cooldown
        float cooled = (attacker instanceof EntityPlayer)
                ? ((EntityPlayer) attacker).getCooledAttackStrength(0.0F)
                : 1.0F;

        float godSlay = (float) Math.max(0.0D, Config.katanaGodSlay) * cooled;
        float pierce  = (float) Math.max(0.0D, Config.katanaArmorPierce) * cooled;

        int prevInvul = target.hurtResistantTime;
        target.hurtResistantTime = 0;

        double mx = target.motionX, my = target.motionY, mz = target.motionZ;

        if (godSlay > 0.0F) target.attackEntityFrom(iuadditions$godSlayingSource(attacker), godSlay);
        if (pierce  > 0.0F) target.attackEntityFrom(iuadditions$armorBypassSource(attacker),  pierce);

        target.motionX = mx; target.motionY = my; target.motionZ = mz;
        target.velocityChanged = true;
        target.hurtResistantTime = Math.max(target.hurtResistantTime, prevInvul);
    }

    @Unique
    private void iuadditions$applySoulDrain(EntityLivingBase target) {
        if (Config.katanaSoulStep <= 0.0D) return;

        final double step = Math.max(1.0D,Config.katanaSoulStep);
        final IAttributeInstance inst = target.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        if (inst == null) return;

        double current = 0.0D;
        AttributeModifier existing = inst.getModifier(UUID_SOUL_DRAIN);
        if (existing != null) current = existing.getAmount();

        double next = current - step;
        if (existing != null) inst.removeModifier(existing);

        if (next > -1.0D) {
            inst.applyModifier(new AttributeModifier(UUID_SOUL_DRAIN, "iuadditions.soul_drain", next, 2)); // MULTIPLY_TOTAL
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
}
