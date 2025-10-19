package net.yiran.tetrajs.kubejs.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.ShieldModularItem;

public class ShieldModularItemBuilder extends AbstractWithSlotModularItemBuilder<ShieldModularItemBuilder> {
    public ShieldModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    @Override
    public Item createObject() {
        return new ShieldModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
    }
}
