package committee.nova.sittable.common.registry.type;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.ToDoubleFunction;

public record SittableRegistry(Block block, ToDoubleFunction<BlockState> height) {
    public SittableRegistry(Block block) {
        this(block, s -> .5);
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
