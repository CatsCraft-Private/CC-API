package api.brainsynder.afk_manager;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class AFKPlayer {
    public static HashMap<UUID, AFKPlayer> all = new HashMap();
    private UUID uuid;
    private boolean afk = false;
    private Location lastLocation;
    
    private AFKPlayer(UUID uuid) {
        all.put(uuid, this);
        this.uuid = uuid;
    }
    
    public static boolean isAFK(UUID uuid) {
        return getPlayer(uuid).afk;
    }
    
    public static AFKPlayer getPlayer(UUID uuid) {
        return !all.containsKey(uuid) ? all.put(uuid, new AFKPlayer(uuid)) : all.get(uuid);
    }
    
    public void remove() {
        all.remove(this.uuid);
    }

    public boolean isAfk() {
        return this.afk;
    }

    public Location getLastLocation() {
        return this.lastLocation;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }
}