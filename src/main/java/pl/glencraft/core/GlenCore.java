package pl.glencraft.core;

import org.bukkit.plugin.java.JavaPlugin;
import pl.glencraft.core.Commands.CommandManager;
import pl.glencraft.core.Listeners.NPCListener;
import pl.glencraft.core.Config.ConfigManager;

import java.util.logging.Level;

public final class GlenCore extends JavaPlugin {

    public static GlenCore instance;
    private ConfigManager configManager;
    private CommandManager commandManager;


    public static GlenCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;



        checkDepends();
        this.configManager = new ConfigManager();
        this.configManager.loadConfigs();
        getServer().getPluginManager().registerEvents(new NPCListener(), this);

        this.commandManager = new CommandManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    private void checkDepends(){
        if(getServer().getPluginManager().getPlugin("Citizens") == null || !getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
            getLogger().log(Level.SEVERE, "Serwer ma problem z znalezieniem lub zaladowaniem wtyczyki Citizens");
            getServer().getPluginManager().disablePlugin(this);
        }

    }

}
