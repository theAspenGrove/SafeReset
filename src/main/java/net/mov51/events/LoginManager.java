package net.mov51.events;

import com.earth2me.essentials.User;
import net.mov51.SafeEnd;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

import static org.bukkit.Bukkit.getWorld;


public class LoginManager implements Listener {


    private final SafeEnd main;

    public LoginManager(SafeEnd main) {
        this.main = main;
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        User Ip = main.ess.getUser(p);
        String ResetTime = main.getConfig().getString("ResetTime");
        String ResetWorld = main.getConfig().getString("ResetWorld");

        if (ResetTime != null && ResetWorld != null) {
            System.out.println("Time not null");
            System.out.println("Config loaded");
            World World = getWorld(ResetWorld);
            if (World != null){
                System.out.println("World is not null" + World.toString());
                if (p.getWorld() == World) {
                    System.out.println("Player in world");
                    if (Ip.getLastLogout() < Long.parseLong(ResetTime)) {
                        System.out.println("Player outside of time");
                        Location TpTo = World.getSpawnLocation();
                        p.teleport(TpTo);
                    }
                }
            }else{
                System.out.println("World is null");
            }

        }
    }
}
