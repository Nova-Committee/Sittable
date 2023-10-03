package committee.nova.sittable.common.entity.impl;

import committee.nova.sittable.common.entity.init.EntityInit;
import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SittableEntity extends Entity {
    private int tick = 0;
    public static final EntityDataAccessor<Boolean> LIMITS_YAW_OFFSET = SynchedEntityData.defineId(SittableEntity.class, EntityDataSerializers.BOOLEAN);

    public SittableEntity(EntityType<?> t, Level l) {
        super(t, l);
        setInvulnerable(true);
        setInvisible(true);
        setNoGravity(true);
        noPhysics = true;
    }

    public SittableEntity(Level world, double x, double y, double z, float facingYRot, boolean limitYaw) {
        this(EntityInit.sittable.get(), world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        if (facingYRot != Float.MIN_VALUE) {
            setYRot(facingYRot);
            if (limitYaw) this.entityData.set(LIMITS_YAW_OFFSET, true);
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(LIMITS_YAW_OFFSET, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.entityData.set(LIMITS_YAW_OFFSET, tag.getBoolean("limits_yaw_offset"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putBoolean("limits_yaw_offset", this.entityData.get(LIMITS_YAW_OFFSET));
    }

    @Override
    public void tick() {
        if (level().isClientSide || tickCount < 5) return;
        final List<Entity> passengers = getPassengers();
        if (passengers.isEmpty()) remove(RemovalReason.DISCARDED);
        tick++;
        tick &= 15;
        if (tick != 0) return;
        for (final var p : passengers) {
            final Component msg = Utilities.isPoseValid(p.getPose()) ?
                    Utilities.isSeatValid(level(), position()) ? null : Messages.INVALID.getComponent() : Messages.WRONG_POSE.getComponent();
            if (msg == null) continue;
            p.stopRiding();
            if (p instanceof Player player) player.displayClientMessage(msg, true);
        }
        if (getPassengers().isEmpty()) remove(RemovalReason.DISCARDED);
        //super.tick();
    }

    @Override
    protected float getEyeHeight(Pose pose, EntityDimensions size) {
        return size.height;
    }

    @Override
    protected void positionRider(Entity e, MoveFunction f) {
        super.positionRider(e, f);
        limitYaw(e);
    }

    @Override
    public void onPassengerTurned(Entity e) {
        super.onPassengerTurned(e);
        limitYaw(e);
    }

    // Thank u MrCrayfish, this works really well :D
    private void limitYaw(Entity e) {
        if (!this.entityData.get(LIMITS_YAW_OFFSET)) return;
        e.setYBodyRot(this.getYRot());
        final float wrappedYaw = Mth.wrapDegrees(e.getYRot() - this.getYRot());
        final float clampedYaw = Mth.clamp(wrappedYaw, -120.0F, 120.0F);
        e.yRotO += clampedYaw - wrappedYaw;
        e.setYRot(e.getYRot() + clampedYaw - wrappedYaw);
        e.setYHeadRot(e.getYRot());
    }
}
