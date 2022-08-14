package de.lupu.smapi;

import de.lupu.smapi.mysql.MySQL;
import de.lupu.smapi.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MysqlAPI extends JavaPlugin {

    private static MysqlAPI plugin;

    @Override
    public void onEnable() {

        plugin = this;

        sendConsoleMessage(Strings.prefix + "API loaded successfully.");

    }

    @Override
    public void onDisable() {

    }

    public static MysqlAPI getPlugin(){
        return plugin;
    }

    public void sendConsoleMessage(String msg){
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    public static MySQL createNewAPI(String host, String port, String database, String username, String password){
        MySQL mysql = new MySQL(host, port, database, username, password);
        return mysql;
    }

}
