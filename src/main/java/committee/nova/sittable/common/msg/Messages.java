package committee.nova.sittable.common.msg;

import net.minecraft.network.chat.Component;

public enum Messages {
    INVALID("invalid"),
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
        return Component.translatable(getKey());
    }
}
