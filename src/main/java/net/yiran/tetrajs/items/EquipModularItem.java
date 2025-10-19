package net.yiran.tetrajs.items;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.yiran.tetrajs.util.SlotData;
import se.mickelus.tetra.properties.AttributeHelper;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EquipModularItem extends BaseModularItem implements Equipable {
    public EquipmentSlot slot;
    public boolean providerAttribute;

    public EquipModularItem(ResourceLocation itemID, List<SlotData> majorsData, List<SlotData> minorsData, boolean canHone, int honeBase, int honeIntegrityMultiplier, String[] synergiesPath, EquipmentSlot slot, boolean providerAttribute) {
        super(itemID, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
        this.slot = slot;
        this.providerAttribute = providerAttribute;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack) {
        if (!providerAttribute || this.isBroken(itemStack)) {
            return AttributeHelper.emptyMap;
        } else if (slot == this.slot) {
            return Equip$fixIdentifiers(slot, this.getAttributeModifiersCached(itemStack));
        } else {
            return AttributeHelper.emptyMap;
        }
    }

    public Multimap<Attribute, AttributeModifier> fixIdentifiers(Multimap<Attribute, AttributeModifier> modifiers) {
        return modifiers;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return slot;
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return slot;
    }

    public Multimap<Attribute, AttributeModifier> Equip$fixIdentifiers(EquipmentSlot slot, Multimap<Attribute, AttributeModifier> modifiers) {
        return modifiers.entries()
                .stream()
                .collect(Multimaps.toMultimap(
                        Map.Entry::getKey,
                        (entry) ->
                                new AttributeModifier(
                                        UUID.nameUUIDFromBytes((entry.getValue().getName() + slot.getName()).getBytes()),
                                        entry.getValue().getName() + slot.getName(),
                                        entry.getValue().getAmount(),
                                        entry.getValue().getOperation()
                                ),
                        ArrayListMultimap::create));
    }
}
