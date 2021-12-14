package com.toxsylph.bearly.gui;

import java.util.ArrayList;

import com.toxsylph.bearly.gui.clickgui.GClickGui;
import com.toxsylph.bearly.template.TGuiScreen;
import com.toxsylph.bearly.template.TManager;
import com.toxsylph.bearly.util.StaticGlobals;

import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.MouseReleasedEvent;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GuiManager extends TManager<TGuiScreen> {

	public GuiManager() {
		list = new ArrayList<TGuiScreen>();
		loadModules();
	}

	@Override
	protected void loadModules() {
		this.list.add(new GClickGui("GClickGui", StaticGlobals.KEY_RIGHT_SHIFT));
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (event.getAction() == StaticGlobals.KEY_PRESS) {
			for (TGuiScreen g : list) {
				if (g.getKey() == event.getKey()) {
					g.toggle();
				}
			}
		}
	}

	@SubscribeEvent
	public void onMouseClicked(MouseClickedEvent.Post event) {
		for (TGuiScreen g : list) {
			g.startDragging(event.getMouseX(), event.getMouseY());
		}
	}

	@SubscribeEvent
	public void onMouseReleased(MouseReleasedEvent.Post event) {
		for (TGuiScreen g : list) {
			g.endDragging();
		}
	}

}
