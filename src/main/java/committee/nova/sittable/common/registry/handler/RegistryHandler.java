package committee.nova.sittable.common.registry.handler;

import committee.nova.sittable.Sittable;
import committee.nova.sittable.common.entity.init.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Sittable.MODID);

    public static void register() {
        final var bus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityInit.init();
        ENTITIES.register(bus);
    }
}
