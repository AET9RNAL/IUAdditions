package com.aeternal.iuadditions.tiles.mechanism.assembler.integrations.mekanism.api;

import com.aeternal.iuadditions.components.MekaGasses;
import com.aeternal.iuadditions.tiles.mechanism.assembler.api.IAssemblerPart;
import mekanism.api.gas.GasTank;

public interface IAssemblerMekaGasInput extends IAssemblerPart {

    GasTank getGasTank();

    MekaGasses getGas();

}
