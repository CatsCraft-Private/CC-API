/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package EasterEgg.hunt.api;


import EasterEgg.hunt.api.Impl.CustomSkullImpl;

@Deprecated
public class CSkullPlugin {
	public static CustomSkull getSkullMaker () {
		return new CustomSkullImpl ();
	}
}
