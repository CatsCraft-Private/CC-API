package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandTop20 extends CommandCore {
    
    @Command(name = "top20")
    public void run(Player p) {
        if (!Core.get().getConfiguration().isSet("ServerName")) return;
        if (Core.get().getConfiguration().getString("ServerName", false).equals("off")) return;
        if (Cooldown.hasCooldown(p, 20)) {
            return;
        }
        Cooldown.giveCooldown(p);
        if (cache.containsKey("top20")) {
            JSONObject json = (JSONObject)cache.get("top20");
            JSONArray array = (JSONArray) json.get("list");
            int i = 1;
            List<String> lines = new ArrayList<>();
            p.sendMessage(prefix + "Here is the top 20 list for this server...");
            for (Object obj : array) {
                JSONObject j = (JSONObject) obj;
                String name = String.valueOf(j.get("Username"));
                ChatColor color = ChatColor.GOLD;
                if (name.equals(p.getName()))
                    color = ChatColor.AQUA;
                String builder = String.valueOf(ChatColor.YELLOW) +
                        i +
                        ChatColor.RED +
                        " | " +
                        color +
                        j.get("Username") +
                        ChatColor.RED +
                        " | " +
                        ChatColor.GRAY +
                        j.get("Time");
                lines.add(builder);
                i++;
            }
            for (String s : lines)
                p.sendMessage(s);
        }else {
            String server = Core.get().getConfiguration().getString("ServerName", false);
            System.setProperty("http.agent", "Chrome");
            try {
                URL url = new URL("http://onlinetime.tk/topweekly.php?server=" + server);
                URLConnection connection = url.openConnection();
                connection.addRequestProperty("User-Agent", "Mozilla/5.0"); // Setting the "Browser"
                connection.addRequestProperty("Content-Encoding", "gzip"); // Set the Encoding
                connection.setConnectTimeout(5000); // Wait 10 Seconds Then destroy
                connection.setReadTimeout(5000); // Allow 10 seconds for reading
                InputStream inputStream = connection.getInputStream();
                JSONObject json = (JSONObject) JSONValue.parseWithException(IOUtils.toString(inputStream));
                cache.put("top20", json, 10, TimeUnit.MINUTES);
                JSONArray array = (JSONArray) json.get("list");
                int i = 1;
                List<String> lines = new ArrayList<>();
                p.sendMessage(prefix + "Here is the top 20 list for this server...");
                for (Object obj : array) {
                    JSONObject j = (JSONObject) obj;
                    String name = String.valueOf(j.get("Username"));
                    ChatColor color = ChatColor.GOLD;
                    if (name.equals(p.getName()))
                        color = ChatColor.AQUA;
                    String builder = String.valueOf(ChatColor.YELLOW) +
                            i +
                            ChatColor.RED +
                            " | " +
                            color +
                            j.get("Username") +
                            ChatColor.RED +
                            " | " +
                            ChatColor.GRAY +
                            j.get("Time");
                    lines.add(builder);
                    i++;
                }
                for (String s : lines)
                    p.sendMessage(s);
            } catch (Exception e) {
                p.sendMessage(prefix + "Could not connect to the database, Please try again later");
            }
        }
    }
}
