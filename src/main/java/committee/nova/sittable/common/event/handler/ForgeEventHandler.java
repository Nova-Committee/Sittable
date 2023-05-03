package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock e) {
        final Level level = e.getWorld();
        final BlockPos pos = e.getPos();
        e.setCanceled(Utilities.trySit(level, pos, level.getBlockState(pos), e.getHitVec(), e.getPlayer()));
    }

    @SubscribeEvent
    public static void onRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv()) e.registerSittable(new SittableRegistry(Blocks.OAK_SLAB,
                (s, p, h) -> (p.isCrouching() || !s.getValue(SlabBlock.TYPE).equals(SlabType.BOTTOM)) ? Optional.empty()
                        : Optional.of(new Vec3(.5, .5, .5))));
    }
}
