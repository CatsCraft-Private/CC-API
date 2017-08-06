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

import org.bukkit.Location;
import org.bukkit.block.Skull;

@Deprecated
public class EasterEgg {
	private Skull skull;
	private int id;
	private String hint;

	public EasterEgg (Skull skull, int id, String hint) {
		this.skull = skull;
		this.id = id;
		this.hint = hint;
	}

	public String getHint () {
		return hint;
	}

	public Location getLocation () {
		Location loc = skull.getLocation ();
		return loc;
	}

	public Skull getSkull () {
		return skull;
	}

	public int getId () {
		return id;
	}
}
