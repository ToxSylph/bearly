package com.toxsylph.bearly.hack.hacks.render;

import com.toxsylph.bearly.hack.hacks.Hack;
import com.toxsylph.bearly.setting.settings.SettingNumeric;
import com.toxsylph.bearly.util.ResourceUtil;
import com.toxsylph.bearly.util.StaticGlobals;

import net.minecraft.client.Minecraft;

public class FullBright extends Hack {

	private double gamma = 1;

	public FullBright(String name, int key, boolean toggled, String description, Category category) {
		super(name, key, toggled, description, category);
	}

	public FullBright() {
		super("FullBright", StaticGlobals.KEY_B, false, "Allow to see in dark places", Category.RENDER);
		loadSettings();
	}

	private void loadSettings() {
		settings.add(new SettingNumeric<Double>("Brightness", 96.0d, 0.0d, 100.0d, 0.0d, 999999.0d));
	}

	@Override
	protected void onEnable() {
		Minecraft mc = Minecraft.getInstance();
		this.gamma = mc.gameSettings.gamma;
		mc.gameSettings.gamma = (double) getSetting("Brightness").getValue();
		ResourceUtil.saveDir("Pre run text");
	}

	@Override
	protected void onDisable() {
		Minecraft mc = Minecraft.getInstance();
		mc.gameSettings.gamma = this.gamma;
	}

}
