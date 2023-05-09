package committee.nova.sittable.common.util;

import committee.nova.sittable.common.entity.impl.SittableEntity;
import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Utilities {
    public static boolean isInDevEnv() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public static boolean canEntitySit(World world, int x, int y, int z) {
        final Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block == null) return false;
        return !block.blockMaterial.equals(Material.air);
    }

    public static boolean canEntitySit(World level, double x, double y, double z) {
        return canEntitySit(level, (int) Math.floor(x), (int) Math.floor(y - .03), (int) Math.floor(z));
    }

    public static boolean isOccupied(World level, int x, int y, int z, Vec3 center) {
        final Vec3 real = center.addVector(x, y, z);
        final Vec3 start = real.addVector(-.1, -.1, -.1);
        final Vec3 end = real.addVector(.1, .1, .1);
        return !level.getEntitiesWithinAABB(SittableEntity.class, AxisAlignedBB.getBoundingBox(start.xCoord, start.yCoord, start.zCoord, end.xCoord, end.yCoord, end.zCoord)).isEmpty();
    }

    public static boolean trySit(World level, int x, int y, int z, int face, EntityPlayer player) {
        final int block = level.getBlockId(x, y, z);
        final int metadata = level.getBlockMetadata(x, y, z);
        final SittableRegistry registry = SittableRegisterEvent.getSittables().get(block);
        if (registry == null) return false;
        final Vec3 vec = registry.getOffset().apply(block, metadata, player, face);
        if (vec == null) return false;
        if (Utilities.isOccupied(level, x, y, z, vec)) {
            player.addChatMessage(Messages.OCCUPIED.getKey());
            return true;
        }
        if (!level.isRemote) {
            final SittableEntity sittable = new SittableEntity(level, x + vec.xCoord, y + vec.yCoord, z + vec.zCoord);
            level.spawnEntityInWorld(sittable);
            player.mountEntity(sittable);
        }
        return true;
    }
}
