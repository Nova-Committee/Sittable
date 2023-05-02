package committee.nova.sittable.client.event.handler;

import committee.nova.sittable.client.render.entity.SittableRenderer;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.entity.init.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@SuppressWarnings("unchecked")
public class ModClientEventHandler {
    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType<SittableEntity>) EntityInit.sittable.get(), SittableRenderer::new);
    }
}
