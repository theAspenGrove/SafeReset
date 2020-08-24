package net.mov51.events;

import com.earth2me.essentials.User;
import net.mov51.SafeEnd;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


public class LoginManager implements Listener {


    private final SafeEnd main;

    public LoginManager(SafeEnd main) {
        this.main = main;
    }
    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        User Ip = main.ess.getUser(p);
        String ResetTime = main.getConfig().getString("ResetTime");
        assert ResetTime != null;
        System.out.println(ResetTime);
        System.out.println(Ip.getLastLogout());
        System.out.println(Long.parseLong(ResetTime));
        System.out.println("dif=" + (Ip.getLastLogout() < Long.parseLong(ResetTime)));

        if(Ip.getLastLogout() < Long.parseLong(ResetTime)){
            System.out.println("HELP");
        }
    }
}
