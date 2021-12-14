package com.toxsylph.bearly.gui.component;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.toxsylph.bearly.Bearly;

import net.minecraft.client.gui.AbstractGui;

public class GFrame {

	protected int x, y, width, height, fcolor, tcolor, ocolor, xDragOffset, yDragOffset;
	protected final String text;
	protected boolean isDragging, isDraggable, isShown;
	public GFrame brother, child;

	public GFrame(String text, int x, int y, int width, int height, int fcolor, int tcolor, int ocolor) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fcolor = fcolor;
		this.tcolor = tcolor;
		this.ocolor = ocolor;

		// TODO: ERASE THIS 
		this.fcolor = 0XFF0004D4;
		this.tcolor = 0xFFFFFFFF;
		this.ocolor = 0X77000130;
		this.xDragOffset = this.yDragOffset = 0;
		this.isDragging = false;
		this.isDraggable = true;
		this.isShown = true;
		this.brother = this.child = null;
	}

	public void render(MatrixStack matrixStack) {
		AbstractGui.fill(matrixStack, x, y, x + width, y + height, fcolor);
		AbstractGui.drawCenteredString(matrixStack, Bearly.mc.fontRenderer, text, x + width / 2, y + 1, tcolor);
	}

	public void render(MatrixStack matrixStack, int xOffset, int yOffset) {
		AbstractGui.fill(matrixStack, xOffset, yOffset, xOffset + width, yOffset + height, fcolor);
		AbstractGui.drawCenteredString(matrixStack, Bearly.mc.fontRenderer, text, xOffset + width / 2, yOffset + 1, tcolor);
	}

	public void update(double mouseX, double mouseY) {
		if (isDragging) {
			this.x = (int) mouseX - xDragOffset;
			this.y = (int) mouseY - yDragOffset;
		}
	}

	public boolean isHovered(double mouseX, double mouseY) {
		if (mouseX < x || mouseX > x + width || mouseY < y || mouseY > y + height)
			return false;
		return true;
	}

	public void startDragging(double mouseX, double mouseY) {
		if (!isHovered(mouseX, mouseY))
			return;
		this.xDragOffset = (int) mouseX - this.x;
		this.yDragOffset = (int) mouseY - this.y;
		this.isDragging = true;
	}

	public void endDragging() {
		this.xDragOffset = this.yDragOffset = 0;
		this.isDragging = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColor() {
		return fcolor;
	}

	public void setColor(int color) {
		this.fcolor = color;
	}

	public String getText() {
		return text;
	}

	public int getFcolor() {
		return fcolor;
	}

	public void setFcolor(int fcolor) {
		this.fcolor = fcolor;
	}

	public int getTcolor() {
		return tcolor;
	}

	public void setTcolor(int tcolor) {
		this.tcolor = tcolor;
	}

	public boolean isDragging() {
		return isDragging;
	}

	public void setDragging(boolean isDragging) {
		this.isDragging = isDragging;
	}

	public boolean isDraggable() {
		return isDraggable;
	}

	public void setDraggable(boolean isDraggable) {
		this.isDraggable = isDraggable;
	}

}
