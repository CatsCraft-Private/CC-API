package api.brainsynder.holidays;

import api.brainsynder.Core;
import api.brainsynder.Utils.BlockLocation;
import api.brainsynder.commands.api.CommandListener;
import api.brainsynder.commands.api.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("ALL")
public abstract class HolidayEvents implements CommandListener, Listener {
    private SimpleDateFormat format;
    private String month;
    public final Core instance;
    // The Directory where all Locations are stored...
    protected File locDir;
    // the Directory where all Entities are stored...
    protected File entLoc;
    /**
     * How Creative Spawn is setup...
     *
     *    -Z
     * -X  C +X
     *    +Z
     */
    protected BlockLocation center = null;

    public HolidayEvents (String month) {
        this.month = month;
        instance = Core.get();
        locDir = new File(instance.getDataFolder().toString() + File.separator + "BlockLocations" + File.separator);
        entLoc = new File(instance.getDataFolder().toString() + File.separator + "Entities" + File.separator);
        try {
            center = BlockLocation.fromFile(locDir, "SpawnLocation");
        }catch (Exception ignored){}
        format = new SimpleDateFormat("MM");
    }

    public boolean fileExists (File folder, String fileName) {
        if (!folder.exists()) folder.mkdirs();
        File file = new File(folder, fileName + ".nbt");
        if (!file.exists()) return false;
        return true;
    }

    public boolean isMonth () {
        Date date = new Date();
        return format.format(date).equals(month);
    }

    public void load () {
        CommandManager.register(this);
        Bukkit.getPluginManager().registerEvents(this, Core.get());
    }

    public void unLoad () {
        if (center != null) center.save(locDir, "SpawnLocation");
    }

    public void setCenter(BlockLocation center) {
        this.center = center;
    }
}
