package net.skin43d.impl.type.arrow;

import net.skin43d.skin3d.SkinType;
import org.lwjgl.opengl.GL11;

import net.skin43d.utils.Point3D;
import net.skin43d.utils.Rectangle3D;
import net.skin43d.impl.client.model.ModelArrow;
import net.skin43d.impl.type.AbstractSkinPartTypeBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SkinArrowPartBase extends AbstractSkinPartTypeBase {

    public SkinArrowPartBase(SkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-5, -5, -2, 11, 11, 16);
        this.guideSpace = new Rectangle3D(0, 0, 0, 0, 0, 0);
        this.offset = new Point3D(0, 0, 0);
    }

    @Override
    public String getPartName() {
        return "base";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderBuildingGuide(float scale, boolean showSkinOverlay, boolean showHelper) {
        GL11.glTranslated(0, this.buildingSpace.getY() * scale, 0);
        GL11.glTranslated(0, -this.guideSpace.getY() * scale, 0);
        if (showHelper) {
            ModelArrow.MODEL.render(scale, true);
        }
        GL11.glTranslated(0, this.guideSpace.getY() * scale, 0);
        GL11.glTranslated(0, -this.buildingSpace.getY() * scale, 0);
    }
}
