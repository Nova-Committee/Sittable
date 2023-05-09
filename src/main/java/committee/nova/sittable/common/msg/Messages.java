package committee.nova.sittable.common.msg;

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
}
