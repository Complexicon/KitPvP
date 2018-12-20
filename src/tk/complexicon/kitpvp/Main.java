package tk.complexicon.kitpvp;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import tk.complexicon.kitpvp.event.*;

import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    public KitManager km = new KitManager();
    public HashMap<Player, Long> cooldown = new HashMap<>();
    public boolean economyHook;
    public boolean permissionHook;
    public boolean nteHook;
    public Team invisible;
    public Economy econ;
    public Permission perms;

    public Logger l = getLogger();

    ScoreboardManager manager;
    Scoreboard board;
    String teamName = "invisible";


    public void onEnable(){
        l.info("Loading Kits...");
        km.initKits();
        l.info("Registering Events...");
        Bukkit.getServer().getPluginManager().registerEvents(new Death(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitSelection(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SignInteraction(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitSpecific(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SmallEvents(this), this);

        l.info("Loading Plugin Hooks...");

        l.info("Trying to Hook Vault...");
        if(pluginExists("Vault")){
            l.info("Success!");
            economyHook = setupEconomy();
            l.info("Hooked Economy: " + economyHook);
            permissionHook = setupPermissions();
            l.info("Hooked Permissions: " + permissionHook);
        }else l.info("Failed!");

        l.info("Trying to Hook NametagEdit");
        if(pluginExists("NametagEdit")){
            nteHook = true;
            l.info("Success!");
        }else l.info("Failed!");


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

    private boolean pluginExists(String plName){
        return getServer().getPluginManager().getPlugin(plName) != null;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) return false;
        perms = rsp.getProvider();
        return perms != null;
    }

}