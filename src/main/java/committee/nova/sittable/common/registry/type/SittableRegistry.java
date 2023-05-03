package committee.nova.sittable.common.registry.type;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import java.util.Optional;

public class SittableRegistry {
    @FunctionalInterface
    public interface OffsetStrategy {
        Optional<Vec3> apply(Block block, int metadata, EntityPlayer player, int face);
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
