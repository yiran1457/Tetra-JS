package net.yiran.tetrajs.api;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.yiran.tetrajs.util.StatGetterWrapper;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.getter.*;

public class StatBarHelper {
    public static StatBarHelper INSTANCE = new StatBarHelper();

    public IStatGetter effectLevel(ItemEffect effect, double multiplier, double base) {
        return new StatGetterEffectLevel(effect, multiplier, base);
    }

    public IStatGetter effectLevel(ItemEffect effect, double multiplier) {
        return effectLevel(effect, multiplier, 0);
    }

    public IStatGetter effectLevel(ItemEffect effect) {
        return effectLevel(effect, 1);
    }

    public IStatGetter effectEfficiency(ItemEffect effect, double multiplier, double base) {
        return new StatGetterEffectEfficiency(effect, multiplier, base);
    }

    public IStatGetter effectEfficiency(ItemEffect effect, double multiplier) {
        return effectEfficiency(effect, multiplier, 0);
    }

    public IStatGetter effectEfficiency(ItemEffect effect) {
        return effectEfficiency(effect, 1);
    }

    public IStatGetter sum(double amount, IStatGetter... statGetter) {
        return new StatGetterAdd(amount, statGetter);
    }

    public IStatGetter sum(IStatGetter... statGetter) {
        return sum(0, statGetter);
    }

    public IStatGetter multiply(double amount, IStatGetter... statGetter) {
        return new StatGetterMultiply(amount, statGetter);
    }

    public IStatGetter multiply(IStatGetter... statGetter) {
        return multiply(0, statGetter);
    }

    public IStatGetter attribute(Attribute attribute, boolean ignoreBase, boolean ignoreBonuses, double offset) {
        return new StatGetterAttribute(attribute, ignoreBase, ignoreBonuses, offset);
    }

    public IStatGetter attribute(Attribute attribute, boolean ignoreBase, boolean ignoreBonuses) {
        return attribute(attribute, ignoreBase, ignoreBonuses, 0);
    }

    public IStatGetter attribute(Attribute attribute, boolean ignoreBase) {
        return attribute(attribute, ignoreBase, false);
    }

    public IStatGetter attribute(Attribute attribute) {
        return attribute(attribute, false);
    }

    public IStatGetter attributeAddition(Attribute attribute) {
        return new StatGetterAttributeAddition(attribute);
    }

    public IStatGetter attributeMultiply(Attribute attribute) {
        return sum(-1, new StatGetterAttributeMultiply(attribute));
    }

    public IStatGetter integrity() {
        return new StatGetterIntegrity();
    }

    public IStatGetter magicCapacity(IStatGetter... statGetter) {
        return new StatGetterMagicCapacity();
    }

    public IStatGetter showWrapper(IStatGetter statGetter, StatGetterWrapper.ShowCheck showCheck) {
        return new StatGetterWrapper(statGetter, showCheck);
    }

}
