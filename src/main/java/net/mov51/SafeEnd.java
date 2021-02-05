package net.mov51;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class SafeEnd extends JavaPlugin implements Listener {

    //Last Played Hash
    private HashMap<UUID, Long> hasJoinedBeforeReset = new HashMap<>();

    /**
     * When the plugin enables
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    /**
     * The event to run on the player join
     * @param event
     */
    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getPlayerProfile().getId());
        long resetTime = getConfig().getLong("ResetTime");
        if (offlinePlayer.getLastSeen() < resetTime) {
            hasJoinedBeforeReset.put(offlinePlayer.getUniqueId(), System.currentTimeMillis());
        }
    }

    /**
     * On the player join event
     * @param event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Object hasPlayedLastPlayed = hasJoinedBeforeReset.get(event.getPlayer().getUniqueId());
        if(hasPlayedLastPlayed != null) {
            String resetWorld = getConfig().getString("ResetWorld");
            if(resetWorld != null) {
                World world = Bukkit.getWorld(resetWorld);
                if(world != null && event.getPlayer().getWorld() == world) {
                    event.getPlayer().teleport(world.getSpawnLocation());
                }
            }
        }
        hasJoinedBeforeReset.remove(event.getPlayer().getUniqueId());
    }
}
