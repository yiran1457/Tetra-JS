package net.yiran.tetrajs.compat.curios.items;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMultimap;
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
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CuriosModularItem extends BaseModularItem implements ICurioItem {
    public boolean providerAttribute;

    public CuriosModularItem(ResourceLocation itemID, List<SlotData> majorsData, List<SlotData> minorsData, boolean canHone, int honeBase, int honeIntegrityMultiplier, String[] synergiesPath, boolean providerAttribute) {
        super(itemID, majorsData, minorsData, canHone, honeBase, honeIntegrityMultiplier, synergiesPath);
        this.providerAttribute = providerAttribute;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        if (!providerAttribute) {
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

}
