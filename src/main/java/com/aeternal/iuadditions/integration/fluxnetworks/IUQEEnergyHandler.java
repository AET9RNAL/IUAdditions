package com.aeternal.iuadditions.integration.fluxnetworks;

import com.aeternal.iuadditions.Config;
import com.denfop.api.sytem.EnergyType;
import com.denfop.api.sytem.ISink;
import com.denfop.api.sytem.ISource;
import com.denfop.componets.ComponentBaseEnergy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sonar.fluxnetworks.api.energy.ITileEnergyHandler;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bridges IU's QE (Quantum Energy) API with Flux Networks' energy handler system.
 * <p>
 * QE tiles use the {@link ComponentBaseEnergy} component with {@link EnergyType#QUANTUM}.
 * Unlike EF tiles, QE tiles never directly implement ISink/ISource — they always
 * use the delegate pattern via {@code ComponentBaseEnergy.delegate}.
 * <p>
 * Registered into FN's {@code TileEntityHandler.tileEnergyHandlers} via
 * {@link com.aeternal.iuadditions.mixins.fluxnetworks.MixinTileEntityHandler}.
 */
public class IUQEEnergyHandler implements ITileEnergyHandler {

    public static final IUQEEnergyHandler INSTANCE = new IUQEEnergyHandler();
    private static final Logger LOGGER = LogManager.getLogger("IUAdditions/FN-QE");

    // Cache per tile class: list of ComponentBaseEnergy fields (may include non-QUANTUM types)
    private static final Map<Class<?>, List<Field>> CBE_FIELD_CACHE = new HashMap<>();

    private IUQEEnergyHandler() {}

    /**
     * Find the ComponentBaseEnergy with EnergyType.QUANTUM on an IU tile entity.
     * A tile may have multiple ComponentBaseEnergy fields for different energy types,
     * so we check getType() on each one.
     */
    private static ComponentBaseEnergy getQEComponent(TileEntity tile) {
        List<Field> fields = CBE_FIELD_CACHE.computeIfAbsent(tile.getClass(), clazz -> {
            List<Field> found = new ArrayList<>();
            for (Field f : clazz.getFields()) {
                if (f.getType() == ComponentBaseEnergy.class) {
                    LOGGER.info("Found ComponentBaseEnergy field '{}' on {}", f.getName(), clazz.getName());
                    found.add(f);
                }
            }
            return found;
        });
        for (Field f : fields) {
            try {
                ComponentBaseEnergy comp = (ComponentBaseEnergy) f.get(tile);
                if (comp != null && comp.getType() == EnergyType.QUANTUM) {
                    return comp;
                }
            } catch (Exception e) {
                // ignore
            }
        }
        return null;
    }

    /**
     * Find the ISink delegate for a tile's QE component.
     */
    public static ISink findSink(TileEntity tile) {
        ComponentBaseEnergy comp = getQEComponent(tile);
        if (comp != null && comp.delegate instanceof ISink) {
            return (ISink) comp.delegate;
        }
        return null;
    }

    /**
     * Find the ISource delegate for a tile's QE component.
     */
    public static ISource findSource(TileEntity tile) {
        ComponentBaseEnergy comp = getQEComponent(tile);
        if (comp != null && comp.delegate instanceof ISource) {
            return (ISource) comp.delegate;
        }
        return null;
    }

    @Override
    public boolean hasCapability(@Nonnull TileEntity tile, EnumFacing side) {
        return getQEComponent(tile) != null;
    }

    @Override
    public boolean canAddEnergy(@Nonnull TileEntity tile, EnumFacing side) {
        return findSink(tile) != null;
    }

    @Override
    public boolean canRemoveEnergy(@Nonnull TileEntity tile, EnumFacing side) {
        return findSource(tile) != null;
    }

    @Override
    public long addEnergy(long amount, @Nonnull TileEntity tile, EnumFacing side, boolean simulate) {
        ISink sink = findSink(tile);
        if (sink != null) {
            int ratio = Config.coefficientQeToRf;
            double demandedQE = sink.getDemanded();
            double offerQE = (double) amount / ratio;
            double toAddQE = Math.min(offerQE, demandedQE);
            if (toAddQE <= 0) return 0;
            if (!simulate) {
                sink.receivedEnergy(toAddQE);
            }
            return (long) (toAddQE * ratio);
        }
        return 0;
    }

    @Override
    public long removeEnergy(long amount, @Nonnull TileEntity tile, EnumFacing side) {
        ISource source = findSource(tile);
        if (source != null) {
            int ratio = Config.coefficientQeToRf;
            double availableQE = source.canProvideEnergy();
            double requestQE = (double) amount / ratio;
            double toRemoveQE = Math.min(requestQE, availableQE);
            if (toRemoveQE <= 0) return 0;
            source.extractEnergy(toRemoveQE);
            return (long) (toRemoveQE * ratio);
        }
        return 0;
    }
}
