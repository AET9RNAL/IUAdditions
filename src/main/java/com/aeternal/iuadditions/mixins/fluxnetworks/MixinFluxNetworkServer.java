package com.aeternal.iuadditions.mixins.fluxnetworks;

import com.aeternal.iuadditions.Config;
import com.aeternal.iuadditions.integration.fluxnetworks.IUEFEnergyHandler;
import com.aeternal.iuadditions.integration.fluxnetworks.IUQEEnergyHandler;
import com.denfop.api.energy.IEnergySource;
import com.denfop.api.sytem.ISource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sonar.fluxnetworks.api.network.FluxLogicType;
import sonar.fluxnetworks.api.tiles.IFluxPlug;
import sonar.fluxnetworks.common.connection.FluxNetworkServer;

import java.util.List;

/**
 * Active IU energy pulling for Flux Plugs (EF + QE).
 */
@Mixin(value = FluxNetworkServer.class, remap = false)
public class MixinFluxNetworkServer {

    @Inject(method = "onEndServerTick", at = @At("RETURN"), require = 0)
    private void iua$pullFromIUSources(CallbackInfo ci) {
        FluxNetworkServer self = (FluxNetworkServer) (Object) this;
        List<IFluxPlug> plugs = self.getConnections(FluxLogicType.PLUG);

        for (IFluxPlug plug : plugs) {
            if (!plug.isActive()) continue;
            World world = plug.getFluxWorld();
            if (world == null || world.isRemote) continue;
            BlockPos pos = ((TileEntity) plug).getPos();

            for (EnumFacing face : EnumFacing.VALUES) {
                TileEntity te = world.getTileEntity(pos.offset(face));
                if (te == null) continue;

                // EF energy sources
                IEnergySource efSrc = IUEFEnergyHandler.findSource(te);
                if (efSrc != null) {
                    double availableEF = efSrc.canExtractEnergy();
                    if (availableEF > 0) {
                        int ratio = Config.coefficientEfToRf;
                        long offeredRF = (long) (availableEF * ratio);
                        if (offeredRF > 0) {
                            long acceptedRF = plug.getTransferHandler()
                                    .receiveFromSupplier(offeredRF, face, false);
                            if (acceptedRF > 0) {
                                efSrc.extractEnergy((double) acceptedRF / ratio);
                            }
                        }
                    }
                }

                // QE energy sources
                ISource qeSrc = IUQEEnergyHandler.findSource(te);
                if (qeSrc != null) {
                    double availableQE = qeSrc.canProvideEnergy();
                    if (availableQE > 0) {
                        int ratio = Config.coefficientQeToRf;
                        long offeredRF = (long) (availableQE * ratio);
                        if (offeredRF > 0) {
                            long acceptedRF = plug.getTransferHandler()
                                    .receiveFromSupplier(offeredRF, face, false);
                            if (acceptedRF > 0) {
                                qeSrc.extractEnergy((double) acceptedRF / ratio);
                            }
                        }
                    }
                }
            }
        }
    }
}
