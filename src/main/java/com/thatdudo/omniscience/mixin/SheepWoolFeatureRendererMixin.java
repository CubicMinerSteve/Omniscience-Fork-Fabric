package com.thatdudo.omniscience.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thatdudo.omniscience.config.Config;
import com.thatdudo.omniscience.config.ConfigManager;
import com.thatdudo.omniscience.util.EntityTargetGroup;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Mixin(SheepWoolFeatureRenderer.class)
public class SheepWoolFeatureRendererMixin {

    @Shadow @Final private static Identifier TEXTURE;
    @Shadow @Final private EntityModel<SheepEntityRenderState> woolModel;
    @Shadow @Final private EntityModel<SheepEntityRenderState> babyWoolModel;

    /* private FeatureRendererContext<SheepEntityRenderState, SheepEntityModel> _context;


    @Inject(at = @At("RETURN"), method = "<init>")
    private void onInit(FeatureRendererContext<SheepEntityRenderState, SheepEntityModel> context, EntityModelLoader loader, CallbackInfo ci) {
        this._context = context;
    }
    */

    @Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/SheepEntityRenderState;FF)V")
    private void onRender(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, SheepEntityRenderState sheepEntityRenderState, float f, float g, CallbackInfo ci) {
        Config config = ConfigManager.getConfig();

        if (config.isEnabled() && sheepEntityRenderState.invisible && !sheepEntityRenderState.sheared && config.isGroupTargeted(EntityTargetGroup.ANIMAL)) {
            if (sheepEntityRenderState.baby) {
                this.babyWoolModel.setAngles(sheepEntityRenderState);
                VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getItemEntityTranslucentCull(TEXTURE));
                this.babyWoolModel.render(matrixStack, vertexConsumer2, i, LivingEntityRenderer.getOverlay(sheepEntityRenderState, 0.0f), sheepEntityRenderState.getRgbColor() & 0xFFFFFF | 0x26000000);
            } else {
                this.woolModel.setAngles(sheepEntityRenderState);
                VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getItemEntityTranslucentCull(TEXTURE));
                this.woolModel.render(matrixStack, vertexConsumer2, i, LivingEntityRenderer.getOverlay(sheepEntityRenderState, 0.0f), sheepEntityRenderState.getRgbColor() & 0xFFFFFF | 0x26000000);
            }
        }
    }
}
