package committee.nova.sittable.client.render.init;

import committee.nova.sittable.client.render.impl.RenderSittableEntity;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderInit {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(SittableEntity.class, RenderSittableEntity::new);
    }
}
