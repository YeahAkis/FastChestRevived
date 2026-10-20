package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Shadow
    @Final
    protected static Set<RenderMaterial> UNREFERENCED_TEXTURES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void fastchestrevived$stitchChestTextures(CallbackInfo ci) {
        for (String name : new String[]{"normal", "normal_left", "normal_right", "trapped", "trapped_left", "trapped_right", "ender"}) {
            UNREFERENCED_TEXTURES.add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("entity/chest/" + name)));
        }
    }
}
