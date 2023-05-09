package committee.nova.sittable.client.render.init;

import committee.nova.sittable.client.render.impl.RenderSittableEntity;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderInit {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(SittableEntity.class, new RenderSittableEntity());
    }
}
