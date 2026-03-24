package com.aeternal.iuadditions.integration.fluxnetworks;

import com.aeternal.iuadditions.Config;
import com.denfop.api.energy.IEnergySink;
import com.denfop.api.energy.IEnergySource;
import com.denfop.api.energy.IEnergyTile;
import com.denfop.componets.Energy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sonar.fluxnetworks.api.energy.ITileEnergyHandler;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Bridges IU's EF energy API with Flux Networks' energy handler system.
 * <p>
 * Handles two patterns of IU energy tiles:
 * <ul>
 *   <li>tiles that directly implement IEnergySink/IEnergySource (e.g. TileSolarPanel)</li>
 *   <li>Tiles that use the Energy component with delegates (IU machines)</li>
 * </ul>
 * Registered into FN's {@code TileEntityHandler.tileEnergyHandlers} via
 * {@link com.aeternal.iuadditions.mixins.fluxnetworks.MixinTileEntityHandler}.
 */
public class IUEFEnergyHandler implements ITileEnergyHandler {

    public static final IUEFEnergyHandler INSTANCE = new IUEFEnergyHandler();
    private static final Logger LOGGER = LogManager.getLogger("IUAdditions/FN");

    // Cache per tile class: Optional.empty() = no Energy field found
    private static final Map<Class<?>, Optional<Field>> ENERGY_FIELD_CACHE = new HashMap<>();

    private IUEFEnergyHandler() {}

    /**
     * Find the Energy component on an IU tile entity via cached reflection.
     * IU tiles store their Energy component as a public field.
     */
    private static Energy getEnergyComponent(TileEntity tile) {
        Optional<Field> opt = ENERGY_FIELD_CACHE.computeIfAbsent(tile.getClass(), clazz -> {
            for (Field f : clazz.getFields()) {
                if (f.getType() == Energy.class) {
                    LOGGER.info("Found Energy component field '{}' on {}", f.getName(), clazz.getName());
                    return Optional.of(f);
                }
            }
            return Optional.empty();
        });
        if (!opt.isPresent()) return null;
        try {
            return (Energy) opt.get().get(tile);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find the IEnergySink for a tile - checks direct implementation first,
     * then falls back to the Energy component delegate.
     */
    public static IEnergySink findSink(TileEntity tile) {
        if (tile instanceof IEnergySink) return (IEnergySink) tile;
        Energy energy = getEnergyComponent(tile);
        if (energy != null && energy.delegate instanceof IEnergySink) {
            return (IEnergySink) energy.delegate;
        }
        return null;
    }

    /**
     * Find the IEnergySource for a tile - checks direct implementation first,
     * then falls back to the Energy component delegate.
     */
    public static IEnergySource findSource(TileEntity tile) {
        if (tile instanceof IEnergySource) return (IEnergySource) tile;
        Energy energy = getEnergyComponent(tile);
        if (energy != null && energy.delegate instanceof IEnergySource) {
            return (IEnergySource) energy.delegate;
        }
        return null;
    }

    private static boolean isEnergyTile(TileEntity tile) {
        if (tile instanceof IEnergyTile) return true;
        return getEnergyComponent(tile) != null;
    }

    @Override
    public boolean hasCapability(@Nonnull TileEntity tile, EnumFacing side) {
        return isEnergyTile(tile);
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
        IEnergySink sink = findSink(tile);
        if (sink != null) {
            int ratio = Config.coefficientEfToRf;
            double demandedEF = sink.getDemandedEnergy();
            double offerEF = (double) amount / ratio;
            double toAddEF = Math.min(offerEF, demandedEF);
            if (toAddEF <= 0) return 0;
            if (!simulate) {
                sink.receiveEnergy(toAddEF);
            }
            return (long) (toAddEF * ratio);
        }
        return 0;
    }

    @Override
    public long removeEnergy(long amount, @Nonnull TileEntity tile, EnumFacing side) {
        IEnergySource source = findSource(tile);
        if (source != null) {
            int ratio = Config.coefficientEfToRf;
            double availableEF = source.canExtractEnergy();
            double requestEF = (double) amount / ratio;
            double toRemoveEF = Math.min(requestEF, availableEF);
            if (toRemoveEF <= 0) return 0;
            source.extractEnergy(toRemoveEF);
            return (long) (toRemoveEF * ratio);
        }
        return 0;
    }
}
