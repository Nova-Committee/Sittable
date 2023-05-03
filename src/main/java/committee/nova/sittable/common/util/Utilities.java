package committee.nova.sittable.common.util;

import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;

public class Utilities {
    public static boolean isInDevEnv() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public static boolean canEntitySit(World world, BlockPos pos) {
        return !world.getBlockState(pos).getMaterial().equals(Material.AIR);
    }

    public static boolean canEntitySit(World level, Vec3d vec) {
        return canEntitySit(level, new BlockPos(Math.floor(vec.x), Math.floor(vec.y - .03), Math.floor(vec.z)));
    }

    public static boolean isOccupied(World level, BlockPos pos, Vec3d center) {
        final Vec3d real = center.add(pos.getX(), pos.getY(), pos.getZ());
        final Vec3d start = real.add(-.1, -.1, -.1);
        final Vec3d end = real.add(.1, .1, .1);
        return !level.getEntitiesWithinAABB(SittableEntity.class, new AxisAlignedBB(start, end)).isEmpty();
    }

    public static boolean trySit(World level, BlockPos pos, @Nullable EnumFacing face, EntityPlayer player) {
        final IBlockState state = level.getBlockState(pos);
        final Block block = state.getBlock();
        final SittableRegistry registry = SittableRegisterEvent.getSittables().get(block);
        if (registry == null) return false;
        final Optional<Vec3d> o = registry.getOffset().apply(state, player, face);
        if (!o.isPresent()) return false;
        final Vec3d vec = o.get();
        if (Utilities.isOccupied(level, pos, vec)) {
            player.sendStatusMessage(Messages.OCCUPIED.getComponent(), true);
            return true;
        }
        final SittableEntity sittable = new SittableEntity(level, vec.add(pos.getX(), pos.getY(), pos.getZ()));
        level.spawnEntity(sittable);
        player.startRiding(sittable, true);
        return true;
    }
}
