package net.yiran.tetrajs.core.mixins;

import net.yiran.tetrajs.core.IMaterialMultiplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import se.mickelus.tetra.module.data.*;
import se.mickelus.tetra.properties.AttributeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(value = MaterialVariantData.class, remap = false)
public abstract class MaterialVariantDataMixin extends VariantData {
    @Shadow
    public MaterialMultiplier extract;
    @Unique
    public boolean baseOnly = false;
    public boolean ingoreMaterialAttribute = false;
    public boolean ingoreMaterialEffects = false;
    public boolean useMagic = false;

    @Inject(method = "combine", at = @At("HEAD"), cancellable = true)
    private void combine(MaterialData material, CallbackInfoReturnable<VariantData> cir) {
        if (baseOnly || useMagic||ingoreMaterialAttribute||ingoreMaterialEffects) {
            cir.setReturnValue(TetraJS$combine(material));
        }
    }

    @Unique
    public VariantData TetraJS$combine(MaterialData material) {

        UniqueVariantData result = new UniqueVariantData();

        result.key = key + material.key;

        if (material.category != null) {
            result.category = material.category;
        }


        result.magicCapacity = Math.round(magicCapacity + Optional.ofNullable(extract.magicCapacity)
                .map(extracted -> extracted * material.magicCapacity)
                .orElse(0f));


        var mergeAttributes = new ArrayList<>(Arrays.asList(
                attributes,
                AttributeHelper.multiplyModifiers(extract.primaryAttributes, material.primary),
                AttributeHelper.multiplyModifiers(extract.secondaryAttributes, material.secondary),
                AttributeHelper.multiplyModifiers(extract.tertiaryAttributes, material.tertiary)
        ));
        if (!(baseOnly||ingoreMaterialAttribute)) {
            mergeAttributes.add(material.attributes);
        }
        if (useMagic) {
            mergeAttributes.add(AttributeHelper.multiplyModifiers(((IMaterialMultiplier) extract).getMagicAttributes(), result.magicCapacity));
        }
        result.attributes = AttributeHelper.collapseRound(AttributeHelper.merge(mergeAttributes));


        var mergeEffects = new ArrayList<>(Arrays.asList(
                effects,
                EffectData.multiply(extract.primaryEffects, material.primary, material.primary),
                EffectData.multiply(extract.secondaryEffects, material.secondary, material.secondary),
                EffectData.multiply(extract.tertiaryEffects, material.tertiary, material.tertiary)
        ));
        if (!(baseOnly||ingoreMaterialEffects)) {
            mergeEffects.add(material.effects);
        }
        if (useMagic) {
            mergeEffects.add(EffectData.multiply(((IMaterialMultiplier) extract).getMagicEffects(), result.magicCapacity, result.magicCapacity));
        }
        result.effects = EffectData.merge(mergeEffects);


        result.durability = Math.round(durability + Optional.ofNullable(extract.durability)
                .map(extracted -> extracted * material.durability)
                .orElse(0f));

        result.durabilityMultiplier = durabilityMultiplier + Optional.ofNullable(extract.durabilityMultiplier)
                .map(extracted -> extracted * material.durability)
                .orElse(0f);

        result.integrity = integrity + Optional.ofNullable(extract.integrity)
                .map(extracted -> extracted * (extracted > 0 ? material.integrityGain : material.integrityCost))
                .map(Math::round)
                .orElse(0);

        result.tools = ToolData.merge(Arrays.asList(
                tools,
                ToolData.multiply(extract.tools, material.toolLevel, material.toolEfficiency)
        ));

        result.aspects = AspectData.merge(aspects, material.aspects);

        if (material.rarity != null && (rarity == null || material.rarity.ordinal() > rarity.ordinal())) {
            result.rarity = material.rarity;
        } else {
            result.rarity = rarity;
        }

        result.glyph = Optional.ofNullable(extract.glyph)
                .map(glyph -> new GlyphData(glyph.textureLocation, glyph.textureX, glyph.textureY, material.tints.glyph))
                .orElse(glyph);

        List<String> availableTextures = Arrays.asList(extract.availableTextures);
        // note that map is run on one of the sub-streams
        result.models = Stream.concat(
                        Arrays.stream(models),
                        Arrays.stream(extract.models).map(model -> MaterialData.kneadModel(model, material, availableTextures)))
                .toArray(ModuleModel[]::new);

        if (tags == null) {
            result.tags = material.tags;
        } else if (material.tags == null) {
            result.tags = tags;
        } else {
            result.tags = Stream.concat(tags.stream(), material.tags.stream()).collect(Collectors.toSet());
        }

        return result;
    }
}
