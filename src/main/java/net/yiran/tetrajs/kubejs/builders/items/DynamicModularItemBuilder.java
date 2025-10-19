package net.yiran.tetrajs.kubejs.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.DynamicModularItem;

public class DynamicModularItemBuilder extends AbstractModularItemBuilder<DynamicModularItemBuilder> {

    public DynamicModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    @Override
    public Item createObject() {
        return new DynamicModularItem(id, canHone, honeBase, honeIntegrityMultiplier,synergiesPath);
    }
}
