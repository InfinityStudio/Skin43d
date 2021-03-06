package net.skin43d.impl.type.wings;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.skin43d.utils.Point3D;
import net.skin43d.utils.Rectangle3D;
import net.skin43d.skin3d.SkinType;
import net.skin43d.impl.client.model.ModelChest;
import net.skin43d.impl.type.AbstractSkinPartTypeBase;

public class SkinWingsPartLeftWing extends AbstractSkinPartTypeBase {

    public SkinWingsPartLeftWing(SkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-32, -24, 0, 32, 48, 12);
        this.guideSpace = new Rectangle3D(-4, -12, -4, 8, 12, 4);
        this.offset = new Point3D(0, -1, 0);
    }

    @Override
    public String getPartName() {
        return "leftWing";
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void renderBuildingGuide(float scale, boolean showSkinOverlay, boolean showHelper) {
        GL11.glTranslated(0, this.buildingSpace.getY() * scale, 0);
        
        GL11.glTranslated(0, -this.guideSpace.getY() * scale, 0);
        GL11.glTranslated(0, 0, -2 * scale);
        
        ModelChest.MODEL.renderChest(scale);
        
        GL11.glTranslated(0, 0, 2 * scale);
        GL11.glTranslated(0, this.guideSpace.getY() * scale, 0);
        
        GL11.glTranslated(0, -this.buildingSpace.getY() * scale, 0);
    }
    
    @Override
    public int getMaximumMarkersNeeded() {
        return 1;
    }
    
    @Override
    public int getMinimumMarkersNeeded() {
        return 1;
    }
}
