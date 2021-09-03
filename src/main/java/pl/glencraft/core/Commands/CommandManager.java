package pl.glencraft.core.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.glencraft.core.GlenCore;
import pl.glencraft.core.Utils.TextUtil;

public class CommandManager implements CommandExecutor {

    private GlenCore plugin;
    private String COMMAND = "glencore";

    public CommandManager(){
        this.plugin = GlenCore.getInstance();
        this.plugin.getCommand(COMMAND).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args[0].equalsIgnoreCase("reload")){
            this.plugin.getConfigManager().reloadFiles();
            sender.sendMessage(TextUtil.color("Wtyczka została przeładowana"));
            return true;
        }



        return false;
    }
}
