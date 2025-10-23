package net.yiran.tetrajs.kubejs.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.TwoHandedModularItem;

public class TwoHandedModularItemBuilder extends AbstractWithSlotModularItemBuilder<TwoHandedModularItemBuilder> {
    public TwoHandedModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    @Override
    public Item createObject() {
        return new TwoHandedModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
    }
}
