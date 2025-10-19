package net.yiran.tetrajs.api;

import net.minecraft.world.item.ItemStack;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.module.schematic.OutcomeMaterial;

import java.util.Objects;

public class TetraJSUtils {
    public static TetraJSUtils INSTANCE = new TetraJSUtils();
    public boolean stackIsTetraMaterial(ItemStack stack){
        return DataManager.instance.materialData
                .getData()
                .values()
                .stream()
                .map(data->data.material)
                .map(OutcomeMaterial::getPredicate)
                .filter(Objects::nonNull)
                .anyMatch(itemPredicate -> itemPredicate.matches(stack));
    }
}
