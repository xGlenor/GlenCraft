package pl.glencraft.core.Config;

import org.bukkit.configuration.file.FileConfiguration;
import pl.glencraft.core.Utils.TextUtil;

public enum Message {

    NO_PERMISSION("main.noPerm"),


    TEST("test.test");


    private static FileConfiguration config;
    private String path;


    Message(String path) {
        this.path = path;
    }

    public static void setConfiguration(FileConfiguration configuration) {
        config = configuration;
    }

    public static String getMessage(String path) {
        return TextUtil.color(config.getString(path));
    }

    @Override
    public String toString(){
        return TextUtil.color(config.getString(this.path));
    }
}
