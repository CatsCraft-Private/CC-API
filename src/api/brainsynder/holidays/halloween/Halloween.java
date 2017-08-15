package api.brainsynder.holidays.halloween;

import api.brainsynder.Core;
import api.brainsynder.Utils.BlockLocation;
import api.brainsynder.animation.AnimationCycle;
import api.brainsynder.animation.AnimationManager;
import api.brainsynder.commands.api.Command;
import api.brainsynder.holidays.HolidayEvents;
import api.brainsynder.wrappers.ArmorStandWrapper;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import simple.brainsynder.api.SkullMaker;

public class Halloween extends HolidayEvents {
    private ArmorStandWrapper stand;
    private AnimationCycle cycle;

    public Halloween() {
        super("10");
    }

    @Override
    public void load() {
        super.load();
        instance.spawnMe = true;
        stand = new ArmorStandWrapper(center.getWorld().spawn(center.toLocation().add(0, 0, -3), ArmorStand.class));
        if (!fileExists(entLoc, "HalloweenStand")) {
            stand.getStand().setGravity(false);
            stand.getStand().setArms(false);
            stand.getStand().setBasePlate(false);
            stand.getStand().setSmall(false);
            stand.getStand().setVisible(false);
        }else{
            stand.loadFromFile(entLoc, "HalloweenStand");
        }
        instance.spawnMe = false;
        SkullMaker maker = new SkullMaker().setOwner("eyJ0aW1lc3RhbXAiOjE0OTI4OTY5MzQ2OTksInByb2ZpbGVJZCI6IjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwicHJvZmlsZU5hbWUiOiJHb2xkYXBmZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE0YjJhMzJmM2MxZWM0YzQ0ZDZkOGU5YTlmM2ZmNGRmYzIxZDdkZDc3NWI4YzBjZjFhODRkYjk4NjNhZGUxZTcifX19");
        stand.getEquipment().setHelmet(maker.create());
        stand.getEquipment().updateEquipment(stand);
        stand.getStand().setMetadata("NO_TOUCH", new FixedMetadataValue(Core.get(), "NO_TOUCH"));
        stand.saveData(entLoc, "HalloweenStand");
        cycle = new AnimationCycle(stand, AnimationManager.floating);
        cycle.register(1);
    }

    @Override
    public void unLoad() {
        super.unLoad();
        cycle.cancelTask();
        stand.saveData(entLoc, "HalloweenStand");
        stand.getStand().remove();
    }

    @Command(name = "setCenter")
    public void setCenter (Player player) {
        if (!player.hasPermission("")) {
            player.sendMessage(ChatColor.COLOR_CHAR + "c You do not have permission for this command.");
            return;
        }
        BlockLocation location = new BlockLocation(player.getLocation());
        location.save(locDir, "SpawnLocation");
        setCenter(location);
        player.sendMessage(ChatColor.COLOR_CHAR + "a Set the center point for the Holiday Features.");
    }
}