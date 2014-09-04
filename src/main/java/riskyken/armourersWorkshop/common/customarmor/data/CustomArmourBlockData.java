package riskyken.armourersWorkshop.common.customarmor.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class CustomArmourBlockData {
    
    private static final String TAG_X = "x";
    private static final String TAG_Y = "y";
    private static final String TAG_Z = "z";
    private static final String TAG_COLOUR = "colour";
    private static final String TAG_BLOCK_TYPE = "blockType";
    
    @Override
    public String toString() {
        return "ArmourBlockData [x=" + x + ", y=" + y + ", z=" + z
                + ", colour=" + colour + ", blockType=" + blockType + "]";
    }
    
    public byte x;
    public byte y;
    public byte z;
    public int colour;
    public byte blockType;
    
    public CustomArmourBlockData() {
    }
    
    public CustomArmourBlockData(int x, int y, int z, int colour, int blockType) {
        this.x = (byte) x;
        this.y = (byte) y;
        this.z = (byte) z;
        this.colour = colour;
        this.blockType = (byte) blockType;
    }
    
    public CustomArmourBlockData(byte x, byte y, byte z, int colour, byte blockType) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.colour = colour;
        this.blockType = blockType;
    }
    
    public CustomArmourBlockData(ByteBuf buf) {
        readFromBuf(buf);
    }
    
    public CustomArmourBlockData(NBTTagCompound compound) {
        readFromNBT(compound);
    }
    
    public void writeToBuf(ByteBuf buf) {
        buf.writeByte(x);
        buf.writeByte(y);
        buf.writeByte(z);
        buf.writeInt(colour);
        buf.writeByte(blockType);
    }
    
    private void readFromBuf(ByteBuf buf) {
        x = buf.readByte();
        y = buf.readByte();
        z = buf.readByte();
        colour = buf.readInt();
        blockType = buf.readByte();
    }
    
    public void writeToNBT(NBTTagCompound compound) {
        compound.setByte(TAG_X, x);
        compound.setByte(TAG_Y, y);
        compound.setByte(TAG_Z, z);
        compound.setInteger(TAG_COLOUR, colour);
        compound.setByte(TAG_BLOCK_TYPE, blockType);
    }
    
    private void readFromNBT(NBTTagCompound compound) {
        x = compound.getByte(TAG_X);
        y = compound.getByte(TAG_Y);
        z = compound.getByte(TAG_Z);
        colour = compound.getInteger(TAG_COLOUR);
        blockType = compound.getByte(TAG_BLOCK_TYPE);
    }
    
    public boolean isGlowing() {
        return this.blockType == 1;
    }
}