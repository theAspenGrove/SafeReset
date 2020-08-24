package net.mov51;

import com.earth2me.essentials.Essentials;
import net.mov51.events.LoginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeEnd extends JavaPlugin{

    public Essentials ess = (Essentials) getServer().getPluginManager().getPlugin("Essentials");


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new LoginManager(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown login
    }
}
