package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock e) {
        final World level = e.getWorld();
        final BlockPos pos = e.getPos();
        e.setCanceled(Utilities.trySit(level, pos, level.getBlockState(pos), e.getHitVec(), e.getPlayer()));
    }

    @SubscribeEvent
    public static void onRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv()) e.registerSittable(new SittableRegistry(Blocks.OAK_SLAB,
                (s, p, h) -> (p.isCrouching() || !s.getValue(SlabBlock.TYPE).equals(SlabType.BOTTOM)) ? Optional.empty()
                        : Optional.of(new Vector3d(.5, .5, .5))));
    }
}
