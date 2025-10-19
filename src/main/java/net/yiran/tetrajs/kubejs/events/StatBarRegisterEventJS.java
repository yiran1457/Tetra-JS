package net.yiran.tetrajs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.yiran.tetrajs.kubejs.builders.statbar.AttributeStatBarBuilder;
import net.yiran.tetrajs.kubejs.builders.statbar.EffectStatBarBuilder;
import net.yiran.tetrajs.kubejs.builders.statbar.IStatBarBuilder;
import se.mickelus.tetra.effect.ItemEffect;

import java.util.ArrayList;
import java.util.List;

public class StatBarRegisterEventJS extends EventJS {
    public List<IStatBarBuilder<?>> statBars = new ArrayList<>();;

    public EffectStatBarBuilder registerEffectBar(ItemEffect effect) {
        var builder = new EffectStatBarBuilder(effect);
        statBars.add(builder);
        return builder;
    }

    public AttributeStatBarBuilder registerAttributeBar(Attribute attribute) {
        var builder = new AttributeStatBarBuilder(attribute);
        statBars.add(builder);
        return builder;
    }

    public List<IStatBarBuilder<?>> getStatBars() {
        return this.statBars;
    }
}
