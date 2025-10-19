package net.yiran.tetrajs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import se.mickelus.tetra.aspect.ItemAspect;
import se.mickelus.tetra.aspect.TetraEnchantmentHelper;

public class EnchantAspectRegisterEventJS extends EventJS {
    public void register(ItemAspect aspect, EnchantmentCategory... categories) {
        TetraEnchantmentHelper.registerMapping(aspect,
                new TetraEnchantmentHelper.EnchantmentRules(
                        "additions/" + aspect.getKey(), "exclusions/" + aspect.getKey(),
                        categories
                ));
    }
}
