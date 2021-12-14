package com.toxsylph.bearly.gui.clickgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.toxsylph.bearly.Bearly;
import com.toxsylph.bearly.gui.component.GButton;
import com.toxsylph.bearly.gui.component.GFrame;
import com.toxsylph.bearly.hack.hacks.Hack;
import com.toxsylph.bearly.template.TGuiScreen;
import com.toxsylph.bearly.template.TModule.Category;

import net.minecraft.util.text.ITextComponent;

public class GClickGui extends TGuiScreen {

	private GFrame tree;
	public int textColor;
	public int framesColor;
	public int buttonsColor;

	public GClickGui(ITextComponent titleIn) {
		super(titleIn);
		loadFrames();
	}

	public GClickGui(String titleIn) {
		super(titleIn);
		loadFrames();
	}

	public GClickGui(String titleIn, int key) {
		super(titleIn, key);
		loadFrames();
	}

	private void loadFrames() {
		this.tree = null;
		GFrame tmp = null;

		tmp = new GFrame("> Player <", 10, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
		tmp = new GFrame("> Combat <", 90, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
		tmp = new GFrame("> Exploits <", 170, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
		tmp = new GFrame("> Movement <", 250, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
		tmp = new GFrame("> World <", 330, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
		tmp = new GFrame("> Render <", 410, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
		for (Hack h : Bearly.hackManager.getList()) {
			if (h.getCategory() == Category.RENDER) {
				addChild(tmp, new GButton("> " + h.getName(), 410, 20, 80, 10, 0x0, 0x0, 0x0, h));
			}
		}
		tmp = new GFrame("> Client <", 490, 20, 80, 10, 0x0, 0x0, 0x0);
		addBrother(tmp);
	}

	@Override
	protected void init() {
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		GFrame frame = this.tree;
		int yOffset;
		while (frame != null) {
			yOffset = 10;
			GFrame module = frame.child;
			frame.update(mouseX, mouseY);
			frame.render(matrixStack);
			while (module != null) {
				GFrame setting = module.brother;
				module.update(mouseX, mouseY);
				module.render(matrixStack, frame.getX(), frame.getY() + yOffset);
				yOffset += 10;
				while (setting != null) {
					setting.update(mouseX, mouseY);
					setting.render(matrixStack, frame.getX(), frame.getY() + yOffset);
					yOffset += 10;
					setting = setting.brother;
				}
				module = module.child;
			}
			frame = frame.brother;
		}
	}

	@Override
	public void startDragging(double mouseX, double mouseY) {
		GFrame frame = this.tree;
		while (frame != null) {
			GFrame module = frame.child;
			frame.startDragging(mouseX, mouseY);
			while (module != null) {
				GFrame setting = module.brother;
				module.startDragging(mouseX, mouseY);
				while (setting != null) {
					setting.startDragging(mouseX, mouseY);
					setting = setting.brother;
				}
				module = module.child;
			}
			frame = frame.brother;
		}
	}

	@Override
	public void endDragging() {
		GFrame frame = this.tree;
		while (frame != null) {
			GFrame module = frame.child;
			frame.endDragging();
			while (module != null) {
				GFrame setting = module.brother;
				module.endDragging();
				while (setting != null) {
					setting.endDragging();
					setting = setting.brother;
				}
				module = module.child;
			}
			frame = frame.brother;
		}
	}

	private void addBrother(GFrame brother) {
		if (this.tree == null) {
			this.tree = brother;
		} else {
			GFrame tmp = this.tree;
			while (tmp.brother != null) {
				tmp = tmp.brother;
			}
			tmp.brother = brother;
		}
	}

	private void addChild(GFrame parent, GFrame child) {
		if (parent.child == null) {
			parent.child = child;
		} else {
			GFrame tmp = parent;
			while (tmp.child != null) {
				tmp = tmp.child;
			}
			tmp.child = child;
		}
	}
	
	public void printTree() {
		GFrame frame = this.tree;
		while (frame != null) {
			System.out.println(frame.getText());
			GFrame module = frame.child;
			while (module != null) {
				System.out.println(module.getText());
				GFrame setting = module.brother;
				while (setting != null) {
					System.out.println(setting.getText());
					setting = setting.brother;
				}
				module = module.child;
			}
			frame = frame.brother;
		}
	}

}

/*
 * this.addButton( new Button(20, 20, 80, 20, new
 * StringTextComponent("ClickGUIButton"), button -> this.closeScreen()));
 * System.out.println("Opening");
 */
