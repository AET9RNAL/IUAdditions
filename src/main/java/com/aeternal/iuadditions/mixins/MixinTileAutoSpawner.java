package com.aeternal.iuadditions.mixins;

import com.aeternal.iuadditions.Config;
import com.denfop.tiles.base.TileAutoSpawner;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.world.storage.loot.LootTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = TileAutoSpawner.class, remap = false, priority = 1000)
public abstract class MixinTileAutoSpawner {

    private static final Logger LOG = LogManager.getLogger("IUAdditions/MixinTileAutoSpawner");

    /** Visibility that the mixin runs; logs original value before redirect. */
    @Inject(
            method = "dropItemFromEntity(Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/util/DamageSource;Lnet/minecraft/world/storage/loot/LootTable;I)V",
            at = @At("HEAD"),
            cancellable = false,
            require = 0
    )
    private void iuadditions$logEntry(EntityLiving entity, DamageSource source, LootTable table, int index, CallbackInfo ci) {
        if (Config.allowBoss) {
            LOG.info("[IUAdditions] allowBoss=true -> relaxing gate; entity='{}' nonBoss(original)={}",
                    entity.getName(), entity.isNonBoss());
        }
    }

    @Redirect(
            method = "dropItemFromEntity(Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/util/DamageSource;Lnet/minecraft/world/storage/loot/LootTable;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLiving;isNonBoss()Z", remap = true),
            require = 1
    )
    private boolean iuadditions$allowBossDrops(EntityLiving self) {
        boolean original = self.isNonBoss();
        boolean result = original || Config.allowBoss;
        if (Config.allowBoss) {
            LOG.info("[IUAdditions] redirect isNonBoss: entity='{}' original={} -> returning={}",
                    self.getName(), original, result);
        }
        return result;
    }
}
