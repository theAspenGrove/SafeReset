package net.mov51.SafeReset;

import com.earth2me.essentials.Essentials;
import net.mov51.SafeReset.events.LoginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SafeReset extends JavaPlugin{

    public Essentials ess = (Essentials) getServer().getPluginManager().getPlugin("Essentials");
    public static Logger logger;

    public static String ResetTime;
    public static String ResetWorld;

    @Override
    public void onEnable() {
        //getLogger
        logger = this.getLogger();

        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new LoginManager(this), this);

        ResetTime = this.getConfig().getString("ResetTime");
        ResetWorld = this.getConfig().getString("ResetWorld");
        if (ResetTime != null && ResetWorld != null) {
            logger.info("THE WORLD ISSSSS SAVVVEEDDDD!");
            logger.info("or rather, you are saved from it...");
        }else{
            logger.warning("World is null - please correct the config!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown login
    }
}
