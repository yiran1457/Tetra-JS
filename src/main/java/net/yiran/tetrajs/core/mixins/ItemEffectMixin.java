package net.yiran.tetrajs.core.mixins;

import net.yiran.tetrajs.util.StaticMapGetterHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.mickelus.tetra.effect.ItemEffect;

import java.util.Map;

@Mixin(value = ItemEffect.class, remap = false)
public class ItemEffectMixin {
    @Shadow
    @Final
    private static Map<String, ItemEffect> effectMap;

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void init(CallbackInfo ci) {
        StaticMapGetterHelper.EffectMap = effectMap;
    }
}
