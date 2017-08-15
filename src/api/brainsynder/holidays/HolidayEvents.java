package api.brainsynder.holidays;

import api.brainsynder.Core;
import api.brainsynder.Utils.BlockLocation;
import api.brainsynder.commands.api.CommandListener;
import api.brainsynder.commands.api.CommandManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("ALL")
public abstract class HolidayEvents implements CommandListener {
    private SimpleDateFormat format;
    private String month;
    public final Core instance;
    protected File locDir;
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
        try {
            center = BlockLocation.fromFile(new File(locDir, "SpawnLocation.nbt"));
        }catch (Exception ignored){}
        format = new SimpleDateFormat("MM");
    }

    public boolean isMonth () {
        Date date = new Date();
        return format.format(date).equals(month);
    }

    public void load () {
        CommandManager.register(this);
    }

    public void unLoad () {
        if (center != null) center.save(new File(locDir, "SpawnLocation.nbt"));
    }

    public void setCenter(BlockLocation center) {
        this.center = center;
    }
}
