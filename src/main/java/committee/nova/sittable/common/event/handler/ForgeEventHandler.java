package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock e) {
        final Level level = e.getWorld();
        if (level.isClientSide) return;
        final BlockPos pos = e.getPos();
        e.setCanceled(Utilities.trySit(level, pos, level.getBlockState(pos), e.getPlayer()));
    }

    @SubscribeEvent
    public static void onRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv()) e.registerSittable(new SittableRegistry(Blocks.OAK_SLAB, s -> .5));
    }
}
