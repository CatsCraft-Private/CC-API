package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import simple.brainsynder.storage.ExpireHashMap;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class CommandTime extends CommandCore {
    private ExpireHashMap<String, Object> playerCache = new ExpireHashMap<>();

    @Command(name = "onlinetime")
    public void run(Player p) {
        if (!Core.get().getConfiguration().isSet("ServerName")) return;
        if (Core.get().getConfiguration().getString("ServerName", false).equals("off")) return;
        if (Cooldown.hasCooldown(p, 20)) {
            return;
        }
        Cooldown.giveCooldown(p);
        if (playerCache.containsKey(p.getUniqueId().toString())) {
            JSONObject json = (JSONObject) playerCache.get(p.getUniqueId().toString());
            Object days = json.get("days");
            Object hours = json.get("hours");
            Object minutes = json.get("minutes");
            Object seconds = json.get("seconds");
            p.sendMessage("§7Total Online Time: §c" + days + " §eDay(s) §6| §c" + hours + " §eHour(s) §6| §c" + minutes + " §eMinute(s) §6| §c" + seconds + " §eSecond(s)");
            if (json.containsKey("0")) {
                JSONObject obj = (JSONObject) json.get("0");
                Object days1 = obj.get("days");
                Object hours1 = obj.get("hours");
                Object minutes1 = obj.get("minutes");
                Object seconds1 = obj.get("seconds");
                p.sendMessage("§7Weekly Online Time: §c" + days1 + " §eDay(s) §6| §c" + hours1 + " §eHour(s) §6| §c" + minutes1 + " §eMinute(s) §6| §c" + seconds1 + " §eSecond(s)");
            }
        } else {
            String server = Core.get().getConfiguration().getString("ServerName", false);
            System.setProperty("http.agent", "Chrome");
            try {
                URL url = new URL("http://onlinetime.tk/retrieve.php?uuid=" + p.getUniqueId().toString() + "&server=" + server);
                URLConnection connection = url.openConnection();
                connection.addRequestProperty("User-Agent", "Mozilla/5.0"); // Setting the "Browser"
                connection.addRequestProperty("Content-Encoding", "gzip"); // Set the Encoding
                connection.setConnectTimeout(5000); // Wait 10 Seconds Then destroy
                connection.setReadTimeout(5000); // Allow 10 seconds for reading
                InputStream inputStream = connection.getInputStream();
                JSONObject json = (JSONObject) JSONValue.parseWithException(IOUtils.toString(inputStream));
                playerCache.put(p.getUniqueId().toString(), json, 10, TimeUnit.MINUTES);
                Object days = json.get("days");
                Object hours = json.get("hours");
                Object minutes = json.get("minutes");
                Object seconds = json.get("seconds");
                p.sendMessage("§7Total Online Time: §c" + days + " §eDay(s) §6| §c" + hours + " §eHour(s) §6| §c" + minutes + " §eMinute(s) §6| §c" + seconds + " §eSecond(s)");
                if (json.containsKey("0")) {
                    JSONObject obj = (JSONObject) json.get("0");
                    Object days1 = obj.get("days");
                    Object hours1 = obj.get("hours");
                    Object minutes1 = obj.get("minutes");
                    Object seconds1 = obj.get("seconds");
                    p.sendMessage("§7Weekly Online Time: §c" + days1 + " §eDay(s) §6| §c" + hours1 + " §eHour(s) §6| §c" + minutes1 + " §eMinute(s) §6| §c" + seconds1 + " §eSecond(s)");
                }
            } catch (Exception e) {
                p.sendMessage(prefix + "Could not find your Online Time.");
            }
        }
    }
}
