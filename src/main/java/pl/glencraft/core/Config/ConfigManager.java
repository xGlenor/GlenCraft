package pl.glencraft.core.Config;

import pl.glencraft.core.GlenCore;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private Map<ConfigType, ConfigHandler> configurations;
    private GlenCore plugin;

    public ConfigManager(){
        this.plugin = GlenCore.getInstance();
        this.configurations = new HashMap<>();
    }

    public void loadConfigs(){
        configurations.put(ConfigType.CONFIG, new ConfigHandler(this.plugin, "config"));
        configurations.put(ConfigType.MESSAGE, new ConfigHandler(this.plugin, "message"));
        configurations.put(ConfigType.NPC, new ConfigHandler(this.plugin, "depends/npc"));

        configurations.values().forEach(ConfigHandler::saveDefaultConfig);

        Message.setConfiguration(getFile(ConfigType.MESSAGE).getConfig());

    }

    public ConfigHandler getFile(ConfigType type) {
        return configurations.get(type);
    }

    public void reloadFiles(){
        configurations.values().forEach(ConfigHandler::reload);
        Message.setConfiguration(getFile(ConfigType.MESSAGE).getConfig());
    }

    public void saveFiles(ConfigType type) {
        configurations.get(type).save();
    }


}
