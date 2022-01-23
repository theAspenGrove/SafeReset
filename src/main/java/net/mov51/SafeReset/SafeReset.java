package net.mov51;

import com.earth2me.essentials.Essentials;
import net.mov51.events.LoginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SafeEnd extends JavaPlugin{

    public Essentials ess = (Essentials) getServer().getPluginManager().getPlugin("Essentials");

    public static Logger logger;

    @Override
    public void onEnable() {
        //getLogger
        logger = this.getLogger();

        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new LoginManager(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown login
    }
}
