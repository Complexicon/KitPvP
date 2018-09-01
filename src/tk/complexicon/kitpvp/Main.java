package tk.complexicon.kitpvp;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    public KitManager km = new KitManager();
    public boolean economyHook;
    public boolean permissionHook;
    public boolean nteHook;

    Logger l = getLogger();

    ScoreboardManager manager;
    Scoreboard board;
    Team invisible;
    String teamName = "invisible";


    public void onEnable(){
        km.initKits();
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
        economyHook = setupEconomy();
        permissionHook = setupPermissions();
        nteHook = testNTE();

        manager = Bukkit.getScoreboardManager();
        board = manager.getMainScoreboard();

        if(board.getTeam(teamName) != null){
            board.getTeam(teamName).unregister();
        }

        invisible = board.registerNewTeam(teamName);
        invisible.setNameTagVisibility(NameTagVisibility.NEVER);
        l.info("Enabled KitPvP");
    }

    public void onDisable(){
        invisible.unregister();
    }

    private boolean testNTE() {
        if (getServer().getPluginManager().getPlugin("NametagEdit") == null) {
            return false;
        }
        return true;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        Economy econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        Permission perms = rsp.getProvider();
        return perms != null;
    }

}