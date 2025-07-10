package com.thatdudo.omniscience.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thatdudo.omniscience.config.ConfigManager;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.BackgroundRenderer.StatusEffectFogModifier;
import net.minecraft.entity.Entity;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(at = @At("HEAD"), method = "getFogModifier", cancellable = true)
    private static void onGetFogModifier(Entity entity, float tickDelta, CallbackInfoReturnable<StatusEffectFogModifier> callbackInfo) {
        if (ConfigManager.getConfig().isEnabled()) {
            if (ConfigManager.getConfig().removeBlindnessEffect) {
                callbackInfo.setReturnValue(null);
            }
        }
    }
}
