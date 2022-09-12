package nguyenkhoi.Protocol;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import static nguyenkhoi.Protocol.Util.getVersion;

public class BlockPosition {
    private Object blockPosition = null;
    protected int x = 0;
    protected int y = 0;
    protected int z = 0;

    private void buildBlockPosition() {
        try {
            Class<?> BlockPosition = getVersion() >= 17 ? Class.forName("net.minecraft.core.BlockPosition") :
                    Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage()
                            .getName().split("\\.")[3] + ".BlockPosition");
            this.blockPosition = BlockPosition.getConstructor(int.class, int.class, int.class)
                    .newInstance(this.x, this.y, this.z);
        } catch (Exception e) {}
    }

    /**
     * Construct BlockPosition by minecraft position
     * @param x - x coordinate
     * @param y - y coordinate
     * @param z - z coordinate
     */
    public BlockPosition(int x, int y, int z) {
        try {
            this.x = x;
            this.y = y;
            this.z = z;
            buildBlockPosition();
        } catch (Exception ignored) {}
    }

    /**
     * Construct BlockPosition by bukkit Vector
     * @param vector - the bukkit vector
     */
    public BlockPosition(Vector vector) {
        try {
            if (vector == null)
                throw new IllegalArgumentException("Vector can't be NULL.");
            this.x = vector.getBlockX();
            this.y = vector.getBlockY();
            this.z = vector.getBlockZ();
            buildBlockPosition();
        } catch (Exception ignored) {}
    }

    /**
     * Construct BlockPosition by bukkit Location
     * @param location - the bukkit location
     */
    public BlockPosition(Location location) {
        try {
            if (location == null)
                throw new IllegalArgumentException("Location can't be NULL.");
            this.x = location.getBlockX();
            this.y = location.getBlockY();
            this.z = location.getBlockZ();
            buildBlockPosition();
        } catch (Exception ignored) {}
    }

    /**
     * @return the NMS BlockPosition
     */
    protected Object getBlockPosition() {
        return this.blockPosition;
    }

    /**
     * Convert BlockPosition to a bukkit Vector
     * @return the vector was converted
     */
    public Vector toVector() {
       return new Vector(this.x, this.y, this.z);
    }

    /**
     * Convert BlockPosition to a bukkit World
     * @param world - the bukkit world
     * @return the world was converted
     */
    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z);
    }

    /**
     * @return the x-coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return the z-coordinate
     */
    public int getZ() {
      return this.z;
    }

    /**
     * Adds the current BlockPosition and a given BlockPosition together
     * @param other - the other BlockPosition
     * @return new BlockPosition
     */
    public BlockPosition add(BlockPosition other) {
        if (other == null)
            throw new IllegalArgumentException("other can't be NULL");
        return new BlockPosition(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Subtracts the current BlockPosition and a given BlockPosition together
     * @param other - the other BlockPosition
     * @return new BlockPosition
     */
    public BlockPosition subtract(BlockPosition other) {
        if (other == null)
            throw new IllegalArgumentException("other can't be NULL");
        return new BlockPosition(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     * Multiples the current BlockPosition and a given BlockPosition together
     * @param other - the other BlockPosition
     * @return new BlockPosition
     */
    public BlockPosition multiply(BlockPosition other) {
        if (other == null)
            throw new IllegalArgumentException("other can't be NULL");
        return new BlockPosition(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    /**
     * Divides the current BlockPosition and a given BlockPosition together
     * @param other - the other BlockPosition
     * @return new BlockPosition
     */
    public BlockPosition divide(BlockPosition other) {
        if (other == null)
            throw new IllegalArgumentException("other can't be NULL");
        return new BlockPosition(this.x / other.x, this.y / other.y, this.z / other.z);
    }
}
