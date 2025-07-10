package com.thatdudo.omniscience.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thatdudo.omniscience.config.ConfigManager;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    
    @Inject(at = @At("HEAD"), method = "hasBlindnessOrDarkness(Lnet/minecraft/client/render/Camera;)Z", cancellable = true)
    public void onHasBlindnessOrDarkness(Camera camera, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (ConfigManager.getConfig().isEnabled()) {
            if (ConfigManager.getConfig().removeBlindnessEffect) {
                callbackInfo.setReturnValue(false);
            }
        }
    }

}
