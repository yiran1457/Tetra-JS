package net.yiran.tetrajs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import se.mickelus.tetra.gui.stats.getter.IStatFormat;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.StatFormat;
import se.mickelus.tetra.gui.stats.sorting.BasicStatSorter;
import se.mickelus.tetra.gui.stats.sorting.StatSorters;

public class StatSorterRegisterEventJS extends EventJS {
    public void registerBasicSorter(IStatGetter getter, String name, StatFormats statFormat) {
        StatSorters.staticSorters.add(new BasicStatSorter(getter, name, statFormat.statFormat));
    }

    public enum StatFormats {
        noDecimal(StatFormat.noDecimal),
        oneDecimal(StatFormat.oneDecimal),
        twoDecimal(StatFormat.twoDecimal),
        abbreviate(StatFormat.abbreviate);
        public final IStatFormat statFormat;

        StatFormats(IStatFormat statFormat) {
            this.statFormat = statFormat;
        }
    }
}
