package net.yiran.tetrajs.core.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.yiran.tetrajs.util.StaticMapGetterHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.mickelus.tetra.aspect.ItemAspect;

import java.util.Map;

@Mixin(value = ItemAspect.class,remap = false)
public class ItemAspectMixin {
    @Shadow
    @Final
    private static Map<String, ItemAspect> map;

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void init(CallbackInfo ci) {
        StaticMapGetterHelper.AspectsMap = map;
    }
}
