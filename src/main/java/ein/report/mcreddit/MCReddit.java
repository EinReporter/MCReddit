package ein.report.mcreddit;

import ein.report.mcreddit.commands.Reddit;
import ein.report.mcreddit.tools.ColorUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public final class MCReddit extends JavaPlugin {

    private static MCReddit instance;
    private ConsoleCommandSender console;

    private static final HashMap<String,String> chatPrefixes = new HashMap<String,String>();
    private static final String fallbackChatPrefix = ChatColor.DARK_GRAY + "[" + net.md_5.bungee.api.ChatColor.of(new Color(255, 87, 0)) + "Reddit" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;

    @Override
    public void onEnable() {
        instance = this;
        console = getServer().getConsoleSender();
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerCommands();
        registerEvents();
        loadChatPrefixes();
    }

    private void registerEvents() {
        //getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    private void registerCommands() {
        getCommand("Reddit").setExecutor(new Reddit());
    }

    public static MCReddit getInstance()
    {
        return instance;
    }

    public String getChatPrefix(String prefixname) {
        if(chatPrefixes.containsKey(prefixname))
        {
            if(!chatPrefixes.get(prefixname).isEmpty())
            {
                return chatPrefixes.get(prefixname);
            }
            return fallbackChatPrefix;
        }
        return fallbackChatPrefix;
    }

    public void loadChatPrefixes()
    {
        for(String prefixname : Objects.requireNonNull(getConfig().getConfigurationSection("chat.prefixes")).getKeys(false))
        {
            if(!Objects.requireNonNull(getConfig().getString("chat.prefixes." + prefixname)).isEmpty())
            {
                //String prefix = ChatColor.translateAlternateColorCodes('&',getConfig().getString("chat.prefixes." + prefixname));
                String prefix = ColorUtils.translateColorCodes(getConfig().getString("chat.prefixes." + prefixname));
                console.sendMessage(prefix);
                chatPrefixes.put(prefixname, prefix);
            }
            else
            {
                console.sendMessage("Missing prefix for: " + prefixname);
            }
        }
    }
}
