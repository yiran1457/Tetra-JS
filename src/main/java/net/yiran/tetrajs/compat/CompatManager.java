package net.yiran.tetrajs.compat;

import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.yiran.tetra_addition.TetraAddition;
import net.yiran.tetrajs.TetraJS;
import net.yiran.tetrajs.compat.curios.CuriosHelper;
import net.yiran.tetrajs.compat.curios.builders.items.CuriosModularItemBuilder;
import net.yiran.tetrajs.compat.curios.builders.items.DynamicCuriosModularItemBuilder;
import net.yiran.tetrajs.compat.tetra_addition.TetraAdditionEvents;
import net.yiran.tetrajs.compat.tetra_addition.events.WorkbenchTileCraftEventJS;
import top.theillusivec4.curios.Curios;

import java.util.function.BiConsumer;

public class CompatManager {
    public static boolean CuriosLoaded = ModList.get().isLoaded(Curios.MODID);
    public static boolean TetraAdditionLoaded = ModList.get().isLoaded(TetraAddition.MODID);

    public static void init(IEventBus modEventBus) {
        TetraJS.LOGGER.debug("TetraJS Compat Init");
        if (TetraAdditionLoaded) {
            TetraAdditionEvents.init();
            MinecraftForge.EVENT_BUS.register(WorkbenchTileCraftEventJS.class);
        }
    }

    public static void registerCompatItemBuilder(RegistryInfo<Item> registryInfo) {
        //TetraJS.LOGGER.debug("Registering TetraJS Compat ItemBuilder");
        if (CuriosLoaded) {
            registryInfo.addType("TetraJS:CuriosModularItem", CuriosModularItemBuilder.class, CuriosModularItemBuilder::new);
            registryInfo.addType("TetraJS:DynamicCuriosModularItem", DynamicCuriosModularItemBuilder.class, DynamicCuriosModularItemBuilder::new);
        }
    }

    public static void registerCompatBindings(BiConsumer<String, Object> register) {
        //TetraJS.LOGGER.debug("Registering TetraJS Compat Bindings");
        if (CuriosLoaded) {
            register.accept("CuriosHelper", CuriosHelper.getInstance());
        }
    }
}
