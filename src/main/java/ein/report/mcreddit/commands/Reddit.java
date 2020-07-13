package ein.report.mcreddit.commands;

import ein.report.mcreddit.MCReddit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reddit implements CommandExecutor {

    MCReddit plugin = MCReddit.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            if(args.length == 0)
            {
                p.sendMessage(plugin.getChatPrefix("reddit") + "Reddit posts");
            }
        }
        return false;
    }
}
