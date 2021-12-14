package com.toxsylph.bearly;

import com.toxsylph.bearly.gui.GuiManager;
import com.toxsylph.bearly.hack.HackManager;
import com.toxsylph.bearly.util.StaticGlobals;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(StaticGlobals.MODID)
public class Bearly {

	// < >
	public static Minecraft mc;
	public static HackManager hackManager;
	public static GuiManager guiManager;

	public Bearly() {
		mc = Minecraft.getInstance();
		MinecraftForge.EVENT_BUS.register(this);
		loadManagers();
	}

	private void loadManagers() {
		hackManager = new HackManager();
		guiManager = new GuiManager();

		MinecraftForge.EVENT_BUS.register(hackManager);
		MinecraftForge.EVENT_BUS.register(guiManager);
	}

	//TODO: Finish Settings settings, add settings to every hack, save/load settings from disk
	// update gradle project to add GSON
}
