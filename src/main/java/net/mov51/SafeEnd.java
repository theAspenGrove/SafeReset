package net.mov51;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeEnd extends JavaPlugin implements Listener {

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
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        long resetTime = getConfig().getLong("ResetTime");
        String resetWorld = getConfig().getString("ResetWorld");

        if(resetWorld != null) {
            World world = Bukkit.getWorld(resetWorld);
            if(world != null && player.getWorld() == world) {
                if(player.getLastSeen() < resetTime) {
                    player.teleport(world.getSpawnLocation());
                }
            }
        }
    }
}
