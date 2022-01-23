package net.mov51.SafeReset.events;

import com.earth2me.essentials.User;
import net.mov51.SafeReset.SafeReset;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static net.mov51.SafeReset.SafeReset.logger;
import static org.bukkit.Bukkit.getWorld;

public class LoginManager implements Listener {


    private final SafeReset main;

    public LoginManager(SafeReset main) {
        this.main = main;
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        User Ip = main.ess.getUser(p);
        String ResetTime = main.getConfig().getString("ResetTime");
        String ResetWorld = main.getConfig().getString("ResetWorld");

        if (ResetTime != null && ResetWorld != null) {
            //System.out.println("Time not null");
            //System.out.println("Config loaded");
            World World = getWorld(ResetWorld);
            if (World != null){
                //System.out.println("World is not null" + World.toString());
                if (p.getWorld() == World) {
                    //System.out.println("Player in world");
                    if (Ip.getLastLogout() < Long.parseLong(ResetTime)) {
                        //System.out.println("Player outside of time");
                        Location PLoc = p.getLocation();
                        Location TpTo = World.getSpawnLocation();
                        logger.info("Player " + p.getName() + " has been 'saved' from " + PLoc.getBlockX() + ", " + PLoc.getBlockY() + ", " + PLoc.getBlockZ());
                        p.teleport(TpTo);
                    }
                }
            }else{
                logger.warning("World is null - please correct the config!");
            }

        }
    }
}
