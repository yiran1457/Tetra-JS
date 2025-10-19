package net.yiran.tetrajs.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.stream.IntStream;

public record NbtSlotData(boolean required, boolean isMajor, String slot, int x, int y) {
    public static Codec<NbtSlotData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.optionalFieldOf("required",true).forGetter(NbtSlotData::required),
            Codec.BOOL.optionalFieldOf("isMajor",true).forGetter(NbtSlotData::isMajor),
            Codec.STRING.fieldOf("slot").forGetter(NbtSlotData::slot),
            Codec.INT.fieldOf("x").forGetter(NbtSlotData::x),
            Codec.INT.fieldOf("y").forGetter(NbtSlotData::y)
    ).apply(instance, NbtSlotData::new));
    public static Codec<List<NbtSlotData>> LCODEC = CODEC.listOf();

    public static NbtSlotData create(String slot, int x, int y,boolean isMajor,boolean required) {
        return new NbtSlotData(required, isMajor, slot, x, y);
    }

    public boolean isMinor() {
        return !isMajor;
    }

    public IntStream getPos() {
        return IntStream.of(x, y);
    }
}
