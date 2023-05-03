package committee.nova.sittable;

import committee.nova.sittable.client.render.init.RenderInit;
import committee.nova.sittable.common.entity.init.EntityInit;
import committee.nova.sittable.common.event.handler.ForgeEventHandler;
import committee.nova.sittable.common.event.impl.SittableRegisterEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Sittable.MODID, useMetadata = true)
public class Sittable {
    public static final String MODID = "sittable";

    @Mod.Instance(Sittable.MODID)
    public static Sittable instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        EntityInit.init();
        ForgeEventHandler.init();
        if (e.getSide() == Side.CLIENT) RenderInit.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        MinecraftForge.EVENT_BUS.post(new SittableRegisterEvent());
    }
}
