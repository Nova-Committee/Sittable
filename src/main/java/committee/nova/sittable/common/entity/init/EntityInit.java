package committee.nova.sittable.common.entity.init;

import committee.nova.sittable.Sittable;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void init() {
        EntityRegistry.registerModEntity(SittableEntity.class, "Sittable", 0, Sittable.instance,
                5, 20, false);
    }
}
