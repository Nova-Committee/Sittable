package committee.nova.sittable.client.event.handler;

import committee.nova.sittable.client.render.entity.SittableRenderer;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.entity.init.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@SuppressWarnings("unchecked")
public class ModClientEventHandler {
    @SubscribeEvent
    public static void registerRenderer(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<SittableEntity>) EntityInit.sittable.get(), SittableRenderer::new);
    }
}
