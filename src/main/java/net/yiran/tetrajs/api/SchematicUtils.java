package net.yiran.tetrajs.api;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolAction;
import se.mickelus.tetra.TetraToolActions;
import se.mickelus.tetra.module.schematic.*;

public class SchematicUtils {
    public static SchematicUtils INSTANCE = new SchematicUtils();

    @Info("获取原理图的经验消耗")
    public int getExperienceCost(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials, String slot) {
        return schematic.getExperienceCost(targetStack, materials, slot);
    }

    @Info("获取原理图所需的锤等级")
    public int getRequiredHammerLevel(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials) {
        return getRequiredToolLevel(schematic, targetStack, materials, TetraToolActions.hammer);
    }

    @Info("获取原理图所需的指定锤等级")
    public int getRequiredToolLevel(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials, ToolAction toolAction) {
        return schematic.getRequiredToolLevel(targetStack, materials, toolAction);
    }

    @Info("获取最高的工具等级需求")
    public int getMaxRequiredToolLevel(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials) {
        int max = 0;
        for (int value : schematic.getRequiredToolLevels(targetStack, materials).values()) {
            max = Math.max(max, value);
        }
        return max;
    }

    @Info("获取全部工具等级需求总和")
    public int getTotalRequiredToolLevel(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials) {
        int total = 0;
        for (int value : schematic.getRequiredToolLevels(targetStack, materials).values()) {
            total += value;
        }
        return total;
    }

    @Info("获取原理图的类型(other,improvement,minor,major)")
    public SchematicType getType(UpgradeSchematic schematic) {
        return schematic.getType();
    }

    @Info("获取原理图的稀有度(hone,temporary,basic")
    public SchematicRarity getRarity(UpgradeSchematic schematic) {
        return schematic.getRarity();
    }

    @Info("判断此原理图是否会替换部件(应该是)")
    public boolean willReplaced(UpgradeSchematic schematic, ItemStack targetStack, ItemStack[] materials, String slot) {
        return schematic.willReplace(targetStack, materials, slot);
    }

    @Info("判断此原理图是否是打磨")
    public boolean isHoning(UpgradeSchematic schematic) {
        return schematic.isHoning();
    }

    @Info("判断此原理图是否为ConfigSchematic(一般你数据包生成的就是这类)")
    public boolean isConfigSchematic(UpgradeSchematic schematic) {
        return schematic instanceof ConfigSchematic;
    }

    @Info("判断此原理图是否是修复(修补耐久的那个)")
    public boolean isRepairSchematic(UpgradeSchematic schematic) {
        return schematic instanceof RepairSchematic;
    }

    @Info("判断此原理图是否是附魔原理图(消耗附魔书附魔的)")
    public boolean isEnchantSchematic(UpgradeSchematic schematic) {
        return schematic instanceof BookEnchantSchematic;
    }
}
