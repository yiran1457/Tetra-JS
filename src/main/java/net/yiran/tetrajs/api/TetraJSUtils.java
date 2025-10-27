package net.yiran.tetrajs.api;

import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.aspect.ItemAspect;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.module.ItemModule;
import se.mickelus.tetra.module.ItemModuleMajor;
import se.mickelus.tetra.module.schematic.OutcomeMaterial;

import java.util.Objects;
import java.util.Set;

public class TetraJSUtils {
    public static TetraJSUtils INSTANCE = new TetraJSUtils();

    public boolean stackIsTetraMaterial(ItemStack stack) {
        return DataManager.instance.materialData
                .getData()
                .values()
                .stream()
                .map(data -> data.material)
                .map(OutcomeMaterial::getPredicate)
                .filter(Objects::nonNull)
                .anyMatch(itemPredicate -> itemPredicate.matches(stack));
    }

    public int getImprovementLevel(ItemStack stack, String slot, String improvement) {
        if (stack.getItem() instanceof IModularItem modularItem) {
            if (modularItem.getModuleFromSlot(stack, slot) instanceof ItemModuleMajor moduleMajor) {
                return moduleMajor.getImprovementLevel(stack, improvement);
            }
        }
        return -1;
    }

    public void addImprovementLevel(ItemStack stack, String slot, String improvement, int level) {
        if (stack.getItem() instanceof IModularItem modularItem) {
            if (modularItem.getModuleFromSlot(stack, slot) instanceof ItemModuleMajor moduleMajor) {
                moduleMajor.addImprovement(stack, improvement, level);
            }
        }
    }

    public int getAspectLevel(ItemStack stack, String slot, ItemAspect aspect) {
        if (stack.getItem() instanceof IModularItem modularItem) {
            ItemModule itemModule = modularItem.getModuleFromSlot(stack, slot);
            if (itemModule != null) {
                return itemModule.getAspects(stack).getLevel(aspect);
            }
        }
        return -1;
    }

    public Set<ItemAspect> getAspects(ItemStack stack, String slot, ItemAspect aspect) {
        if (stack.getItem() instanceof IModularItem modularItem) {
            ItemModule itemModule = modularItem.getModuleFromSlot(stack, slot);
            if (itemModule != null) {
                return itemModule.getAspects(stack).getValues();
            }
        }
        return Set.of();
    }

    public boolean hasAspectLevel(ItemStack stack, String slot, ItemAspect aspect) {
        if (stack.getItem() instanceof IModularItem modularItem) {
            ItemModule itemModule = modularItem.getModuleFromSlot(stack, slot);
            if (itemModule != null) {
                return itemModule.hasAspect(stack,aspect);
            }
        }
        return false;
    }


}
