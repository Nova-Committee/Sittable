package committee.nova.sittable.common.registry.type;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public record SittableRegistry(
        Block block,
        OffsetProvider offset) {
    @FunctionalInterface
    public interface OffsetProvider {
        @SuppressWarnings("all")
        SittablePosition get(BlockState state, Player player, Optional<BlockHitResult> hit);
    }


    public SittableRegistry(Block block) {
        this(block, (s, p, h) -> SittablePosition.of(new Vec3(.5, .5, .5)));
    }
}
