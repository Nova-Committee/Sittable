package committee.nova.sittable.common.msg;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

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

    public IChatComponent getComponent() {
        return new ChatComponentTranslation(getKey());
    }
}
