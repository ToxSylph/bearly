package com.toxsylph.bearly.hack.hacks.movement;

import com.toxsylph.bearly.hack.hacks.Hack;
import com.toxsylph.bearly.util.StaticGlobals;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Sprint extends Hack {

	public Sprint(String name, int key, boolean toggled, String description, Category category) {
		super(name, key, toggled, description, category);
	}

	public Sprint() {
		super("Sprint", StaticGlobals.KEY_R, false, "Auto Sprint", Category.MOVEMENT);
	}

	@Override
	public void update() {
		Minecraft mc = Minecraft.getInstance();
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKey(), true);
	}

	@Override
	protected void onDisable() {
		Minecraft mc = Minecraft.getInstance();
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKey(), false);
		mc.gameSettings.toggleSprint = false;
	}

}
