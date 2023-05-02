package committee.nova.sittable.common.sittable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public enum Messages {
    WRONG_POSE("wrong_pose"),
    OCCUPIED("occupied");
    private final String key;

    Messages(String key) {
        this.key = key;
    }

    public String getKey() {
        return "msg.sittable." + key;
    }

    public Component getComponent() {
        return new TranslatableComponent(getKey());
    }
}
