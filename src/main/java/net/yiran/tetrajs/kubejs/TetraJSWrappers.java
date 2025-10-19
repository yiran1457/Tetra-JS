package net.yiran.tetrajs.kubejs;

import dev.latvian.mods.rhino.mod.util.NBTUtils;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.registries.ForgeRegistries;
import net.yiran.tetrajs.util.NbtSlotData;
import se.mickelus.tetra.aspect.ItemAspect;
import se.mickelus.tetra.effect.ItemEffect;

public class TetraJSWrappers {
    public static ItemEffect toItemEffect(Object o) {
        if (o instanceof ItemEffect) {
            return (ItemEffect) o;
        }
        if (o instanceof String) {
            return ItemEffect.get((String) o);
        }
        return ItemEffect.get(o.toString());
    }

    public static ToolAction toToolAction(Object o) {
        if (o instanceof ToolAction) {
            return (ToolAction) o;
        }
        if (o instanceof String) {
            return ToolAction.get((String) o);
        }
        return ToolAction.get(o.toString());
    }

    public static NbtSlotData toNbtSlotData(Object o) {
        if (o instanceof NbtSlotData) {
            return (NbtSlotData) o;
        }
        return NbtSlotData.CODEC.parse(NbtOps.INSTANCE, NBTUtils.toTagCompound(o)).result().get();
    }

    public static Attribute toAttribute(Object o) {
        if (o instanceof Attribute) {
            return (Attribute) o;
        }
        if (o instanceof ResourceLocation) {
            return ForgeRegistries.ATTRIBUTES.getValue((ResourceLocation) o);
        }
        return ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(o.toString()));
    }
    public static ItemAspect toItemAspect(Object o) {
        if (o instanceof ItemAspect) {
            return (ItemAspect) o;
        }
        if (o instanceof String) {
            return ItemAspect.get((String) o);
        }
        return ItemAspect.get(o.toString());
    }
}
