package committee.nova.sittable.common.entity.init;

import committee.nova.sittable.Sittable;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.registry.handler.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;

public class EntityInit {
    public static final RegistryObject<EntityType<? extends Entity>> sittable = RegistryHandler.ENTITIES.register(Sittable.MODID, () -> EntityType.Builder.of(SittableEntity::new, EntityClassification.MISC)
            .fireImmune().sized(.4F, .02F).build(Sittable.MODID));

    public static void init() {
    }
}
