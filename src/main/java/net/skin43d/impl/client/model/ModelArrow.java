package net.skin43d.impl.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.util.ResourceLocation;
import net.skin43d.impl.client.render.ModRenderHelper;

@SideOnly(Side.CLIENT)
public class ModelArrow {

    public static ModelArrow MODEL = new ModelArrow();

    private final ResourceLocation arrowTextures;
    private int displayList;

    public ModelArrow() {
        this.displayList = -1;
        this.arrowTextures = ReflectionHelper.getPrivateValue(RenderArrow.class, null, "arrowTextures", "field_110780_a");
    }

    @Override
    protected void finalize() throws Throwable {
        if (this.displayList != -1) {
            GLAllocation.deleteDisplayLists(displayList);
        }
        super.finalize();
    }

    public void render(float scale, boolean ghost) {
        if (displayList == -1) {
            buildDisplayList();
        }
        GL11.glPushMatrix();
        GL11.glRotatef(90F, 0F, 1F, 0F);
        GL11.glTranslatef(-3 * scale, -0.5F * scale, -0.5F * scale);
        ModRenderHelper.enableAlphaBlend();
        if (ghost) {
            GL11.glColor4f(1F, 1F, 1F, 0.25F);
        }
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        Minecraft.getMinecraft().getTextureManager().bindTexture(arrowTextures);
        GL11.glCallList(this.displayList);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        if (ghost) {
            GL11.glColor4f(1F, 1F, 1F, 1F);
        }
        ModRenderHelper.disableAlphaBlend();
        GL11.glPopMatrix();
    }

    private void buildDisplayList() {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(this.displayList, GL11.GL_COMPILE);

        GL11.glPushMatrix();
        VertexBuffer tessellator = Tessellator.getInstance().getBuffer();
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float) (0 + b0 * 10) / 32.0F;
        float f5 = (float) (5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float) (5 + b0 * 10) / 32.0F;
        float f9 = (float) (10 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;
        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
//        tessellator.startDrawingQuads();
        tessellator.begin(7, DefaultVertexFormats.POSITION_TEX);
        tessellator.pos(-7.0D, -2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
        tessellator.pos(-7.0D, -2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
        tessellator.pos(-7.0D, 2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
        tessellator.pos(-7.0D, 2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
        Tessellator.getInstance().draw();
        GL11.glNormal3f(-f10, 0.0F, 0.0F);

//        tessellator.startDrawingQuads();
        tessellator.begin(7, DefaultVertexFormats.POSITION_TEX);
        tessellator.pos(-7.0D, 2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
        tessellator.pos(-7.0D, 2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
        tessellator.pos(-7.0D, -2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
        tessellator.pos(-7.0D, -2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
        Tessellator.getInstance().draw();

        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
//            tessellator.startDrawingQuads();
            tessellator.begin(7, DefaultVertexFormats.POSITION_TEX);
            tessellator.pos(-8.0D, -2.0D, 0.0D).tex((double) f2, (double) f4).endVertex();
            tessellator.pos(8.0D, -2.0D, 0.0D).tex((double) f3, (double) f4).endVertex();
            tessellator.pos(8.0D, 2.0D, 0.0D).tex((double) f3, (double) f5).endVertex();
            tessellator.pos(-8.0D, 2.0D, 0.0D).tex((double) f2, (double) f5).endVertex();
            Tessellator.getInstance().draw();
        }

        GL11.glPopMatrix();
        GL11.glEndList();
    }
}
