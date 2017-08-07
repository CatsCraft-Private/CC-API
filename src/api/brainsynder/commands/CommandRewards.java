package api.brainsynder.commands;

import api.brainsynder.Utils.Cooldown;
import api.brainsynder.Utils.UtilMath;
import api.brainsynder.commands.api.Command;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CommandRewards extends CommandCore {

    private float step = 0.0F;

    @Command(name = "rewards")
    public void run(Player p) {
        if (Cooldown.hasCooldown(p)) {
            return;
        }
        Cooldown.giveCooldown(p);

    }

    public void shape(Location location, ArmorStand stand) {
        for (int i = 0; i < 1; i++) {
            double inc = 6.283185307179586D / (10 * 20);
            double toAdd = 0.0D;
            if (i == 1) {
                toAdd = 3.5D;
            }
            double angle = step * inc + toAdd;
            Vector v = new Vector();
            v.setX(Math.cos(angle));
            v.setZ(Math.sin(angle));
            if (i == 0) {
                UtilMath.rotateAroundAxisZ(v, 10.0D);
            } else {
                UtilMath.rotateAroundAxisZ(v, 100.0D);
            }
            location.add(v);
            location.setYaw((location.getYaw() + 7.5F));
            stand.teleport(location);
            location.subtract(v);
        }
        step += 3.0F;
    }
}
