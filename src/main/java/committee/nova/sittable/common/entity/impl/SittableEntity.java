package committee.nova.sittable.common.entity.impl;

import committee.nova.sittable.common.entity.init.EntityInit;
import committee.nova.sittable.common.sittable.Messages;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SittableEntity extends Entity {
    private int tick = 0;

    public SittableEntity(EntityType<?> t, Level l) {
        super(t, l);
        setInvulnerable(true);
    }

    public SittableEntity(Level world, double x, double y, double z) {
        this(EntityInit.sittable.get(), world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        if (level.isClientSide || tickCount < 5) return;
        final List<Entity> passengers = getPassengers();
        if (passengers.isEmpty()) remove(RemovalReason.DISCARDED);
        tick++;
        tick &= 15;
        if (tick != 0) return;
        for (final var p : passengers)
            if (!Utilities.canEntitySit(level, position(), p.getPose())) {
                p.stopRiding();
                if (p instanceof Player player) player.displayClientMessage(Messages.WRONG_POSE.getComponent(), true);
            }
        if (getPassengers().isEmpty()) remove(RemovalReason.DISCARDED);
        //super.tick();
    }

    @Override
    public double getPassengersRidingOffset() {
        return getEyeHeight() - .25;
    }

    @Override
    protected float getEyeHeight(Pose pose, EntityDimensions size) {
        return size.height;
    }
}
