package net.yiran.tetrajs.kubejs.builders.items;

import dev.latvian.mods.rhino.util.RemapPrefixForJS;
import net.minecraft.resources.ResourceLocation;
import net.yiran.tetrajs.util.SlotData;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWithSlotModularItemBuilder<T extends AbstractModularItemBuilder<T>> extends AbstractModularItemBuilder<T> {

    public List<SlotData> majorsData = new ArrayList<>();
    public List<SlotData> minorsData = new ArrayList<>();

    public AbstractWithSlotModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    public T tjs$addMajorSlot(String slotName, int x, int y) {
        this.majorsData.add(SlotData.create(slotName, x, y, true));
        return (T) this;
    }

    public T tjs$addMajorSlot(String slotName, int x, int y, boolean required) {
        this.majorsData.add(SlotData.create(slotName, x, y, required));
        return (T) this;
    }

    public T tjs$addMinorSlot(String slotName, int x, int y) {
        this.minorsData.add(SlotData.create(slotName, x, y));
        return (T) this;
    }

    public T tjs$addMinorSlot(String slotName, int x, int y, boolean required) {
        this.minorsData.add(SlotData.create(slotName, x, y, required));
        return (T) this;
    }

}
