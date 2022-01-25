package net.mov51.safeReset.events;

import com.earth2me.essentials.User;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.mov51.safeReset.SafeReset;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static net.mov51.safeReset.SafeReset.*;
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

        World World = getWorld(ResetWorld);
        if (World != null){
//            System.out.println("World is not null" + World.toString());
            if (p.getWorld() == World) {
//                System.out.println("Player in world");
                if (Ip.getLastLogout() < Long.parseLong(ResetTime)) {
//                    System.out.println("Player outside of time");
                    Location PLoc = p.getLocation();
                    Location TpTo = World.getSpawnLocation();
                    logger.info("Player " + p.getName() + " has been 'saved' from " + PLoc.getBlockX() + ", " + PLoc.getBlockY() + ", " + PLoc.getBlockZ());
                    p.teleport(TpTo);
                    String prefix = "Safe-Reset ";
                    p.sendMessage(Component.text().append(Component.text(prefix).color(TextColor.fromHexString("#7304D1"))).append(Component.text(" You've been teleported to a safe location by Staff because this world was reset since you last entered it!")));
                }
            }
        }
    }
}
