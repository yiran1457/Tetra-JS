package net.yiran.tetrajs.compat.curios.items;

import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.yiran.tetrajs.compat.curios.CuriosHelper;
import net.yiran.tetrajs.items.BaseModularItem;
import net.yiran.tetrajs.util.SlotData;
import se.mickelus.tetra.properties.AttributeHelper;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class CuriosModularItem extends BaseModularItem implements ICurioItem {
    public boolean providerAttribute;
    public boolean isUnbreakable;

    public CuriosModularItem(ResourceLocation itemID, List<SlotData> majorsData, List<SlotData> minorsData, boolean canHone, int honeBase, int honeIntegrityMultiplier, String[] synergiesPath, boolean providerAttribute, boolean isUnbreakable) {
        super(itemID, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
        this.providerAttribute = providerAttribute;
        this.isUnbreakable = isUnbreakable;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        if (!providerAttribute || isBroken(stack)) {
            return AttributeHelper.emptyMap;
        }
        return CuriosHelper.Curios$fixIdentifiers(slotContext, getAttributeModifiersCached(stack));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack) {
        return AttributeHelper.emptyMap;
    }

    public Multimap<Attribute, AttributeModifier> fixIdentifiers(Multimap<Attribute, AttributeModifier> modifiers) {
        return modifiers;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return !isUnbreakable && super.isDamageable(stack);
    }

    @Override
    public int getMaxDamage(ItemStack itemStack) {
        return isUnbreakable ? -1 : super.getMaxDamage(itemStack);
    }

    @Override
    public boolean isBroken(int damage, int maxDamage) {
        return !isUnbreakable && super.isBroken(damage, maxDamage);
    }
}
