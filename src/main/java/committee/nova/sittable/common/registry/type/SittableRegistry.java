package committee.nova.sittable.common.registry.type;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Optional;

public class SittableRegistry {
    public interface OffsetProvider {
        @SuppressWarnings("all")
        Optional<Vector3d> get(BlockState state, PlayerEntity player, Optional<BlockRayTraceResult> hit);
    }

    private final Block block;
    private final OffsetProvider offset;

    public SittableRegistry(Block block, OffsetProvider offset) {
        this.block = block;
        this.offset = offset;
    }

    public SittableRegistry(Block block) {
        this(block, (s, p, h) -> Optional.of(new Vector3d(.5, .5, .5)));
    }

    public Block getBlock() {
        return block;
    }

    public OffsetProvider getOffset() {
        return offset;
    }
}
