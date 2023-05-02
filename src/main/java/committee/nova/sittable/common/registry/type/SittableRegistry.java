package committee.nova.sittable.common.registry.type;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.ToDoubleBiFunction;

public record SittableRegistry(Block block, ToDoubleBiFunction<BlockState, Player> height) {
    public SittableRegistry(Block block) {
        this(block, (s, p) -> .5);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SittableRegistry that)) return false;
        return block.equals(that.block);
    }

    public int hashCode() {
        final ResourceLocation r = block.getRegistryName();
        return r == null ? -1 : r.hashCode();
    }
}
