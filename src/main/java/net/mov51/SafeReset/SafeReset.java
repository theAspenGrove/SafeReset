package net.mov51.SafeReset;

import com.earth2me.essentials.Essentials;
import net.mov51.SafeReset.events.LoginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SafeReset extends JavaPlugin{

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
