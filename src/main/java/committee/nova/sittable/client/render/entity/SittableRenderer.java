package committee.nova.sittable.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SittableRenderer extends EntityRenderer<SittableEntity> {
    public SittableRenderer(EntityRendererManager m) {
        super(m);
    }

    @Override
    public ResourceLocation getTextureLocation(SittableEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRender(SittableEntity e, ClippingHelper h, double d1, double d2, double d3) {
        return false;
    }

    @Override
    public void render(SittableEntity e, float f1, float f2, MatrixStack p, IRenderTypeBuffer b, int i) {

    }
}
