package api.brainsynder.Utils;

import org.bukkit.plugin.Plugin;
import simple.brainsynder.files.FileMaker;

public class Configuration extends FileMaker {
    public Configuration(Plugin plugin) {
        super(plugin, "config.yml");
    }
}
