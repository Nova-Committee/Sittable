package committee.nova.sittable.common.registry.type;

import com.mojang.datafixers.util.Function3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Optional;

public class SittableRegistry {
    private final Block block;
    private final Function3<BlockState, PlayerEntity, Optional<BlockRayTraceResult>, Optional<Vector3d>> offset;

    public SittableRegistry(
            Block block,
            Function3<BlockState, PlayerEntity, Optional<BlockRayTraceResult>, Optional<Vector3d>> offset) {
        this.block = block;
        this.offset = offset;
    }

    public SittableRegistry(Block block) {
        this(block, (s, p, h) -> Optional.of(new Vector3d(.5, .5, .5)));
    }

    public Block getBlock() {
        return block;
    }

    public Function3<BlockState, PlayerEntity, Optional<BlockRayTraceResult>, Optional<Vector3d>> getOffset() {
        return offset;
    }
}
