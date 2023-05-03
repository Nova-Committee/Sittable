package committee.nova.sittable.common.registry.type;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class SittableRegistry {
    @FunctionalInterface
    public interface OffsetStrategy {
        Optional<Vec3d> apply(IBlockState state, EntityPlayer player, EnumFacing face);
    }

    private final Block block;
    private final OffsetStrategy offset;

    public SittableRegistry(Block block, OffsetStrategy offset) {
        this.block = block;
        this.offset = offset;
    }

    public Block getBlock() {
        return block;
    }

    public OffsetStrategy getOffset() {
        return offset;
    }
}
