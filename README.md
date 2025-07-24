IU Additions is an addon for [Industrial Upgrade](https://modrinth.com/mod/industrialupgrade) that adds a few new solar panels as integrations with other mods such as DivineRPG, Forestry, and AstralSorcery, as well as new items and Converters.

Converters added:

Draconic Evolution Integration:

Spectral Converter - bidirectional EF to RF. Capable of transferring energy directly to and from DE energy core bypassing any limiters. Place it near your core (default range 16 blocks), ensure that the core is built and active and wait for the converter to detect it, choose the mode in which you want to operate and you're good to go.

Spectral Quantum Converter bidirectional QE to RF, the rest of the story is the same as it's EF version.

DE converters require [Draconic Evolution](https://www.curseforge.com/minecraft/mc-mods/draconic-evolution) and [PowerUtilities\[IU\]](https://www.curseforge.com/minecraft/mc-mods/power-utilities-iu) to be installed.

Botania Integration:

Mana converter - bidirectional Mana to EF.

When in MN=>EF mode: Draws mana from adjacent mana pools, mana buffers, other converters, except for the top side. Mana pools supported: Botania, Botaniverse, Botanic Additions. Mana buffers supported: Extra Botany

Accepts mana from spreaders

Can't accept mana from sparks

It then converts the mana it received according to I:"coefficient mana to ef" in the config into energy for further use.

By default 1 Mana yields 50EF

When EF=>MN mode:

Accepts EF energy and converts it into mana using this ratio I:"coefficient ef to mana" in the config.

The mana can then be extracted via spark with recessive augment, placed on top of the converter. As well as exported to adjacent mana pools, mana buffers, other converters, only from the top side.

The transfer rate to adjacent blocks depends on converter's power tier that can be increased via transformer upgrades, the higher the tier - the higher the potential I/O. It is also capped by internal energy/mana buffer, that can be increased with Energy Upgrades

The converter can be configured to only use either mode, both, or neither in the config. By default it allows only MN=>EF mode because of my view on balance. In my opinion as well as botania's devs the whole point of the mod is to use it's generating flowers, not just get unlimited mana from some other energy. But you are free to do whatever you wish.

By default 50EF yield 1 Mana

Botania converters requires [Botania](https://www.curseforge.com/minecraft/mc-mods/botania) and [PowerUtilities\[IU\]](https://www.curseforge.com/minecraft/mc-mods/power-utilities-iu) to be installed. [Botanic Additions](https://www.curseforge.com/minecraft/mc-mods/botanic-additions), [Extra Botany](https://www.curseforge.com/minecraft/mc-mods/extrabotany) and [Botaniverse](https://www.curseforge.com/minecraft/mc-mods/botaniverse) are optional, although i highly recommend you throw [Botaniverse](https://www.curseforge.com/minecraft/mc-mods/botaniverse) in your pack as well.

The addon was primarily designed for use with the [NewDawn](https://www.curseforge.com/minecraft/modpacks/nd-new-dawn) modpack.
