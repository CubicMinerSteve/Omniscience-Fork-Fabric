package com.thatdudo.omniscience.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.thatdudo.omniscience.config.ConfigManager;

import net.minecraft.client.model.ModelPart;

@Mixin(ModelPart.class)
public class ModelPartMixin {

    @ModifyVariable(at = @At("HEAD"), method = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V", ordinal = 2, argsOnly = true)
    private int onRender(int color) {
        if (ConfigManager.getConfig().isEnabled()) {
            int alpha = (color >> 24) & 0xFF;
            if (alpha != 0xFF) {
                int newAlpha = (int) (ConfigManager.getConfig().alpha * 255);
                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = color & 0xFF;
                int newColor = (newAlpha << 24) | (red << 16) | (green << 8) | blue;
                return newColor;
            }
        }
        return color;
    }
}
