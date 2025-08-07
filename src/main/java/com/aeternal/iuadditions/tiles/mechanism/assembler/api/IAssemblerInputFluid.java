package com.aeternal.iuadditions.tiles.mechanism.assembler.api;

import com.denfop.componets.Fluids;
import net.minecraftforge.fluids.FluidTank;

public interface IAssemblerInputFluid extends IAssemblerPart {

    FluidTank getFluidTank();

    Fluids getFluid();

}