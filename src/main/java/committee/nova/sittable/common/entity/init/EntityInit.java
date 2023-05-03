package committee.nova.sittable.common.entity.init;

import committee.nova.sittable.Sittable;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void init() {
        EntityRegistry.registerModEntity(new ResourceLocation(Sittable.MODID, Sittable.MODID), SittableEntity.class, "Sittable", 0, Sittable.instance,
                5, 20, false);
    }
}
