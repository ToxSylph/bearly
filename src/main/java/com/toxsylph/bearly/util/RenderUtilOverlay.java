package com.toxsylph.bearly.util;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.toxsylph.bearly.Bearly;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class RenderUtilOverlay {

	public static void drawText(MatrixStack matrix, float offset, String text) {
		Bearly.mc.fontRenderer.drawStringWithShadow(matrix, text, 10f, offset, 0xffff00);
		GL11.glDisable(GL11.GL_LIGHTING);
	}

	public static void getGLLineToEntity(Entity e) {

		PlayerEntity p = Bearly.mc.player;
		Vector3d entityPosition = interpolate(e);
		Vector3d playerPosition = interpolate(p);

		GL11.glPushMatrix();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);

		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
		GL11.glLineWidth(2.5f);

		Color c = chooseColor(p.getDistance(e));
		GL11.glColor4f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, 1.0f);

		// GL11.glColor4f(0.75f, 0.0f, 0.11f, 1.0f);

		ActiveRenderInfo ari = Bearly.mc.gameRenderer.getActiveRenderInfo();
		Vector3d proy = ari.getProjectedView();
		GL11.glRotatef(ari.getPitch(), 1, 0, 0);
		GL11.glRotatef(ari.getYaw() + 180, 0, 1, 0);
		GL11.glTranslated(-proy.x, -proy.y, -proy.z);
		

		Vector3f vv = Bearly.mc.gameRenderer.getActiveRenderInfo().getViewVector();

		GL11.glBegin(GL11.GL_LINES);

		GL11.glVertex3d(entityPosition.getX(), entityPosition.getY() + 1, entityPosition.getZ());
		GL11.glVertex3d(playerPosition.x + vv.getX(), playerPosition.getY() + p.getEyeHeight() + vv.getY(), playerPosition.z + vv.getZ());

		GL11.glEnd();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);

		GL11.glPopMatrix();

	}

	public static Vector3d interpolate(Entity e) {
		double x = e.lastTickPosX + (e.getPosX() - e.lastTickPosX) * (double) Bearly.mc.getRenderPartialTicks();
		double y = e.lastTickPosY + (e.getPosY() - e.lastTickPosY) * (double) Bearly.mc.getRenderPartialTicks();
		double z = e.lastTickPosZ + (e.getPosZ() - e.lastTickPosZ) * (double) Bearly.mc.getRenderPartialTicks();

		return new Vector3d(x, y, z);
	}

	public static Color chooseColor(float entityDistance) {
		if (entityDistance > StaticGlobals.TRACER_SAFE_DISTANCE)
			entityDistance = StaticGlobals.TRACER_SAFE_DISTANCE;
		final Color c = new Color(Color.HSBtoRGB((((entityDistance / StaticGlobals.TRACER_SAFE_DISTANCE) * 120.0f) / 360.0f), 1.0f, 1.0f));
		// final Color c = new Color(0.0f, 1.0f,0.0f);

		return c;
	}
}

/*
 * RayTraceResult lookingAt = Bearly.mc.objectMouseOver; if (lookingAt != null
 * && lookingAt.getType() == RayTraceResult.Type.BLOCK) { Vector3d pos =
 * lookingAt.getHitVec(); RenderUtilOverlay.drawText(event.getMatrixStack(), 20,
 * "Target" + pos.toString()); RenderUtilOverlay.getGLLineToEntity(entity); }
 */

/*
 * Tessellator tessellator = Tessellator.getInstance(); BufferBuilder
 * bufferBuilder = tessellator.getBuffer(); GL11.glLineWidth(3.0f);
 * GL11.glColor4f(0.8f, 0.8f, 0.2f, 1.0f); bufferBuilder.begin(GL11.GL_LINES,
 * DefaultVertexFormats.POSITION); bufferBuilder.pos(e.getPosX() - 0.5,
 * e.getPosY() + 1, e.getPosZ() + 0.5).endVertex();
 * bufferBuilder.pos(e.getPosX() - 0.5, e.getPosY() + 1, e.getPosZ() -
 * 0.5).endVertex(); bufferBuilder.pos(e.getPosX() - 0.5, e.getPosY() + 1,
 * e.getPosZ() - 0.5).endVertex(); bufferBuilder.pos(e.getPosX() + 0.5,
 * e.getPosY() + 1, e.getPosZ() - 0.5).endVertex();
 * bufferBuilder.pos(e.getPosX() + 0.5, e.getPosY() + 1, e.getPosZ() -
 * 0.5).endVertex(); bufferBuilder.pos(e.getPosX() + 0.5, e.getPosY() + 1,
 * e.getPosZ() + 0.5).endVertex(); bufferBuilder.pos(e.getPosX() + 0.5,
 * e.getPosY() + 1, e.getPosZ() + 0.5).endVertex();
 * bufferBuilder.pos(e.getPosX() - 0.5, e.getPosY() + 1, e.getPosZ() +
 * 0.5).endVertex(); tessellator.draw();
 */
