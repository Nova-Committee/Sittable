package committee.nova.sittable.common.event.impl;

import com.google.common.collect.ImmutableMap;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.Event;

import java.util.HashMap;
import java.util.Map;

public class SittableRegisterEvent extends Event {
    private static final Map<Block, SittableRegistry> registries = new HashMap<>();

    public void registerSittable(SittableRegistry sittable) {
        registries.put(sittable.block(), sittable);
    }

    public static Map<Block, SittableRegistry> getSittables() {
        return ImmutableMap.copyOf(registries);
    }
}
