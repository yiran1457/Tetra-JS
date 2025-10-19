package net.yiran.tetrajs.compat.curios.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.compat.curios.items.CuriosModularItem;
import net.yiran.tetrajs.compat.curios.items.DynamicCuriosModularItem;
import net.yiran.tetrajs.kubejs.builders.items.AbstractModularItemBuilder;
import net.yiran.tetrajs.kubejs.builders.items.AbstractWithSlotModularItemBuilder;

public class DynamicCuriosModularItemBuilder extends AbstractModularItemBuilder<DynamicCuriosModularItemBuilder> {
    public boolean providerAttribute = true;

    public DynamicCuriosModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    public DynamicCuriosModularItemBuilder tjs$setProviderAttribute(boolean providerAttribute) {
        this.providerAttribute = providerAttribute;
        return this;
    }

    public DynamicCuriosModularItemBuilder tjs$addCuriosSlot(String curiosSlot) {
        this.tag(new ResourceLocation("curios", curiosSlot));
        return this;
    }

    @Override
    public Item createObject() {
        return new DynamicCuriosModularItem(id, canHone, honeBase, honeIntegrityMultiplier, synergiesPath, providerAttribute);
    }

}
