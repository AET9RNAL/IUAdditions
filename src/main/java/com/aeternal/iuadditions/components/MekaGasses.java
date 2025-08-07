package com.aeternal.iuadditions.components;

import com.denfop.Localization;
import com.denfop.componets.AbstractComponent;
import com.denfop.invslot.InvSlot;
import com.denfop.tiles.base.TileEntityInventory;
import com.denfop.utils.ModUtils;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.GasTank;
import net.minecraft.util.EnumFacing;

import java.util.*;
import java.util.stream.Collectors;

public class MekaGasses extends AbstractComponent {

    protected final List<InternalGasTank> managedTanks = new ArrayList();

    public MekaGasses(TileEntityInventory parent) {
        super(parent);
    }

    public static Predicate<Gas> gasPredicate(Gas... gasses) {
        Collection<Gas> acceptedGasses;
        if (gasses.length > 10) {
            acceptedGasses = new HashSet(Arrays.asList(gasses));
        } else {
            acceptedGasses = Arrays.asList(gasses);
        }

        return acceptedGasses::contains;
    }

    public static Predicate<Gas> gasPredicate(List<Gas> gasses) {
        Collection<Gas> acceptedGasses;
        if (gasses != null) {
            if (gasses.size() > 10) {
                acceptedGasses = new HashSet(gasses);
            } else {
                acceptedGasses = gasses;
            }
        } else {
            acceptedGasses = new ArrayList();
        }

        return acceptedGasses::contains;
    }

    public InternalGasTank addTank(String name, int capacity, InvSlot.TypeItemSlot typeItemSlot) {
        return this.addTank(name, capacity, typeItemSlot, Predicates.alwaysTrue());
    }

    public InternalGasTank addTank(String name, int capacity, Predicate<Gas> acceptedGasses) {
        return this.addTank(name, capacity, InvSlot.TypeItemSlot.INPUT_OUTPUT, acceptedGasses);
    }

    public InternalGasTank addTank(String name, int capacity, Predicate<Gas> acceptedGasses, InvSlot.TypeItemSlot slot) {
        return this.addTank(name, capacity, slot, acceptedGasses);
    }

    public InternalGasTank addTank(String name, int capacity, InvSlot.TypeItemSlot typeItemSlot, Predicate<Gas> acceptedGasses) {
        return this.addTank(name, capacity, typeItemSlot.isInput() ? ModUtils.allFacings : Collections.emptySet(), typeItemSlot.isOutput() ? ModUtils.allFacings : Collections.emptySet(), acceptedGasses, typeItemSlot);
    }

    public InternalGasTank addTank(String name, int capacity, Collection<EnumFacing> inputSides, Collection<EnumFacing> outputSides, Predicate<Gas> acceptedGasses, InvSlot.TypeItemSlot typeItemSlot) {
        return this.addTank(new InternalGasTank(name, inputSides, outputSides, acceptedGasses, capacity, typeItemSlot));
    }

    public InternalGasTank addTank(InternalGasTank tank) {
        this.managedTanks.add(tank);
        return tank;
    }

    public static class InternalGasTank extends GasTank {
        protected final String identifier;
        List<String> gasList = new ArrayList();
        private InvSlot.TypeItemSlot typeItemSlot;
        private Predicate<Gas> acceptedGasses;
        private List<EnumFacing> inputSides;
        private List<EnumFacing> outputSides;
        private boolean canAccept = true;

        public InternalGasTank(String identifier, Collection<EnumFacing> inputSides, Collection<EnumFacing> outputSides, Predicate<Gas> acceptedGasses, int capacity, InvSlot.TypeItemSlot typeItemSlot) {
            super(capacity);
            this.identifier = identifier;
            this.acceptedGasses = acceptedGasses;
            this.inputSides = new ArrayList(inputSides);
            this.outputSides = new ArrayList(outputSides);
            this.typeItemSlot = typeItemSlot;

            for(Gas gas : getRegisteredGasses().values().stream().filter(acceptedGasses).collect(Collectors.toList())) {
                this.gasList.add(Localization.translate(gas.getUnlocalizedName()));
            }
        }

        private static Map<String, Gas> getRegisteredGasses() {
            Map<String, Gas> registeredGasses = new HashMap<>();
            for (Gas gas : GasRegistry.getRegisteredGasses()) {
                registeredGasses.put(gas.getName(), gas);
            }
            return registeredGasses;
        }

    }
}
