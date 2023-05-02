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
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.List;

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

    public static boolean isOccupied(Level level, BlockPos pos) {
        return !level.getEntitiesOfClass(SittableEntity.class, new AABB(pos.getX() + .0625, pos.getY(), pos.getZ() + .0625,
                pos.getX() + .9375, pos.getY() + 1.5, pos.getZ() + .9375)).isEmpty();
    }

    public static boolean trySit(Level level, BlockPos pos, BlockState state, Player player) {
        final SittableRegistry registry = SittableRegisterEvent.getSittables().get(state.getBlock());
        if (registry == null) return false;
        final double height = registry.height().applyAsDouble(state, player);
        if (height == Double.MIN_VALUE) return false;
        if (Utilities.isOccupied(level, pos)) {
            player.displayClientMessage(Messages.OCCUPIED.getComponent(), true);
            return true;
        }
        final SittableEntity sittable = new SittableEntity(level, pos.getX() + .5, pos.getY() + height, pos.getZ() + .5);
        level.addFreshEntity(sittable);
        if (player.isPassenger()) player.stopRiding();
        player.startRiding(sittable);
        return true;
    }
}
