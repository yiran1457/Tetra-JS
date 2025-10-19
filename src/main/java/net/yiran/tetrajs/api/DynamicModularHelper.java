package net.yiran.tetrajs.api;

import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.yiran.tetrajs.util.NbtSlotData;
import se.mickelus.tetra.items.modular.IModularItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DynamicModularHelper {
    public static DynamicModularHelper INSTANCE = new DynamicModularHelper();

    public void addSlot(ItemStack stack, NbtSlotData slot) {
        var slotsData = getSlotData(stack);
        slotsData.put(slot.slot(), slot);
        saveSlotData(stack, new ArrayList<>(slotsData.values()));
    }

    public void addSlot(ItemStack stack, String slot,int x, int y,boolean isMajor,boolean required) {
        addSlot(stack,NbtSlotData.create(slot,x,y,isMajor,required));
    }
    public void addSlot(ItemStack stack, String slot,int x, int y,boolean isMajor) {
        addSlot(stack,NbtSlotData.create(slot,x,y,isMajor,isMajor));
    }

    public void removeSlot(ItemStack stack,String slot) {
        var slotsData = getSlotData(stack);
        slotsData.remove(slot);
        saveSlotData(stack, new ArrayList<>(slotsData.values()));
    }

    public void clearSlot(ItemStack stack) {
        saveSlotData(stack, new ArrayList<>());
    }

    public Map<String, NbtSlotData> getSlotData(ItemStack stack) {
        var tag = stack.getOrCreateTag();
        var list = tag.getList("extraslots", Tag.TAG_COMPOUND);
        return NbtSlotData.LCODEC.parse(NbtOps.INSTANCE, list)
                .result()
                .get()
                .stream()
                .collect(Collectors.toMap(NbtSlotData::slot, Function.identity()));
    }

    public void saveSlotData(ItemStack stack, List<NbtSlotData> slots) {
        var tag = stack.getOrCreateTag();
        tag.put("extraslots", NbtSlotData.LCODEC.encodeStart(NbtOps.INSTANCE, slots).result().get());
        IModularItem.updateIdentifier(tag);
    }
}
