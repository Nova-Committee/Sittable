package committee.nova.sittable.client.render.impl;

import committee.nova.sittable.common.entity.impl.SittableEntity;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderSittableEntity extends Render<SittableEntity> {
    public RenderSittableEntity(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull SittableEntity entity) {
        return null;
    }
}
