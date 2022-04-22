package pl.sagin.downloader;

import org.bukkit.plugin.java.JavaPlugin;
import pl.sagin.downloader.commands.PobierzCommand;

public final class Downloader extends JavaPlugin {

    Downloader plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("pobierz").setExecutor(new PobierzCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
