package net.skin43d.impl.client.render.bakery;

import java.util.*;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.skin43d.skin3d.ISkinDye;
import net.skin43d.impl.client.render.BakedCubes;
import net.skin43d.impl.skin.SkinDye;

@SideOnly(Side.CLIENT)
public class BakedPart {
    /**
     * Blank dye that is used if no dye is applied.
     */
    private static final SkinDye blankDye = new SkinDye();
    private HashMap<ModelKey, BakedCubes> dyeModels;

    public List<BakedFace>[] vertexLists;
    public int[] totalCubesInPart;

    private int[] averageR = new int[10];
    private int[] averageG = new int[10];
    private int[] averageB = new int[10];

    public BakedPart() {
        dyeModels = new HashMap<ModelKey, BakedCubes>();
    }

    public BakedCubes getModelForDye(ISkinDye skinDye, byte[] extraColours) {
        if (skinDye == null)
            skinDye = blankDye;
        ModelKey modelKey = new ModelKey(skinDye, extraColours);
        BakedCubes skinModel = dyeModels.get(modelKey);
        if (skinModel == null) {
            skinModel = new BakedCubes(vertexLists);
            dyeModels.put(modelKey, skinModel);
        }
        return skinModel;
    }

    public void cleanUpDisplayLists() {
        Set keys = dyeModels.keySet();
        Iterator<ModelKey> i = dyeModels.keySet().iterator();
        while (i.hasNext()) {
            ModelKey modelKey = i.next();
            dyeModels.get(modelKey).cleanUpDisplayLists();
        }
    }

    public int getModelCount() {
        return dyeModels.size();
    }

    public void setVertexLists(List<BakedFace>[] vertexLists) {
        this.vertexLists = vertexLists;
    }

    public void setAverageDyeValues(int[] r, int[] g, int[] b) {
        this.averageR = r;
        this.averageG = g;
        this.averageB = b;
    }

    public int[] getAverageDyeColour(int dyeNumber) {
        return new int[]{averageR[dyeNumber], averageG[dyeNumber], averageB[dyeNumber]};
    }

    public List<BakedFace>[] getVertexes() {
        return vertexLists;
    }

    private class ModelKey {

        private ISkinDye skinDye;
        byte[] extraColours;

        ModelKey(ISkinDye skinDye, byte[] extraColours) {
            this.skinDye = skinDye;
            this.extraColours = extraColours;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(extraColours);
            result = prime * result
                    + ((skinDye == null) ? 0 : skinDye.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ModelKey other = (ModelKey) obj;
            if (!Arrays.equals(extraColours, other.extraColours))
                return false;
            if (skinDye == null) {
                if (other.skinDye != null)
                    return false;
            } else if (!skinDye.equals(other.skinDye))
                return false;
            return true;
        }
    }
}
