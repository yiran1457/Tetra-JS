package net.yiran.tetrajs.compat.curios.builders.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.compat.curios.items.CuriosModularItem;
import net.yiran.tetrajs.kubejs.builders.items.AbstractWithSlotModularItemBuilder;

public class CuriosModularItemBuilder extends AbstractWithSlotModularItemBuilder<CuriosModularItemBuilder> {
    public boolean providerAttribute = true;

    public CuriosModularItemBuilder(ResourceLocation itemID) {
        super(itemID);
    }

    public CuriosModularItemBuilder tjs$setProviderAttribute(boolean providerAttribute) {
        this.providerAttribute = providerAttribute;
        return this;
    }

    public CuriosModularItemBuilder tjs$addCuriosSlot(String curiosSlot) {
        this.tag(new ResourceLocation("curios", curiosSlot));
        return this;
    }

    @Override
    public Item createObject() {
        return new CuriosModularItem(id, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath, providerAttribute);
    }

}
