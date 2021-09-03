package pl.glencraft.core.Listeners;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;
import pl.glencraft.core.GlenCore;
import pl.glencraft.core.Config.ConfigType;

public class NPCListener implements Listener {

    private GlenCore plugin;
    private FileConfiguration npcConfig;
    private BukkitScheduler scheduler;

    public NPCListener(){
        this.plugin = GlenCore.getInstance();
        this.npcConfig = this.plugin.getConfigManager().getFile(ConfigType.NPC).getConfig();
        this.scheduler = this.plugin.getServer().getScheduler();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRightClick(NPCRightClickEvent event){
        if(npcConfig.contains("npc."+event.getNPC().getId())){
            System.out.println("SIEMA 1");
            npcConfig.getStringList("npc."+event.getNPC().getId()).forEach(action -> {
                if(action.contains("RIGHT")){
                    perform(event.getNPC(), event.getClicker(), action);
                }
            });

        }

    }

    private void perform(NPC npc, Player player, String action){
        String[] args = action.split(";");

        switch (args[1]){
            case "CMD":
                if(args[2].equalsIgnoreCase("PLAYER")){
                    this.scheduler.runTaskLater(this.plugin, () -> player.performCommand(args[3]), 20);
                }else{
                    this.scheduler.runTaskLater(this.plugin, () -> this.plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), args[3]), 20);
                }
                break;
            case "MESSAGE":
                System.out.println(args[3]);
                break;
            default:
                break;
        }

    }

}
