package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.ReflectionUtil;
import api.brainsynder.commands.api.CommandListener;
import api.brainsynder.commands.api.CommandManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import simple.brainsynder.api.ParticleMaker;
import simple.brainsynder.storage.ExpireHashMap;
import simple.brainsynder.utils.Reflection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class CommandCore implements CommandListener {
    final List<String> allowed = Arrays.asList(
            "f3ff8cba-3d3e-493f-a6dc-4fc3e4ca0cc6",
            "19612e17-d090-483f-803c-e48efd0fe5a1",
            "05939e3d-a817-49d7-8390-be7ca48ac517",
            "4a4b597b-5415-4e6d-8c81-271cafa4d9d1",
            "58b9e74d-206e-439a-9675-54ca819c5de2"
    );
    public String prefix = "§eCatsCraft §6>> §7";
    ExpireHashMap<String, Object> cache = new ExpireHashMap<>();

    public static void registerCommands() {
    	CommandManager.register(new CommandBounce());
    	CommandManager.register(new CommandSonic());
    	CommandManager.register(new CommandPancake());
        CommandManager.register(new ScriptCommands());
        CommandManager.register(new CommandStealCats());
        CommandManager.register(new CommandFurBall());
        CommandManager.register(new CommandCookieDough());
        CommandManager.register(new CommandBow());
        CommandManager.register(new CommandComfort());
        CommandManager.register(new CommandCry());
        CommandManager.register(new CommandDabAttack());
        CommandManager.register(new CommandExplode());
        CommandManager.register(new CommandFart());
        CommandManager.register(new CommandHiss());
        CommandManager.register(new CommandMeow());
        CommandManager.register(new CommandPee());
        CommandManager.register(new CommandPurr());
        CommandManager.register(new CommandRage());
        CommandManager.register(new CommandRPS());
        CommandManager.register(new CommandSalty());
        CommandManager.register(new CommandSetServer());
        CommandManager.register(new CommandSpleef());
        CommandManager.register(new CommandThrowup());
        CommandManager.register(new CommandTime());
        CommandManager.register(new CommandDisco());
        CommandManager.register(new CommandTop20());
        CommandManager.register(new CommandQuote());
    }

    public static void destoryEntity(Player player, Entity entity) {
        Class<?> outEntityDestroy = Reflection.getNmsClass("PacketPlayOutEntityDestroy");
        Constructor<?> constructor = ReflectionUtil.fillConstructor(outEntityDestroy, Integer.TYPE);
        Object packet = ReflectionUtil.initiateClass(constructor, entity.getEntityId());
        Reflection.sendPacket(packet, player);
    }

    protected static Object connectSite(String _URL) {
        try {
            URL url = new URL(_URL); // Encode and set the URL
            URLConnection connection = url.openConnection(); // Connect to the site
            connection.addRequestProperty("User-Agent", "Mozilla/5.0"); // Setting the "Browser"
            connection.setConnectTimeout(5000); // Wait 5 Seconds Then destroy
            connection.setReadTimeout(5000); // Allow 5 seconds for reading
            connection.setDoOutput(true); // Is there an output from the site?
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Prepare to read the site
            String response = reader.readLine(); // Retrieve the first line on the site.
            reader.close(); // Close the Reader to prevent Memory leaks

            if (response != null) {
                if (response.startsWith("ERR")) {
                    return 500; // If the site contains ANY text print and Error: 500
                }
                return response;
            }
        } catch (Exception ignored) {
        }
        return 500; // If nothing bad happens, then print the OK code
    }

    void sendParticle(Player p, ParticleMaker.Particle effect, float offX, float offY, float offZ) {
        ParticleMaker maker = new ParticleMaker(effect, 0.0, 1, offX, offY, offZ);
        maker.sendToPlayer(p);
    }

    void sendParticle(Player p, Material mat, float offX, float offY, float offZ) {
        ParticleMaker maker = new ParticleMaker(ParticleMaker.Particle.ITEM_CRACK, 0.0, 40, offX, offY, offZ);
        maker.setData(mat);
        maker.sendToPlayer(p);
    }

    private void sendParticle(Location loc, Material mat, float offX, float offY, float offZ) {
        ParticleMaker maker = new ParticleMaker(ParticleMaker.Particle.ITEM_CRACK, 0.0, 40, offX, offY, offZ);
        maker.setData(mat);
        maker.sendToLocation(loc);
    }

    void timedDelay(final Player player, final Material mat, int time) {
        final Item item = player.getWorld().dropItem(player.getLocation(), new ItemStack(mat, 1));
        item.setPickupDelay(Integer.MAX_VALUE);
        item.setCustomNameVisible(true);
        item.setCustomName("§bSPLEEEFED!!!");
        item.setMetadata("takeable", new FixedMetadataValue(Core.get(), "takeable"));
        new BukkitRunnable() {
            @Override
            public void run() {
                item.remove();
                sendParticle(item.getLocation(), mat, 0.3F, 0.3F, 0.3F);
            }
        }.runTaskLater(Core.get(), time);
    }

    int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
