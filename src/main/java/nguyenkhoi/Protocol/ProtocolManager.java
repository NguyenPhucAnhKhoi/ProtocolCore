package nguyenkhoi.Protocol;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

import static nguyenkhoi.Protocol.Util.*;

public final class ProtocolManager {

    /**
     * Send the packet to player by nms
     * @param player - player will receive this packet
     * @param packet - the packet will be sent
     */
    private static void sendPacketNMS(Player player, PacketBlockBreakAnimation packet) {
        try {
            Object nmsPacket = packet.getPacket();
            Class<?> classPacket = getMainPacketClass();
            Object playerConnection = getConnection(player);
            Method sendPacket = getVersion() >= 17 ? Objects.requireNonNull(playerConnection).getClass().getMethod("a", classPacket)
                    : Objects.requireNonNull(playerConnection).getClass().getMethod("sendPacket", classPacket);
            sendPacket.invoke(playerConnection, nmsPacket);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Send the packet to player by protocol
     * @param player - player will receive this packet
     * @param packet - the packet will be sent
     */
    public static void sendPacket(Player player, PacketBlockBreakAnimation packet) {
        sendPacketNMS(player, packet);
    }

    /**
     * Send the packet to player by protocol if condition is met
     * @param player - player will receive this packet
     * @param packet - the packet will be sent
     */
    public static void sendPacketIf(Player player, PacketBlockBreakAnimation packet, Condition condition) {
        if (condition.condition(player)) {
            sendPacketNMS(player, packet);
        }
    }

    /**
     * Send the packet to other player by protocol
     * @param player - player is excluded
     * @param packet - the packet will be sent
     */
    public static void sendPacketOther(Player player, PacketBlockBreakAnimation packet) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.equals(player)) sendPacketNMS(p, packet);
        }
    }

    /**
     * Send the packet to other player by protocol if condition is met
     * @param player - player is excluded
     * @param packet - the packet will be sent
     */
    public static void sendPacketOtherIf(Player player, PacketBlockBreakAnimation packet, Condition condition) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.equals(player) && condition.condition(p)) sendPacketNMS(player, packet);
        }
    }

    /**
     * Send the packet to all player by protocol
     * @param packet - the packet will be sent
     */
    public static void sendPacketAll(PacketBlockBreakAnimation packet) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendPacketNMS(p, packet);
        }
    }

    /**
     * Send the packet to all player by protocol if condition is met
     * @param packet - the packet will be sent
     */
    public static void sendPacketAllIf(PacketBlockBreakAnimation packet, Condition condition) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (condition.condition(p)) sendPacketNMS(p, packet);
        }
    }

}
