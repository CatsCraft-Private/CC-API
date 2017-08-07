/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package api.brainsynder.Utils;

import api.brainsynder.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UtilFlash {
    private static Constructor<?> packetPlayOutAnimation;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;
    private static List<String> redPlayers = new ArrayList();

    static {
        try {
            packetPlayOutAnimation = getMCClass("PacketPlayOutAnimation").getConstructor(getMCClass("Entity"), Integer.TYPE);

            getHandle = getCraftClass("entity.CraftPlayer").getMethod("getHandle");

            playerConnection = getMCClass("EntityPlayer").getDeclaredField("playerConnection");

            sendPacket = getMCClass("PlayerConnection").getMethod("sendPacket", getMCClass("Packet"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UtilFlash() {
        start();
    }

    public static void addRed(Player p) {
        redPlayers.add(p.getName());
    }

    public static void removeRed(Player p) {
        if (redPlayers.contains(p.getName())) {
            redPlayers.remove(p.getName());
        }
    }

    public static boolean isRed(Player p) {
        return !redPlayers.contains(p.getName());
    }

    public static boolean isRed(String name) {
        return !redPlayers.contains(name);
    }

    public static void meRed(Player p) {
        try {
            Object nms_entity = UtilFlash.getHandle.invoke(p);
            Object packet = UtilFlash.packetPlayOutAnimation.newInstance(nms_entity, 1);
            for (Player pl : p.getWorld().getPlayers()) {
                if (pl.getLocation().distance(p.getLocation()) <= 50.0D) {
                    Object nms_player = UtilFlash.getHandle.invoke(pl);
                    Object nms_connection = UtilFlash.playerConnection.get(nms_player);
                    UtilFlash.sendPacket.invoke(nms_connection, packet);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getMCClass(String name) throws ClassNotFoundException {
        String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + '.';
        String className = "net.minecraft.server." + version + name;
        return Class.forName(className);
    }

    private static Class<?> getCraftClass(String name) throws ClassNotFoundException {
        String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + '.';
        String className = "org.bukkit.craftbukkit." + version + name;
        return Class.forName(className);
    }

    public void addRed(String name) {
        redPlayers.add(name);
    }

    public void removeRed(String name) {
        if (redPlayers.contains(name)) {
            redPlayers.remove(name);
        }
    }

    private void start() {
        new BukkitRunnable() {
            public void run() {
                for (String name : UtilFlash.redPlayers) {
                    try {
                        Player p = Bukkit.getServer().getPlayerExact(name);
                        if (p != null) {
                            Object nms_entity = UtilFlash.getHandle.invoke(p);
                            Object packet = UtilFlash.packetPlayOutAnimation.newInstance(nms_entity, 1);
                            for (Player pl : p.getWorld().getPlayers()) {
                                if (!pl.equals(p)) {
                                    if (pl.getLocation().distance(p.getLocation()) <= 50.0D) {
                                        Object nms_player = UtilFlash.getHandle.invoke(pl);
                                        Object nms_connection = UtilFlash.playerConnection.get(nms_player);
                                        UtilFlash.sendPacket.invoke(nms_connection, packet);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.runTaskTimer(Core.get(), 1L, 20L);
    }
}