package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Optional;

public class ForgeEventHandler {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent.RightClickBlock e) {
        final World w = e.getWorld();
        if (w.isRemote) return;
        e.setCanceled(Utilities.trySit(w, e.getPos(), e.getFace(), e.getEntityPlayer()));
    }

    @SubscribeEvent
    public void onSittableRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv()) e.registerSittable(new SittableRegistry(Blocks.WOODEN_SLAB,
                (s, p, f) -> {
                    if (p.isSneaking() || ((BlockSlab) s.getBlock()).isDouble() || s.getValue(BlockSlab.HALF).equals(BlockSlab.EnumBlockHalf.TOP))
                        return Optional.empty();
                    return Optional.of(new Vec3d(.5, .5, .5));
                }));
    }
}
