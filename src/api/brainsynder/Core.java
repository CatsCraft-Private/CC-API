/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package api.brainsynder;

import EasterEgg.hunt.MainEggHunt;
import api.brainsynder.Listeners.ChatListener;
import api.brainsynder.Utils.Configuration;
import api.brainsynder.Utils.FloatingItem;
import api.brainsynder.commands.CommandCore;
import api.brainsynder.manager.OnlineTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Core extends JavaPlugin {
    private static Core instance;
    public boolean spawnMe = false;
    public List<String> quotes = new ArrayList<>();
    private Configuration configuration;
    private boolean blockDabAttack = false;
    private boolean blockCommands = false;
    
    public void onEnable() {
        instance = this;
        CommandCore.registerCommands();
        configuration = new Configuration(this);
        if (!configuration.isSet("AFKCheck-Interval")) {
            configuration.set("AFKCheck-Interval", 120);
        }
        if (!configuration.isSet("DatabaseUpdate-Interval")) {
            configuration.set("DatabaseUpdate-Interval", 300);
        }
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        if (dateFormat.format(date).equals("04")) {
            MainEggHunt.load(this);
        }
        if (!configuration.isSet("Commands-To-Have-Cooldown")) {
            configuration.set("Commands-To-Have-Cooldown", Arrays.asList("kiss", "tpa", "etpa", "roar", "hug"));
        }
        if (!configuration.isSet("Cooldown-Seconds")) {
            configuration.set("Cooldown-Seconds", 10);
        }
    
        loadQuotes();
        quotes = configuration.getStringList("Insperational-quotes");
        FloatingItem.enable(this);
        if (configuration.isSet("ServerName")) {
            if (configuration.getString("ServerName", false).equals("off")) return;
            OnlineTime timer = new OnlineTime();
            getServer().getPluginManager().registerEvents(timer, this);
            new OnlineTime.DatabaseUpload().runTaskTimerAsynchronously(this, 0, (20 * configuration.getInt("DatabaseUpdate-Interval")));
            new OnlineTime.AFKCheckTimer().runTaskTimerAsynchronously(this, 0, (20 * configuration.getInt("AFKCheck-Interval")));
            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    OnlineTime.playerJoin(player.getUniqueId());
                }
            }
        }
    }
    
    
    private void loadQuotes() {
        if ((!configuration.isSet("Insperational-quotes")) || (configuration.getStringList("Insperational-quotes").isEmpty())) {
            List<String> ideas = new ArrayList<>();
            ideas.add("If plan 'A' does not work, there are 25 more letters in the alphabet.");
            configuration.set("Insperational-quotes", ideas);
        }
    }
    
    public void registerListener(Listener listenerClass) {
        Bukkit.getPluginManager().registerEvents(listenerClass, this);
    }
    
    public static Core get() {
        return instance;
    }
    
    public void onDisable() {
        FloatingItem.deleteAll();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                OnlineTime.playerLeave(p.getUniqueId());
                if (dateFormat.format(date).equals("04")) {
                    MainEggHunt.savePlayer(p);
                }
            }
            OnlineTime.DatabaseUpload.forceUpdate();
        }
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public boolean isBlockDabAttack() {
        return this.blockDabAttack;
    }

    public boolean isBlockCommands() {
        return this.blockCommands;
    }

    public void setBlockDabAttack(boolean blockDabAttack) {
        this.blockDabAttack = blockDabAttack;
    }

    public void setBlockCommands(boolean blockCommands) {
        this.blockCommands = blockCommands;
    }
}