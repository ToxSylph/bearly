package com.toxsylph.bearly.hack.hacks.movement;

import com.toxsylph.bearly.Bearly;
import com.toxsylph.bearly.hack.hacks.Hack;
import com.toxsylph.bearly.util.StaticGlobals;

public class Step extends Hack {

	public Step(String name, int key, boolean toggled, String description, Category category) {
		super(name, key, toggled, description, category);
	}

	public Step() {
		super("Step", StaticGlobals.KEY_N, false, "Allows to climb higher", Category.MOVEMENT);
	}

	@Override
	protected void onEnable() {
		Bearly.mc.player.stepHeight = 2.5f;
	}

	@Override
	protected void onDisable() {
		Bearly.mc.player.stepHeight = 0.5f;
	}
}
