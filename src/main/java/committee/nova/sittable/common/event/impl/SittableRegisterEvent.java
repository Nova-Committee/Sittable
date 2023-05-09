package committee.nova.sittable.common.event.impl;

import com.google.common.collect.ImmutableMap;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import net.minecraftforge.event.Event;

import java.util.HashMap;
import java.util.Map;

public class SittableRegisterEvent extends Event {
    private static final Map<Integer, SittableRegistry> registries = new HashMap<Integer, SittableRegistry>();

    public void registerSittable(SittableRegistry sittable) {
        registries.put(sittable.getBlockId(), sittable);
    }

    public static Map<Integer, SittableRegistry> getSittables() {
        return ImmutableMap.copyOf(registries);
    }
}
