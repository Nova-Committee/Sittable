package committee.nova.sittable.client.render.entity;

import committee.nova.sittable.common.entity.impl.SittableEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SittableRenderer extends EntityRenderer<SittableEntity> {
    public SittableRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(SittableEntity entity) {
        return null;
    }
}
