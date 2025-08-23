package com.aeternal.iuadditions.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BaseUpgradeSystemApplier {
    public static final Logger LOGGER = LogManager.getLogger("IUAdditionsMixins");

    public static volatile int CORE_MAX = 1;

    public static volatile int NEUTRON_MAX = 1;

    public static volatile boolean DEBUG = false;

    private BaseUpgradeSystemApplier() {}

    /**
     * Apply values from Config right after it’s loaded (e.g., in preInit).
     * @param coreMax    desired max for core modifier; clamped to >= 1
     * @param neutronMax desired max for neutronium modifier; clamped to >= 1
     * @param debug      Config.DebugEnum
     */
    public static void applyFromConfig(int coreMax, int neutronMax, boolean debug) {
        CORE_MAX = Math.max(1, coreMax);
        NEUTRON_MAX = Math.max(1, neutronMax);
        DEBUG = debug;

        if (DEBUG) {
            LOGGER.info("[IUAdditionsMixins] Applied config: CORE_MAX={}, NEUTRON_MAX={}",
                    CORE_MAX, NEUTRON_MAX);
        }
    }
}
