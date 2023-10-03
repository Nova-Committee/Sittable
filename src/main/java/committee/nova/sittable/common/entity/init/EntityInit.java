package committee.nova.sittable.common.entity.init;

import committee.nova.sittable.Sittable;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.registry.handler.RegistryHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final RegistryObject<EntityType<? extends Entity>> sittable = RegistryHandler.ENTITIES.register(Sittable.MODID, () -> EntityType.Builder.of(SittableEntity::new, MobCategory.MISC)
            .fireImmune().sized(.4F, .02F).build(Sittable.MODID));

    public static void init() {
    }
}
