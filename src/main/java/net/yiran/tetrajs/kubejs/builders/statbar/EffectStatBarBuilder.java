package net.yiran.tetrajs.kubejs.builders.statbar;

import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectEfficiency;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;

public class EffectStatBarBuilder extends AbstractStatBarBuilder<EffectStatBarBuilder> {
    public ItemEffect effect;
    public boolean addEfficiencyTooltip = false;
    public double base = 0;
    public double multiplier = 1;

    public EffectStatBarBuilder(ItemEffect effect) {
        this.effect = effect;
    }

    public EffectStatBarBuilder setAddEfficiencyTooltip(boolean addEfficiencyTooltip) {
        this.addEfficiencyTooltip = addEfficiencyTooltip;
        return this;
    }

    public EffectStatBarBuilder setBase(double base) {
        this.base = base;
        return this;
    }

    public EffectStatBarBuilder setMultiplier(double multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    @Override
    public String getKey() {
        return "tetra.stats." + effect.getKey();
    }

    @Override
    public void checkBuildPre() {
        if (getStatGetter() == null) {
            setStatGetter(new StatGetterEffectLevel(effect, multiplier, base));
        }
        if (statGetters.isEmpty()) {
            addIntegerTooltip(getStatGetter());
            if (addEfficiencyTooltip) {
                addOneDecimalTooltip(new StatGetterEffectEfficiency(effect));
            }
        }
    }
}
