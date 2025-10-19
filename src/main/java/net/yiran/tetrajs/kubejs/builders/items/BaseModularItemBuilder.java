package net.yiran.tetrajs.kubejs.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.BaseModularItem;

public class BaseModularItemBuilder extends AbstractWithSlotModularItemBuilder<BaseModularItemBuilder> {
    public BaseModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    @Override
    public Item createObject() {
        return new BaseModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
    }
}
