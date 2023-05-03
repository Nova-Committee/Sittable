package committee.nova.sittable.common.event.impl;

import com.google.common.collect.ImmutableMap;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class SittableRegisterEvent extends Event {
    private static final Map<Block, SittableRegistry> registries = new HashMap<>();

    public void registerSittable(SittableRegistry sittable) {
        registries.put(sittable.getBlock(), sittable);
    }

    public static Map<Block, SittableRegistry> getSittables() {
        return ImmutableMap.copyOf(registries);
    }
}