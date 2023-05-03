package committee.nova.sittable.common.entity.impl;

import committee.nova.sittable.common.msg.Messages;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SittableEntity extends Entity {

    public SittableEntity(World w) {
        super(w);
        this.width = .4F;
        this.height = .02F;
        noClip = true;
    }

    public SittableEntity(World w, double x, double y, double z) {
        this(w);
        this.setPosition(x, y, z);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {

    }

    @Override
    public void onEntityUpdate() {
        if (worldObj.isRemote || ticksExisted < 5) return;
        if (riddenByEntity == null) kill();
        if (!Utilities.canEntitySit(worldObj, posX, posY, posZ)) {
            riddenByEntity.mountEntity(null);
            if (riddenByEntity instanceof EntityPlayer)
                ((EntityPlayer) riddenByEntity).addChatComponentMessage(Messages.WRONG_POSE.getComponent());
            kill();
        }
    }

    @Override
    public void moveEntity(double d1, double d2, double d3) {

    }

    @Override
    public boolean isEntityInvulnerable() {
        return true;
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
