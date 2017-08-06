package api.brainsynder.manager;

import api.brainsynder.Core;
import api.brainsynder.afk_manager.AFKPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import simple.brainsynder.utils.Base64Wrapper;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.zip.GZIPOutputStream;

public class OnlineTime implements Listener {
    private static Map<UUID, Long> timeMap = new HashMap<>();
    private static List<UUID> toRemove = new ArrayList<>();
    private static Map<UUID, Long> currentTimes = new HashMap<> ();
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (timeMap.containsKey(e.getPlayer().getUniqueId())) {
            playerLeave(e.getPlayer().getUniqueId());
        }
        
        playerJoin(e.getPlayer().getUniqueId());
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (timeMap.containsKey(e.getPlayer().getUniqueId())) {
            playerLeave(e.getPlayer().getUniqueId());
        }
        AFKPlayer.getPlayer(e.getPlayer().getUniqueId()).remove();
    }
    
    @EventHandler
    public void onLeave(PlayerKickEvent e) {
        if (timeMap.containsKey(e.getPlayer().getUniqueId())) {
            playerLeave(e.getPlayer().getUniqueId());
        }
        AFKPlayer.getPlayer(e.getPlayer().getUniqueId()).remove();
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        AFKPlayer afkPlayer = AFKPlayer.getPlayer(p.getUniqueId());
        Location loc = p.getLocation();
        if (afkPlayer.isAfk()) {
            if (!loc.getDirection().equals(afkPlayer.getLastLocation().getDirection())) {
                playerJoin(p.getUniqueId());
                afkPlayer.setAfk(false);
            }
        }
    }
    
    public static void playerJoin(UUID id) {
        timeMap.put(id, System.currentTimeMillis());
    }
    
    public static void playerLeave(UUID id) {
        playerLeave (id, true);
    }
    
    static void playerLeave(UUID id, boolean addTimes) {
        if (addTimes) {
            if (!toRemove.contains(id)) toRemove.add(id);
            addPlayTime(id);
        }
        timeMap.remove(id);
    }
    
    private static void addPlayTime(UUID id) {
        if (!timeMap.containsKey(id)) return;
        if (currentTimes.containsKey(id)) currentTimes.remove(id);
        long saved = timeMap.get(id);
        long current = System.currentTimeMillis();
        long newTime = (current - saved);
        currentTimes.put(id, newTime);
    }
    
    public static class AFKCheckTimer extends BukkitRunnable {
        @Override
        public void run() {
            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Location loc = p.getLocation();
                    AFKPlayer afkPlayer = AFKPlayer.getPlayer(p.getUniqueId());
                    if (afkPlayer.getLastLocation() == null) {
                        afkPlayer.setLastLocation(p.getLocation());
                    } else {
                        Location lastLoc = afkPlayer.getLastLocation();
                        if (!loc.getDirection().equals(lastLoc.getDirection())) {
                            afkPlayer.setLastLocation(p.getLocation());
                            if (afkPlayer.isAfk()) {
                                afkPlayer.setAfk(false);
                                playerJoin(p.getUniqueId());
                            }
                        } else {
                            if (!afkPlayer.isAfk()) {
                                playerLeave(p.getUniqueId());
                                afkPlayer.setAfk(true);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static class DatabaseUpload extends BukkitRunnable {
        private static JSONArray array = new JSONArray();
        private static final String _URL = "http://onlinetime.tk/update.php?server={server}&data=";
        
        static byte[] gzip(String input) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzos = null;
            try {
                gzos = new GZIPOutputStream(baos);
                gzos.write(input.getBytes("UTF-8"));
            } catch (IOException e) {
                System.out.println("ERROR: Could not Compress the uploaded data via GZip");
            } finally {
                if (gzos != null) {
                    try {
                        gzos.close();
                    } catch (IOException ignore) {
                    }
                }
            }
            return baos.toByteArray();
        }
        
        @Override public void run() {
            forceUpdate();
        }
        
        public static void forceUpdate() {
            if (!Core.get().getConfiguration().isSet("ServerName")) return;
            if (Core.get().getConfiguration().getString("ServerName", false).equals("off")) return;
            if (timeMap.isEmpty() && currentTimes.isEmpty()) return;
            array.clear(); // Clears the previously sent data
            
            // Reloads the timeMap to re-add non-afk players
            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!timeMap.containsKey(player.getUniqueId())) {
                        if (!AFKPlayer.isAFK(player.getUniqueId())) {
                            playerJoin(player.getUniqueId());
                        }
                    }
                }
            }
            
            // Updates the current online time and resets it.
            if (!timeMap.isEmpty())
                for (UUID uuid : timeMap.keySet()) {
                    if (Bukkit.getPlayer(uuid) == null) {
                        if (!toRemove.contains(uuid))
                            toRemove.add(uuid);
                    } else {
                        AFKPlayer afkPlayer = AFKPlayer.getPlayer(uuid);
                        if (afkPlayer.isAfk()) {
                            playerLeave(uuid);
                            continue;
                        }
                    }
                    addPlayTime(uuid);
                }
            
            // Adds the online time to a JSONObject in preparation to uploading
            if (!currentTimes.isEmpty())
                for (UUID uuid : currentTimes.keySet()) {
                    if (!timeMap.containsKey(uuid)) {
                        if (!toRemove.contains(uuid))
                            toRemove.add(uuid);
                    }
                    JSONObject json = new JSONObject();
                    json.put("UUID", uuid.toString());
                    json.put("USER_NAME", Bukkit.getOfflinePlayer(uuid).getName());
                    json.put("STORAGE_TIME", currentTimes.get(uuid));
                    array.add(json);
                }
            
            currentTimes.clear();
            if (!toRemove.isEmpty())
                for (UUID uuid : toRemove) {
                    if (timeMap.containsKey(uuid)) {
                        if (Bukkit.getPlayer(uuid) == null) {
                            timeMap.remove(uuid);
                        }
                    }
                }
            toRemove.clear();
            
            /**
             * Uploads the data to the Site.
             *
             * @return
             * 200 = ok
             * 000 = Reset Times
             * 500 = error
             */
            int code = upload();
            if (code == 500) {
                System.out.println("ERROR: Unable to connect the http://onlinetime.tk to update the OnlineTimes...");
            } else if (code == 0) {
                for (UUID uuid : timeMap.keySet()) {
                    playerLeave(uuid, false);
                    new Thread(() -> playerJoin(uuid));
                }
                currentTimes.clear();
                System.out.println("Notice: Player Online Times have been updated AND reset due to weekly times");
            } else {
                System.out.println("Notice: Player Online Times have been updated.");
            }
        }
        
        private static int upload() {
            try {
                URL url = new URL(_URL.replace("{server}", Core.get().getConfiguration().getString("ServerName", false))
                        + Base64Wrapper.encodeString(array.toJSONString())); // Encode and set the URL
                URLConnection connection = url.openConnection(); // Connect to the site
                byte[] compressed = gzip(array.toString()); // Compress the Transfer data
                connection.addRequestProperty("User-Agent", "Mozilla/5.0"); // Setting the "Browser"
                connection.addRequestProperty("Content-Encoding", "gzip"); // Set the Encoding
                connection.addRequestProperty("Content-Length", Integer.toString(compressed.length)); // Setting the size of the data
                connection.addRequestProperty("Connection", "close");
                connection.setConnectTimeout(5000); // Wait 10 Seconds Then destroy
                connection.setReadTimeout(5000); // Allow 10 seconds for reading
                connection.setDoOutput(true); // Is there an output from the site?
                OutputStream os = connection.getOutputStream(); // Open the OutputStream to Output data
                os.write(compressed); // Sending the compressed data (Speeds up the Transfer)
                os.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Prepare to read the site
                String response = reader.readLine(); // Retrieve the first line on the site.
                os.close(); // Close the Output Stream to prevent Memory leaks
                reader.close(); // Close the Reader to prevent Memory leaks
                
                if (response != null) {
                    if (response.startsWith("ERR")) {
                        return 500; // If the site contains ANY text print and Error: 500
                    } else if (response.contains("RESET 000")) {
                        return 0; // If the site contains ANY text print and Error: 500
                    }
                }
            } catch (Exception ignored) {
            }
            return 200; // If nothing bad happens, then print the OK code
        }
    }
}
