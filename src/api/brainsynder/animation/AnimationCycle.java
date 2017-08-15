package api.brainsynder.animation;

import api.brainsynder.Core;
import api.brainsynder.wrappers.ArmorStandWrapper;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AnimationCycle {
    private ArrayList<AnimationFrame> frames = new ArrayList<>();
    private boolean toggle = true;
    private Map<UUID, ArrayList<AnimationFrame>> framesMap = new HashMap<>();
    private BukkitTask runnable;
    private ArmorStandWrapper stand;

    public AnimationCycle(ArmorStandWrapper stand, MovementFrames frames) {
        this.frames = frames.getFrames();
        this.stand = stand;
    }

    public void register(long delay) {
        if (!framesMap.containsKey(stand.getUUID()))
            framesMap.put(stand.getUUID(), frames);
        runnable = new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (!framesMap.containsKey(stand.getUUID())) {
                    this.cancel();
                    return;
                }
                if (!toggle) {
                    this.i = 0;
                    return;
                }
                ArrayList<AnimationFrame> frames = framesMap.get(stand.getUUID());
                if (this.i == frames.size()) {
                    this.i = 0;
                }

                try {
                    frames.get(this.i).setLocations(stand);
                } catch (Exception ignored) {
                }
                ++this.i;

            }
        }.runTaskTimer(Core.get(), 0, delay);
    }

    public boolean isRunning() {
        return toggle;
    }

    public void toggle(boolean var) {
        toggle = var;
    }

    public void cancelTask() {
        runnable.cancel();
    }
}
