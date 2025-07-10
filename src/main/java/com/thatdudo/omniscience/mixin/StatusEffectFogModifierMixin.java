package com.thatdudo.omniscience.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.thatdudo.omniscience.config.ConfigManager;

import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.fog.StatusEffectFogModifier;
import net.minecraft.entity.Entity;

@Mixin(StatusEffectFogModifier.class)
public class StatusEffectFogModifierMixin {

    @Inject(at = @At("HEAD"), method = "shouldApply", cancellable = true)
    private void onShouldApply(@Nullable CameraSubmersionType submersionType, Entity cameraEntity, CallbackInfoReturnable<Boolean> info) {
        if (ConfigManager.getConfig().isEnabled()) {
            if (ConfigManager.getConfig().removeBlindnessEffect) {
                info.setReturnValue(false);
            }
        }
    }

}