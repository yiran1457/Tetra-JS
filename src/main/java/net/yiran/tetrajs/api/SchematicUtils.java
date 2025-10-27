package net.yiran.tetrajs.api;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolAction;
import se.mickelus.tetra.TetraToolActions;
import se.mickelus.tetra.module.schematic.SchematicRarity;
import se.mickelus.tetra.module.schematic.SchematicType;
import se.mickelus.tetra.module.schematic.UpgradeSchematic;

public class SchematicUtils {
    public static SchematicUtils INSTANCE = new SchematicUtils();

    public int getExperienceCost(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials, String slot) {
        return schematic.getExperienceCost(targetStack, materials, slot);
    }

    public int getRequiredHammerLevel(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials) {
        return getRequiredToolLevel(schematic, targetStack, materials, TetraToolActions.hammer);
    }

    public int getRequiredToolLevel(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials, ToolAction toolAction) {
        return schematic.getRequiredToolLevel(targetStack, materials, toolAction);
    }

    public SchematicType getType(UpgradeSchematic schematic) {
        return schematic.getType();
    }

    public SchematicRarity getRarity(UpgradeSchematic schematic) {
        return schematic.getRarity();
    }

    public boolean willReplaced(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials, String slot) {
        return schematic.willReplace(targetStack, materials, slot);
    }
}
