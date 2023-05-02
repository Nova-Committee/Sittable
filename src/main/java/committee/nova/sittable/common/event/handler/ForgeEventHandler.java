package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.sittable.Messages;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock e) {
        final Level level = e.getWorld();
        final BlockHitResult hit = e.getHitVec();
        final BlockPos pos = e.getPos();
        final BlockState state = level.getBlockState(pos);
        final SittableRegistry registry = SittableRegisterEvent.getSittables().get(state.getBlock());
        if (registry == null) return;
        final double height = registry.height().applyAsDouble(state);
        if (height == Double.MIN_VALUE) return;
        e.setCanceled(true);
        final Player player = e.getPlayer();
        if (Utilities.isOccupied(level, pos)) {
            player.displayClientMessage(Messages.OCCUPIED.getComponent(), true);
            return;
        }
        final SittableEntity sittable = new SittableEntity(level, pos.getX() + .5, pos.getY() + height, pos.getZ() + .5);
        level.addFreshEntity(sittable);
        if (player.isPassenger()) player.stopRiding();
        player.startRiding(sittable);
    }

    @SubscribeEvent
    public static void onRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv()) e.registerSittable(new SittableRegistry(Blocks.OAK_SLAB, s -> .5));
    }
}
