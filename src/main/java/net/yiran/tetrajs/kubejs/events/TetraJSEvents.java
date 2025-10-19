package net.yiran.tetrajs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface TetraJSEvents {
    EventGroup GROUP = EventGroup.of("TetraJSEvents");
    EventHandler StatBarRegister = GROUP.client("registerStatBar",()-> StatBarRegisterEventJS.class);
    EventHandler EnchantAspectRegister = GROUP.startup("registerEnchantAspect",()-> EnchantAspectRegisterEventJS.class);
    EventHandler StatSorterRegister = GROUP.client("registerStatSorter",()-> StatSorterRegisterEventJS.class);

}
