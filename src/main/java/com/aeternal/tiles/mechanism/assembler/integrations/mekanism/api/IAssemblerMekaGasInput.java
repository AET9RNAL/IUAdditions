package com.aeternal.tiles.mechanism.assembler.integrations.mekanism.api;

import com.aeternal.components.MekaGasses;
import com.aeternal.tiles.mechanism.assembler.api.IAssemblerPart;
import mekanism.api.gas.GasTank;

public interface IAssemblerMekaGasInput extends IAssemblerPart {

    GasTank getGasTank();

    MekaGasses getGas();

}
