package com.toxsylph.bearly.hack.hacks.render;

import com.toxsylph.bearly.Bearly;
import com.toxsylph.bearly.hack.hacks.Hack;
import com.toxsylph.bearly.util.RenderUtilOverlay;
import com.toxsylph.bearly.util.StaticGlobals;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Tracers extends Hack {

	private boolean bob = false;

	public Tracers(String name, int key, boolean toggled, String description, Category category) {
		super(name, key, toggled, description, category);
		bob = Bearly.mc.gameSettings.viewBobbing;
	}

	public Tracers() {
		super("Tracers", StaticGlobals.KEY_P, false, "Draw line towards entities", Category.RENDER);
	}

	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent event) {
		if (!isToggled())
			return;

		try {
			Bearly.mc.world.getAllEntities().forEach(entity -> {

				if (entity.getName().getString().equalsIgnoreCase("Creeper")) {
					try {
						// TODO: check player.getForward to try fix lines jiggling
						RenderUtilOverlay.getGLLineToEntity(entity);

					} catch (Exception e) {

					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onEnable() {
		bob = Bearly.mc.gameSettings.viewBobbing;
		Bearly.mc.gameSettings.viewBobbing = false;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	protected void onDisable() {
		Bearly.mc.gameSettings.viewBobbing = bob;
		MinecraftForge.EVENT_BUS.unregister(this);
	}

}
