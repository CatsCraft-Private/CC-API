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

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Cooldown {
	private static HashMap<String, Long> map = new HashMap<> ();
    
    public static boolean hasCooldown (Player p){
        return hasCooldown(p, 10);
    }
    
    public static boolean hasCooldown (Player p, int waitTime){
        if (map.containsKey (p.getName ())) {
            long secondsLeft = map.get (p.getName ()) / 1000L + waitTime - System.currentTimeMillis () / 1000L;
            if (secondsLeft > 0L) {
                p.sendMessage ("§eCatsCraft§6> §7This command has a cooldown with " + secondsLeft + " seconds left.");
                return true;
            }
        }
        return false;
    }

	public static void giveCooldown (Player p){
		map.put (p.getName (), System.currentTimeMillis ());
	}
}
