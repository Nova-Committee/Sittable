package committee.nova.sittable.common.registry.type;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import java.util.Optional;

public class SittableRegistry {
    @FunctionalInterface
    public interface OffsetStrategy {
        /**
         * @param block    The block to check if it's sittable
         * @param metadata The block's metadata
         * @param player   The player interacting with the block
         * @param face     The clicked face of the block
         * @return An {@link Optional} of a {@link Vec3}. Its value represents the offset of the player's actual sitting position
         * from the block's location. If it returns an {@link Optional#empty()}, it means that the block is not sittable in this case.
         */
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
