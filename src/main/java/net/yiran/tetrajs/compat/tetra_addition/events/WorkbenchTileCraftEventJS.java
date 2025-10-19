package net.yiran.tetrajs.compat.tetra_addition.events;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.yiran.tetra_addition.WorkbenchTileCraftEvent;
import net.yiran.tetrajs.compat.tetra_addition.TetraAdditionEvents;
import se.mickelus.tetra.blocks.workbench.WorkbenchTile;
import se.mickelus.tetra.module.schematic.UpgradeSchematic;

public class WorkbenchTileCraftEventJS extends EventJS {
    public ItemStack targetStack;
    public ItemStack upgradedStack;
    public Player player;
    public Level level;
    public WorkbenchTile workbenchTile;
    public ItemStack[] materials;
    public ItemStack[] materialsAltered;
    public UpgradeSchematic currentSchematic;
    public String currentSlot;

    public WorkbenchTileCraftEventJS(WorkbenchTileCraftEvent event) {
        this.targetStack = event.targetStack;
        this.upgradedStack = event.upgradedStack;
        this.player = event.player;
        this.level = event.level;
        this.workbenchTile = event.workbenchTile;
        this.materials = event.materials;
        this.materialsAltered = event.materialsAltered;
        this.currentSchematic = event.currentSchematic;
        this.currentSlot = event.currentSlot;
    }

    public ItemStack getTargetStack() {
        return targetStack;
    }

    public ItemStack getUpgradedStack() {
        return upgradedStack;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }

    public WorkbenchTile getWorkbenchTile() {
        return workbenchTile;
    }

    public ItemStack[] getMaterials() {
        return materials;
    }

    public ItemStack[] getMaterialsAltered() {
        return materialsAltered;
    }

    public UpgradeSchematic getCurrentSchematic() {
        return currentSchematic;
    }

    public String getCurrentSlot() {
        return currentSlot;
    }

    @SubscribeEvent
    public static void onCraft(WorkbenchTileCraftEvent event) {
        TetraAdditionEvents.Craft.post(event.level.isClientSide() ? ScriptType.CLIENT : ScriptType.SERVER, new WorkbenchTileCraftEventJS(event));
    }
}
