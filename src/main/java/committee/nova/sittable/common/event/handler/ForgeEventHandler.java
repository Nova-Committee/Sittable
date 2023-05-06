package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.util.Utilities;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.Optional;

public class ForgeEventHandler {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent e) {
        if (e.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return;
        e.setCanceled(Utilities.trySit(e.world, e.x, e.y, e.z, e.face, e.entityPlayer));
    }

    @SubscribeEvent
    public void onSittableRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv()) e.registerSittable(new SittableRegistry(Blocks.wooden_slab, (b, m, p, f) -> {
            if (p.isSneaking() || (m & 8) != 0) return Optional.empty();
            return Optional.of(Vec3.createVectorHelper(.5, .5, .5));
        }));
    }
}
