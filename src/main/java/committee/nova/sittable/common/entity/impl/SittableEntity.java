package committee.nova.sittable.common.entity.impl;

import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SittableEntity extends Entity {
    public SittableEntity(World w) {
        super(w);
        this.width = .4F;
        this.height = .02F;
        setEntityInvulnerable(true);
        noClip = true;
    }

    public SittableEntity(World w, Vec3d vec) {
        this(w);
        this.setPosition(vec.x, vec.y, vec.z);
        this.prevPosX = vec.x;
        this.prevPosY = vec.y;
        this.prevPosZ = vec.z;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {

    }

    @Override
    public void onEntityUpdate() {
        if (world.isRemote || ticksExisted < 5) return;
        if (!isBeingRidden()) setDead();
        if (!Utilities.canEntitySit(world, getPositionVector())) {
            getPassengers().forEach(p -> {
                dismountRidingEntity();
                if (p instanceof EntityPlayer)
                    ((EntityPlayer) p).sendStatusMessage(Messages.INVALID.getComponent(), true);
            });
        }
    }

    @Override
    public boolean isInvisible() {
        return true;
    }

    @Override
    public double getMountedYOffset() {
        return .0;
    }

    @Override
    public float getEyeHeight() {
        return height;
    }
}
