package net.yiran.tetrajs.kubejs.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.CrossBowModularItem;

public class CrossBowModularItemBuilder extends AbstractWithSlotModularItemBuilder<CrossBowModularItemBuilder> {
    public CrossBowModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    @Override
    public Item createObject() {
        return new CrossBowModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
    }
}
