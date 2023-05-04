package committee.nova.sittable.common.msg;

import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

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

    public TextComponent getComponent() {
        return new TranslationTextComponent(getKey());
    }
}
