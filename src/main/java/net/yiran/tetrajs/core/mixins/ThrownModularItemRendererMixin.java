package net.yiran.tetrajs.core.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.yiran.tetrajs.items.DynamicModularItem;
import net.yiran.tetrajs.items.ITetraJSItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.mickelus.tetra.items.modular.ThrownModularItemEntity;
import se.mickelus.tetra.items.modular.ThrownModularItemRenderer;

@Mixin(ThrownModularItemRenderer.class)
public abstract class ThrownModularItemRendererMixin {
    @Shadow(remap = false)
    protected abstract void transformSingleHeaded(ThrownModularItemEntity entity, float partialTicks, PoseStack matrixStack);

    @Inject(method = "render(Lse/mickelus/tetra/items/modular/ThrownModularItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getInstance()Lnet/minecraft/client/Minecraft;", ordinal = 0))
    private void r(ThrownModularItemEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int packedLightIn, CallbackInfo ci, @Local(ordinal = 0) Item item) {
        if (item instanceof ITetraJSItem || item instanceof DynamicModularItem) {
            this.transformSingleHeaded(entity, partialTicks, matrixStack);
        }
    }

}
