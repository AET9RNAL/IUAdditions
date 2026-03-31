//---DEPRECATED---//
package com.aeternal.iuadditions.mixins.fluxnetworks;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.integration.fluxnetworks.IUEFEnergyHandler;
import com.denfop.api.energy.IEnergySource;
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
 * Active EF energy pulling for Flux Plugs.
 */
@Mixin(value = FluxPlugHandler.class, remap = false)
public abstract class MixinFluxPlugHandlerEF extends BasicTransferHandler {

    // Fields declared in FluxPlugHandler (not inherited) - safe to shadow
    @Shadow private Map<EnumFacing, ConnectionTransfer> transfers;
    @Shadow private long received;

    @SuppressWarnings("all")
    protected MixinFluxPlugHandlerEF() {
        super(null);
    }

    @Inject(method = "onCycleStart", at = @At("RETURN"), require = 0)
    private void iua$pullFromEFSources(CallbackInfo ci) {
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

            // Use delegate-aware source detection
            IEnergySource src = IUEFEnergyHandler.findSource(te);
            if (src == null) continue;

            double availableEF = src.canExtractEnergy();
            if (availableEF <= 0) continue;

            int ratio = Config.coefficientEfToRf;
            // Convert: space is RF, availableEF is EF
            long toExtractEF = (long) Math.min(availableEF, (double) space / ratio);
            if (toExtractEF <= 0) continue;

            long rfGained = toExtractEF * ratio;

            // Directly manage buffer - bypassing receiveFromSupplier
            // because bufferLimiter is 0 at this point in the cycle
            buffer += rfGained;
            received += rfGained;
            src.extractEnergy(toExtractEF);

            // Update ConnectionTransfer tracking for this face (if exists)
            ConnectionTransfer transfer = transfers.get(face);
            if (transfer != null) {
                transfer.onEnergyReceived(rfGained);
            }
        }
    }
}
