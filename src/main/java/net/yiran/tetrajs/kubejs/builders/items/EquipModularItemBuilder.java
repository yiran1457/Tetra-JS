package net.yiran.tetrajs.kubejs.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.BaseModularItem;
import net.yiran.tetrajs.items.EquipModularItem;

public class EquipModularItemBuilder extends AbstractWithSlotModularItemBuilder<EquipModularItemBuilder> {
    public EquipmentSlot slot = EquipmentSlot.MAINHAND;
    public boolean providerAttribute = true;

    public EquipModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    public EquipModularItemBuilder tjs$setSlot(EquipmentSlot slot) {
        this.slot = slot;
        return this;
    }

    public EquipModularItemBuilder tjs$setProviderAttribute(boolean providerAttribute) {
        this.providerAttribute = providerAttribute;
        return this;
    }

    @Override
    public Item createObject() {
        return new EquipModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath, slot, providerAttribute);
    }
}
