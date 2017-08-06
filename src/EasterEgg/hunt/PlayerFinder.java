/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package EasterEgg.hunt;

import org.bukkit.entity.Player;

import java.util.List;

@Deprecated
public class PlayerFinder {
	private Player p;
	private int found;
	private List<String> eggIds;

	public PlayerFinder (Player p) {
		this.p = p;
		eggIds = PlayerFile.getList (p, "Egg-Ids-Found");
		this.found = PlayerFile.getInt (p, "Eggs-Found");
	}

	public List<String> getEggIds () {
		return eggIds;
	}

	public boolean isNextEgg (EasterEgg egg) {
		if (found == (egg.getId () - 1)) {
			return true;
		}
		return false;
	}

	public String getNextHint () {
		int id = (found + 1);
		String hint = EggFile.getString ("Eggs." + id + ".Hint");
		return hint;
	}

	public boolean isOnLast () {
		if ((found == 10)) {
			return true;
		}
		return false;
	}

	public void save () {
		PlayerFile.set (p, "Egg-Ids-Found", eggIds);
		PlayerFile.set (p, "Eggs-Found", found);
	}

	public void addFound (EasterEgg egg) {
		found = (found + 1);
		eggIds.add (String.valueOf(egg.getId()));
	}

	public boolean hasFound (int i) {
		if (found == i) {
			return true;
		}
		return false;
	}


}
