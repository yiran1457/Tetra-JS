package net.yiran.tetrajs.kubejs.builders.statbar;

import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.ILabelGetter;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;

public interface IStatBarBuilder<THIS extends IStatBarBuilder<?>> {

    int getMax();

    THIS setMax(int max);

    int getMin();

    THIS setMin(int min);

    boolean getSegmented();

    THIS setSegmented(boolean segmented);

    boolean getSplit();

    THIS setSplit(boolean split);

    boolean getInverted();

    THIS setInverted(boolean inverted);

    ILabelGetter getLabelGetter();

    THIS setLabelGetter(ILabelGetter labelGetter);

    IStatGetter getStatGetter();

    THIS setStatGetter(IStatGetter statGetter);

    String getKey();

    default String getLangKey() {
        return getKey() + ".tooltip";
    }

    GuiStatBar build();

}
