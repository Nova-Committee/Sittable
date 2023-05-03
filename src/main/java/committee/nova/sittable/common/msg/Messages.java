package committee.nova.sittable.common.msg;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum Messages {
    INVALID("invalid"),
    OCCUPIED("occupied");
    private final String key;

    Messages(String key) {
        this.key = key;
    }

    public String getKey() {
        return "msg.sittable." + key;
    }

    public ITextComponent getComponent() {
        return new TextComponentTranslation(getKey());
    }
}
