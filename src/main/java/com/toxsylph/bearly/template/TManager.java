package com.toxsylph.bearly.template;

import java.util.ArrayList;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TManager<T> {

	protected ArrayList<T> list;

	protected void loadModules() {

	}

	public ArrayList<T> getModules() {
		return list;
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

	}

	@SubscribeEvent
	public void onTick(ClientTickEvent event) {

	}

	public ArrayList<T> getList() {
		return list;
	}

}
