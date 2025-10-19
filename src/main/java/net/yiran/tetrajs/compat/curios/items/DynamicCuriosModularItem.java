package net.yiran.tetrajs.compat.curios.items;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.yiran.tetrajs.compat.curios.CuriosHelper;
import net.yiran.tetrajs.items.DynamicModularItem;
import se.mickelus.tetra.properties.AttributeHelper;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class DynamicCuriosModularItem extends DynamicModularItem implements ICurioItem {
    public boolean providerAttribute;

    public DynamicCuriosModularItem(ResourceLocation itemID, boolean canHone, int honeBase, int honeIntegrityMultiplier, String[] synergiesPath, boolean providerAttribute) {
        super(itemID, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
        this.providerAttribute = providerAttribute;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        if (!providerAttribute) {
            return AttributeHelper.emptyMap;
        }
        return CuriosHelper.Curios$fixIdentifiers(slotContext, this.getAttributeModifiersCached(stack));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack) {
        return AttributeHelper.emptyMap;
    }

    public Multimap<Attribute, AttributeModifier> fixIdentifiers(Multimap<Attribute, AttributeModifier> modifiers) {
        return modifiers;
    }

}
