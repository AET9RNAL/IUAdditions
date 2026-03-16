//---DEPRECATED---//
package com.aeternal.iuadditions.mixins.fluxnetworks;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.integration.fluxnetworks.IUQEEnergyHandler;
import com.denfop.api.sytem.ISource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sonar.fluxnetworks.common.connection.transfer.BasicTransferHandler;
import sonar.fluxnetworks.common.connection.transfer.ConnectionTransfer;
import sonar.fluxnetworks.common.connection.transfer.FluxPlugHandler;

import java.util.Map;

/**
 * Active QE energy pulling for Flux Plugs.
 * <p>
 * IU QE sources don't push energy into FN plugs (IU's QE network is separate).
 * mixin scans adjacent tiles for IU {@link ISource}s (via QE ComponentBaseEnergy
 * delegates) during {@code onCycleStart()} and adds extracted energy directly to
 * the plug's buffer.
 * <p>
 * bypass {@code receiveFromSupplier()} because FN resets {@code bufferLimiter}
 * to 0 before calling {@code onCycleStart()}, making {@code receiveFromSupplier()}
 * always return 0 at this point in the cycle.
 */
@Mixin(value = FluxPlugHandler.class, remap = false)
public abstract class MixinFluxPlugHandlerQE extends BasicTransferHandler {

    @Shadow private Map<EnumFacing, ConnectionTransfer> transfers;
    @Shadow private long received;

    @SuppressWarnings("all")
    protected MixinFluxPlugHandlerQE() {
        super(null);
    }

    @Inject(method = "onCycleStart", at = @At("RETURN"), require = 0)
    private void iua$pullFromQESources(CallbackInfo ci) {
        World world = device.getFluxWorld();
        if (world == null || world.isRemote) return;
        if (device.getNetwork().isInvalid()) return;
        BlockPos pos = ((TileEntity) device).getPos();

        long limit = device.getLogicLimit();

        for (EnumFacing face : EnumFacing.VALUES) {
            long space = limit - buffer;
            if (space <= 0) break;

            TileEntity te = world.getTileEntity(pos.offset(face));
            if (te == null) continue;

            ISource src = IUQEEnergyHandler.findSource(te);
            if (src == null) continue;

            double availableQE = src.canProvideEnergy();
            if (availableQE <= 0) continue;

            int ratio = Config.coefficientQeToRf;
            long toExtractQE = (long) Math.min(availableQE, (double) space / ratio);
            if (toExtractQE <= 0) continue;

            long rfGained = toExtractQE * ratio;

            buffer += rfGained;
            received += rfGained;
            src.extractEnergy(toExtractQE);

            ConnectionTransfer transfer = transfers.get(face);
            if (transfer != null) {
                transfer.onEnergyReceived(rfGained);
            }
        }
    }
}
