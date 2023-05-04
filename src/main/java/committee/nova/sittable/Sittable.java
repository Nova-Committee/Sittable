package committee.nova.sittable;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.handler.RegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

@Mod(Sittable.MODID)
public class Sittable {
    public static final String MODID = "sittable";

    public Sittable() {
        RegistryHandler.register();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerAboutToStartEvent event) {
        MinecraftForge.EVENT_BUS.post(new SittableRegisterEvent());
    }
}
