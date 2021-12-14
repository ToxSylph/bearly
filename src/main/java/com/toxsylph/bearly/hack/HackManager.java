package com.toxsylph.bearly.hack;

import java.util.ArrayList;

import com.toxsylph.bearly.hack.hacks.Hack;
import com.toxsylph.bearly.hack.hacks.movement.Sprint;
import com.toxsylph.bearly.hack.hacks.movement.Step;
import com.toxsylph.bearly.hack.hacks.render.FullBright;
import com.toxsylph.bearly.hack.hacks.render.Tracers;
import com.toxsylph.bearly.template.TManager;
import com.toxsylph.bearly.template.TModule;
import com.toxsylph.bearly.util.StaticGlobals;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HackManager extends TManager<Hack> {

	public HackManager() {
		list = new ArrayList<Hack>();
		loadModules();
	}

	@Override
	protected void loadModules() {
		// MOVEMENT HACKS
		this.list.add(new Sprint());
		this.list.add(new Step());

		// RENDER HACKS
		this.list.add(new FullBright());
		this.list.add(new Tracers());
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (event.getAction() == StaticGlobals.KEY_PRESS) {
			for (TModule m : list) {
				if (m.getKey() == event.getKey()) {
					m.toggle();
				}
			}
		}
	}

	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		for (TModule m : list) {
			if (m.isToggled())
				m.update();
		}
	}
}
