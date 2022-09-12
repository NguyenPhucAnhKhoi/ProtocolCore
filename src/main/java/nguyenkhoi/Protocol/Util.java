package nguyenkhoi.Protocol;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Objects;

public class Util {
    /**
     * @param player - player to get connection
     * @return player nms connection
     */
    protected static @Nullable Object getConnection(Player player) {
        try {
            Object craftPlayer = Objects.requireNonNull(getCraftPlayerClass()).cast(player);
            Object entityPlayer = craftPlayer.getClass().getMethod("getHandle").invoke(craftPlayer);
            Object playerConnection;
            if (getVersion() >= 17) {
                playerConnection = entityPlayer.getClass().getField("b").get(entityPlayer);
            } else {
                playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            }
            return playerConnection;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return the class represent nms Packet
     */
    protected static Class<?> getMainPacketClass() {
        try {
            return getVersion() >= 17 ? Class.forName("net.minecraft.network.protocol.Packet") : getNetMinecraftClass("Packet");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return the class represent nms PacketPlayOutBlockBreakAnimation
     */
    protected static @Nullable Class<?> getBlockBreakAnimationPacketClass() {
        try {
            return getVersion() >= 17 ? Class.forName("net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation")
                    : getNetMinecraftClass("PacketPlayOutBlockBreakAnimation");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return the class represent craft player
     */
    protected static @Nullable Class<?> getCraftPlayerClass() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + "entity.CraftPlayer");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get the class represent nms class, only use in 1.16.4 and downs
     * @param name - the name of class in nms package
     * @return the class represent nms class in nms package
     */
   protected static @Nullable Class<?> getNetMinecraftClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * It may throw exception if the server core isn't
     * a spigot, bukkit or vanilla server
     * @return version of spigot that server is using
     */
    @SuppressWarnings("UnknowVersionException")
    protected static int getVersion() {
        String v = Bukkit.getServer().getClass().getPackage().getName();
        v = v.substring(v.lastIndexOf(".") + 1).replace("v", "").trim();
        final String[] versionDetails = v.split("_");
        return Integer.parseInt(versionDetails[1]);
    }
}
