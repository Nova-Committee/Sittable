package committee.nova.sittable.common.registry.type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class SittableRegistry {
    public interface OffsetStrategy {
        /**
         * @param blockId  The block to check if it's sittable
         * @param metadata The block's metadata
         * @param player   The player interacting with the block
         * @param face     The clicked face of the block
         * @return A {@link Vec3}. Its value represents the offset of the player's actual sitting position
         * from the block's location. If it returns null, it means that the block is not sittable in this case.
         */
        Vec3 apply(int blockId, int metadata, EntityPlayer player, int face);
    }

    private final int blockId;
    private final OffsetStrategy offset;

    public SittableRegistry(int blockId, OffsetStrategy offset) {
        this.blockId = blockId;
        this.offset = offset;
    }

    public int getBlockId() {
        return blockId;
    }

    public OffsetStrategy getOffset() {
        return offset;
    }
}
