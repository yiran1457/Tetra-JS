package net.yiran.tetrajs.compat.tetra_addition;

import dev.latvian.mods.kubejs.event.EventHandler;
import net.yiran.tetrajs.compat.tetra_addition.events.WorkbenchTileCraftEventJS;
import net.yiran.tetrajs.kubejs.events.TetraJSEvents;

public class TetraAdditionEvents {
    public static EventHandler Craft;

    public static void init() {
        Craft = TetraJSEvents.GROUP.common("workbenchCraft", () -> WorkbenchTileCraftEventJS.class);
    }
}
