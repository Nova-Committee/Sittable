package committee.nova.sittable.common.event.handler;

import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import committee.nova.sittable.common.registry.type.SittableRegistry;
import committee.nova.sittable.common.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ForgeEventHandler {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }

    @ForgeSubscribe
    public void onSittableRegister(SittableRegisterEvent e) {
        if (Utilities.isInDevEnv())
            e.registerSittable(new SittableRegistry(Block.woodSingleSlab.blockID, new SittableRegistry.OffsetStrategy() {
                @Override
                public Vec3 apply(int b, int m, EntityPlayer p, int f) {
                    if (p.isSneaking()) return null;
                    return Vec3.createVectorHelper(.5, .5, .5);
                }
            }));
    }

    @ForgeSubscribe
    public void onLeftClick(PlayerInteractEvent e) {
        if (e.action != PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) return;
        final EntityPlayer p = e.entityPlayer;
        e.setCanceled(Utilities.trySit(p.worldObj, e.x, e.y, e.z, e.face, p));
    }
}
