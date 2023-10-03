package committee.nova.sittable.common.registry.type;

import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SittablePosition {
    private final Vec3 pos;
    private final float facingYRot;
    private final boolean limitsYaw;


    private SittablePosition(Vec3 pos, float facingYRot, boolean limitsYaw) {
        this.pos = pos;
        this.facingYRot = facingYRot;
        this.limitsYaw = limitsYaw;
    }

    private SittablePosition(Vec3 pos) {
        this(pos, Float.MIN_VALUE, false);
    }

    public static SittablePosition empty() {
        return new SittablePosition(null);
    }

    public static SittablePosition of(@NotNull Vec3 pos) {
        return new SittablePosition(pos);
    }

    public static SittablePosition withFacing(@NotNull Vec3 pos, float facingYRot, boolean limitsYaw) {
        return new SittablePosition(pos, facingYRot, limitsYaw);
    }

    public Vec3 getPos() {
        return pos;
    }

    public float getFacingYRot() {
        return facingYRot;
    }

    public boolean shouldLimitYaw() {
        return limitsYaw;
    }
}
