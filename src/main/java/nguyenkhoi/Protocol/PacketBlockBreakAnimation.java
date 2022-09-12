package nguyenkhoi.Protocol;

import org.bukkit.Bukkit;

import java.util.Objects;

import static nguyenkhoi.Protocol.Util.*;

public class PacketBlockBreakAnimation {
    private Object packet = null;
    private int entityID = 0;
    private BlockPosition blockPosition = new BlockPosition(0, 0, 0);
    private int destroyStage = 0;

    /**
     * Build NMS Packet
     * @return the built packet
     */
    private Object buildPacket() {
        try {
            Object nmsBlockPosition = this.blockPosition.getBlockPosition();
            Class<?> BlockPosition = getVersion() >= 17 ? Class.forName("net.minecraft.core.BlockPosition") :
                    Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage()
                            .getName().split("\\.")[3] + ".BlockPosition");
            return Objects.requireNonNull(getBlockBreakAnimationPacketClass()).getConstructor(int.class, BlockPosition, int.class)
                    .newInstance(this.entityID, nmsBlockPosition, this.destroyStage);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * @return the nms Packet
     */
    protected Object getPacket() {
        return this.packet;
    }

    /**
     * Construct the PacketBlockBreakAnimation
     * @param entityID - the bukkit EntityID
     * @param blockPosition - the protocol BlockPosition
     * @param destroyStage - the nms DestroyStage
     */
    public PacketBlockBreakAnimation(int entityID, BlockPosition blockPosition, int destroyStage) {
        try {
            this.entityID = entityID;
            this.blockPosition = blockPosition;
            this.destroyStage = destroyStage;
            this.packet = buildPacket();
        } catch (Exception ignored) {}
    }

    /**
     * Set the EntityID of this protocol packet
     * @param entityID - the EntityID will be set
     */
    public void setEntityID(int entityID) {
        this.entityID = entityID;
        this.packet = buildPacket();
    }

    /**
     * Set the BlockPosition of this protocol packet
     * @param blockPosition - the BlockPosition to set
     */
    public void setBlockPosition(BlockPosition blockPosition) {
        this.blockPosition = blockPosition;
        this.packet = buildPacket();
    }

    /**
     * Set the DestroyStage of this protocol packet
     * @param destroyStage - the DestroyStage to set
     */
    public void setDestroyStage(int destroyStage) {
        this.destroyStage = destroyStage;
        this.packet = buildPacket();
    }

    /**
     * @return the int represent EntityID
     */
    public int getEntityID() {
        return this.entityID;
    }

    /**
     * @return the protocol BlockPosition represent nms BlockPosition
     */
    public BlockPosition getBlockPosition() {
        return this.blockPosition;
    }

    /**
     * @return the int represent DestroyStage
     */
    public int getDestroyStage() {
        return this.destroyStage;
    }
}
