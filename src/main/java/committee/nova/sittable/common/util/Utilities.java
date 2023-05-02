package committee.nova.sittable.common.util;

import com.google.common.collect.ImmutableList;
import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.sittable.Messages;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.loading.FMLEnvironment;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class Utilities {
    private static final List<Pose> availablePoses = ImmutableList.of(Pose.STANDING, Pose.CROUCHING);

    public static boolean isInDevEnv() {
        return !FMLEnvironment.production;
    }

    public static boolean canEntitySit(Level level, BlockPos pos, Pose pose) {
        return !level.getBlockState(pos).getMaterial().equals(Material.AIR) && availablePoses.contains(pose);
    }

    public static boolean canEntitySit(Level level, Vec3 vec, Pose pose) {
        return canEntitySit(level, new BlockPos(Math.floor(vec.x), Math.floor(vec.y - .03), Math.floor(vec.z)), pose);
    }

    public static boolean isOccupied(Level level, BlockPos pos, Vec3 center) {
        final Vec3 real = center.add(pos.getX(), pos.getY(), pos.getZ());
        return !level.getEntitiesOfClass(SittableEntity.class, new AABB(real.add(-.1, -.1, -.1), real.add(.1, .1, .1))).isEmpty();
    }

    public static boolean trySit(Level level, BlockPos pos, BlockState state, @Nullable BlockHitResult hit, Player player) {
        final SittableRegistry registry = SittableRegisterEvent.getSittables().get(state.getBlock());
        if (registry == null) return false;
        final Optional<Vec3> o = registry.offset().apply(state, player, Optional.ofNullable(hit));
        if (o.isEmpty()) return false;
        final Vec3 vec = o.get();
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
