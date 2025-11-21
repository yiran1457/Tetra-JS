package net.yiran.tetrajs;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.yiran.tetrajs.kubejs.events.StatBarRegisterEventJS;
import net.yiran.tetrajs.kubejs.events.StatSorterRegisterEventJS;
import net.yiran.tetrajs.kubejs.events.TetraJSEvents;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.items.InitializableItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.List;

public class TetraJSClient {
    public static void itemClientInit(List<InitializableItem> items){
        for (InitializableItem item : items) {
            item.clientInit();
        }
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        var registerEvent = new StatBarRegisterEventJS();
        TetraJSEvents.StatBarRegister.post(registerEvent);
        registerEvent.getStatBars().forEach(builder -> {
            GuiStatBar arg = builder.build();
            WorkbenchStatsGui.addBar(arg);
            HoloStatsGui.addBar(arg);
        });
        TetraJSEvents.StatSorterRegister.post(new StatSorterRegisterEventJS());
    }
}
