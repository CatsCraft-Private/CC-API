package api.brainsynder.holidays.halloween;

import api.brainsynder.Utils.BlockLocation;
import api.brainsynder.commands.api.Command;
import api.brainsynder.holidays.HolidayEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;

public class Halloween extends HolidayEvents {
    public Halloween() {
        super("10");
    }


    @Override
    public void load() {
        super.load();
    }

    @Command(name = "setCenter")
    public void setCenter (Player player) {
        if (!player.hasPermission("")) {
            player.sendMessage(ChatColor.COLOR_CHAR + "c You do not have permission for this command.");
            return;
        }
        BlockLocation location = new BlockLocation(player.getLocation());
        location.save(new File(locDir, "SpawnLocation.nbt"));
        setCenter(location);
        player.sendMessage(ChatColor.COLOR_CHAR + "a Set the center point for the Holiday Features.");
    }
}
