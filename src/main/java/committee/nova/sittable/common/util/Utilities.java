package committee.nova.sittable.common.util;

import com.google.common.collect.ImmutableList;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class Utilities {
    private static final List<Pose> availablePoses = ImmutableList.of(Pose.STANDING, Pose.CROUCHING);

    public static boolean isInDevEnv() {
        return !FMLEnvironment.production;
    }

    public static boolean isSeatValid(World level, BlockPos pos) {
        return !level.getBlockState(pos).getMaterial().equals(Material.AIR);
    }

    public static boolean isSeatValid(World level, Vector3d vec) {
        return isSeatValid(level, new BlockPos(Math.floor(vec.x), Math.floor(vec.y - .03), Math.floor(vec.z)));
    }

    public static boolean isPoseValid(Pose pose) {
        return availablePoses.contains(pose);
    }

    public static boolean isOccupied(World level, BlockPos pos, Vector3d center) {
        final Vector3d real = center.add(pos.getX(), pos.getY(), pos.getZ());
        return !level.getEntitiesOfClass(SittableEntity.class, new AxisAlignedBB(real.add(-.1, -.1, -.1), real.add(.1, .1, .1))).isEmpty();
    }

    public static boolean trySit(World level, BlockPos pos, BlockState state, @Nullable BlockRayTraceResult hit, PlayerEntity player) {
        final SittableRegistry registry = SittableRegisterEvent.getSittables().get(state.getBlock());
        if (registry == null) return false;
        final Optional<Vector3d> o = registry.getOffset().get(state, player, Optional.ofNullable(hit));
        if (!o.isPresent()) return false;
        final Vector3d vec = o.get();
        if (Utilities.isOccupied(level, pos, vec)) {
            player.displayClientMessage(Messages.OCCUPIED.getComponent(), true);
            return true;
        }
        final SittableEntity sittable = new SittableEntity(level, pos.getX() + vec.x, pos.getY() + vec.y, pos.getZ() + vec.z);
        level.addFreshEntity(sittable);
        if (player.isPassenger()) player.stopRiding();
        player.startRiding(sittable);
        return true;
    }
}
