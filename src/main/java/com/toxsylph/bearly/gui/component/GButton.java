package com.toxsylph.bearly.gui.component;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.toxsylph.bearly.Bearly;
import com.toxsylph.bearly.hack.hacks.Hack;

import net.minecraft.client.gui.AbstractGui;

public class GButton extends GFrame {

	protected boolean isActive;
	private Hack hack;

	public GButton(String text, int x, int y, int width, int height, int fcolor, int tcolor, int ocolor) {
		super(text, x, y, width, height, fcolor, tcolor, ocolor);
		this.isActive = false;
		this.hack = null;
	}

	public GButton(String text, int x, int y, int width, int height, int fcolor, int tcolor, int ocolor, Hack hack) {
		super(text, x, y, width, height, fcolor, tcolor, ocolor);
		this.isActive = false;
		this.hack = hack;
	}

	public void render(MatrixStack matrixStack, int xOffset, int yOffset) {
		this.x = xOffset;
		this.y = yOffset;
		this.isActive = hack.isToggled();
		AbstractGui.fill(matrixStack, xOffset, yOffset, xOffset + width, yOffset + height, isActive ? fcolor : ocolor);
		AbstractGui.drawString(matrixStack, Bearly.mc.fontRenderer, text, xOffset + 1, yOffset + 1, tcolor);
	}

	@Override
	public void startDragging(double mouseX, double mouseY) {
		if (!isHovered(mouseX, mouseY) || hack == null)
			return;

		hack.toggle();
		this.isDragging = true;
	}

	@Override
	public void endDragging() {
		this.isDragging = false;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Hack getHack() {
		return hack;
	}

}
