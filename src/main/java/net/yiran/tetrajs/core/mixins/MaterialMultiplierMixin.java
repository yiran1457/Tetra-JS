package net.yiran.tetrajs.core.mixins;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.yiran.tetrajs.core.IMaterialMultiplier;
import org.spongepowered.asm.mixin.Mixin;
import se.mickelus.tetra.module.data.EffectData;
import se.mickelus.tetra.module.data.MaterialMultiplier;

@Mixin(value = MaterialMultiplier.class, remap = false)
public class MaterialMultiplierMixin implements IMaterialMultiplier {
    public Multimap<Attribute, AttributeModifier> magicAttributes;
    public EffectData magicEffects;

    public Multimap<Attribute, AttributeModifier> getMagicAttributes() {
        return magicAttributes;
    }

    public EffectData getMagicEffects() {
        return magicEffects;
    }
}
