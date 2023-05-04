package committee.nova.sittable.common.entity.impl;

import committee.nova.sittable.common.entity.init.EntityInit;
import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.util.Utilities;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SittableEntity extends Entity {
    private int tick = 0;

    public SittableEntity(EntityType<?> t, World l) {
        super(t, l);
        setInvulnerable(true);
        setInvisible(true);
        setNoGravity(true);
        noPhysics = true;
    }

    public SittableEntity(World world, double x, double y, double z) {
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
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        if (level.isClientSide || tickCount < 5) return;
        final List<Entity> passengers = getPassengers();
        if (passengers.isEmpty()) remove();
        tick++;
        tick &= 15;
        if (tick != 0) return;
        for (final Entity p : passengers) {
            final TextComponent msg = Utilities.isPoseValid(p.getPose()) ?
                    Utilities.isSeatValid(level, position()) ? null : Messages.INVALID.getComponent() : Messages.WRONG_POSE.getComponent();
            if (msg == null) continue;
            p.stopRiding();
            if (p instanceof PlayerEntity) ((PlayerEntity) p).displayClientMessage(msg, true);
        }
        if (getPassengers().isEmpty()) remove();
        //super.tick();
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {

    }

    @Override
    public double getPassengersRidingOffset() {
        return getEyeHeight() - .25;
    }

    @Override
    public float getEyeHeightAccess(Pose pose, EntitySize size) {
        return size.height;
    }
}
