package com.toxsylph.bearly.template;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.toxsylph.bearly.Bearly;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TGuiScreen extends Screen {

	private int key;
	private boolean isShown;

	public TGuiScreen(ITextComponent titleIn) {
		super(titleIn);
		this.key = -1;
		this.isShown = false;
	}

	public TGuiScreen(String titleIn) {
		super(new StringTextComponent(titleIn));
		this.key = -1;
		this.isShown = false;
	}

	public TGuiScreen(String titleIn, int key) {
		super(new StringTextComponent(titleIn));
		this.key = key;
		this.isShown = false;
	}

	@Override
	protected void init() {
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
	}

	public void startDragging(double mouseX, double mouseY) {
	}

	public void endDragging() {

	}

	public void toggle() {
		if (isShown) {
			disable();
		} else {
			enable();
		}
	}

	public void enable() {
		if (isShown)
			return;
		isShown = true;
		Bearly.mc.displayGuiScreen(this);
	}

	public void disable() {
		closeScreen();
	}

	public void onClose() {
		isShown = false;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isShown() {
		return isShown;
	}

	public void setShown(boolean isShown) {
		this.isShown = isShown;
	}

}
