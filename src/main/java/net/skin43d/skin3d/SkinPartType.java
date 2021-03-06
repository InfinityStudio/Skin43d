package net.skin43d.skin3d;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.skin43d.utils.Point3D;
import net.skin43d.utils.Rectangle3D;


public interface SkinPartType {

    /**
     * Gets the name this skin will be registered with.
     * Armourer's Workshop uses the format baseType.getRegistryName() + "." + getPartName().
     * Example armourers:chest.leftArm is the registry name of
     * Armourer's Workshop chest left arm skin part.
     *
     * @return Registry name
     */
    String getRegistryName();

    /**
     * Get the name of this part.
     *
     * @return Part name
     */
    String getPartName();

    SkinType getBaseType();

    /**
     * The last 3 values are used to define the size of this part, the first 3 values will change the origin.
     * Example -5, -5, -5, 10, 10, 10, Will create a 10x10x10 cube with it's origin in the centre.
     */
    Rectangle3D getBuildingSpace();

    /**
     * The last 3 values set the size of the invisible blocks that cubes can be placed on, the first 3 set the offset.
     * Use 0, 0, 0, 0, 0, 0, if you don't want to use this.
     * Setting showArmourerDebugRender to true in the config will show this box.
     *
     * @return
     */
    Rectangle3D getGuideSpace();

    /**
     * This is used by the armourer to position this part
     *
     * @return
     */
    Point3D getOffset();

    /**
     * @param scale           Normally 0.0625F.
     * @param showSkinOverlay
     * @param showHelper
     */
    @SideOnly(Side.CLIENT)
    void renderBuildingGuide(float scale, boolean showSkinOverlay, boolean showHelper);

    /**
     * Get the minimum number of markers needed for this skin part.
     *
     * @return
     */
    int getMinimumMarkersNeeded();

    /**
     * Gets the maximum number of markers allowed for this skin part.
     *
     * @return
     */
    int getMaximumMarkersNeeded();

    /**
     * If true this part must be present for the skin to be saved.
     *
     * @return
     */
    boolean isPartRequired();
}
