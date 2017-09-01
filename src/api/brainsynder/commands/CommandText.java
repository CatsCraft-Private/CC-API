package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.commands.api.Command;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;

public class CommandText extends CommandCore {
    private void showLine (Location loc, String text) {
        Core.get().spawnMe = true;
        ArmorStand stand = (ArmorStand) loc.getWorld ().spawnEntity (loc, EntityType.ARMOR_STAND);
        stand.setCustomNameVisible (true);
        stand.setCustomName (text);
        stand.setBasePlate (false);
        stand.setSmall (true);
        stand.setVisible (false);
        stand.setGravity (false);
        stand.setMetadata ("NoDamage", new FixedMetadataValue(Core.get(), "NoDamage"));
        stand.setMetadata ("NO_TOUCH", new FixedMetadataValue (Core.get(), "NO_TOUCH"));
    }

    @Command(name = "text")
    public void text(Player player, String[] args) {
        if (allowed.contains(player.getUniqueId().toString())) {
            player.sendMessage("§cYou do not have permission to this command.");
            return;
        }
        if (args.length == 0) {
            player.sendMessage ("/text < Text, For multiple lines do: text;text >");
        }
        else {
            StringBuilder msgBuilder = new StringBuilder ();
            for (int i = 0;i < args.length;i++) {
                msgBuilder.append (args[ i ]).append (" ");
            }
            String msg = ChatColor.translateAlternateColorCodes ('&', msgBuilder.toString ().trim ().replace ("&k", "").replace ("§k", ""));
            String[] msgs = msg.split (";");
            Location loc = player.getLocation ();
            Location first = loc.add (0.0D, 0.2D, 0.0D);
            for (int i = 0; i < Arrays.asList (msgs).size (); i++) {
                showLine (first, Arrays.asList (msgs).get (i));
                first.subtract (0.0D, 0.23D, 0.0D);
            }
        }
    }
}
