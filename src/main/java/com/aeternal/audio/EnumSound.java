package com.aeternal.audio;

import com.denfop.audio.TypePath;
import net.minecraft.util.SoundEvent;

public enum EnumSound {
    assembler(TypePath.Machines, "assembler"),
    InterruptOne(TypePath.Machines, "interrupt_one");

    private final TypePath typePath;
    private final String nameSounds;
    private SoundEvent soundEvent;

    private EnumSound(TypePath typePath, String nameSounds) {
        this.typePath = typePath;
        this.nameSounds = nameSounds;
    }

    public static SoundEvent getSondFromString(String sound) {
        for(EnumSound sound1 : values()) {
            if (sound1.nameSounds.toLowerCase().trim().equals(sound.trim().toLowerCase())) {
                return sound1.soundEvent;
            }
        }

        return null;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public void setSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public String getNameSounds() {
        return this.nameSounds;
    }

    public String getSoundName() {
        return this.typePath.name() + "." + this.nameSounds;
    }
}
