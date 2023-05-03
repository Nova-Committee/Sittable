package committee.nova.sittable.common.registry.type;

import com.mojang.datafixers.util.Function3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public record SittableRegistry(
        Block block,
        Function3<BlockState, Player, Optional<BlockHitResult>, Optional<Vec3>> offset) {
    public SittableRegistry(Block block) {
        this(block, (s, p, h) -> Optional.of(new Vec3(.5, .5, .5)));
    }
}
