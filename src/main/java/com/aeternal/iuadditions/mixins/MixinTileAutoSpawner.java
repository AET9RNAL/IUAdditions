package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.world.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.denfop.tiles.base.TileAutoSpawner", remap = false)
public abstract class MixinTileAutoSpawner {

    @Group(name = "isNonBoss", min = 1)
    @Redirect(
            method = "dropItemFromEntity(Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/util/DamageSource;Lnet/minecraft/world/storage/loot/LootTable;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityLiving;isNonBoss()Z",
                    remap = true)
    )
    private boolean redirectIsNonBoss_onLiving(net.minecraft.entity.EntityLiving self) {
        System.out.println("[IUAdditions] Redirect (EntityLiving) allowBoss=" + com.aeternal.iuadditions.Config.allowBoss);
        return com.aeternal.iuadditions.Config.allowBoss ;
    }

    @Group(name = "isNonBoss", min = 1)
    @Redirect(
            method = "dropItemFromEntity(Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/util/DamageSource;Lnet/minecraft/world/storage/loot/LootTable;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityLivingBase;isNonBoss()Z",
                    remap = true)
    )
    private boolean redirectIsNonBoss_onLivingBase(net.minecraft.entity.EntityLivingBase self) {
        //System.out.println("[IUAdditions] Redirect (EntityLivingBase) allowBoss=" + com.aeternal.iuadditions.Config.allowBoss);
        return com.aeternal.iuadditions.Config.allowBoss;
    }
}

