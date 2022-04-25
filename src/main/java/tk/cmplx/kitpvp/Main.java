package tk.cmplx.kitpvp;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import tk.cmplx.kitpvp.event.Death;
import tk.cmplx.kitpvp.event.InvisibilityBugFix;
import tk.cmplx.kitpvp.event.KitSelection;
import tk.cmplx.kitpvp.event.KitSpecific;
import tk.cmplx.kitpvp.event.SignInteraction;
import tk.cmplx.kitpvp.event.SmallEvents;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Log;

public class Main extends JavaPlugin implements Listener {

    public KitManager km = new KitManager();
    public static boolean economyHook;
    public static boolean permissionHook;
    public static boolean nteHook;
    public Economy econ;
    public Permission perms;

	public static Main instance;
	
	InvisibilityBugFix fix;

    public void onEnable(){
		instance = this;
		Log.initialize();

        Log.info("Loading Kits...");

		for(Kit k : KitManager.kits)
			Log.info("Registered Kit " + k.getClass().getSimpleName());

		Log.info("Total: " + KitManager.kits.length + " Kits Registered!");
		Log.info("Registering Events...");

        Bukkit.getServer().getPluginManager().registerEvents(new Death(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitSelection(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SignInteraction(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KitSpecific(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SmallEvents(), this);
		if(pluginExists("NametagEdit")) {
			fix = new InvisibilityBugFix();
			Bukkit.getServer().getPluginManager().registerEvents(fix, this);
		}

        Log.info("Loading Plugin Hooks...");

        Log.info("Trying to Hook Vault...");

        if(pluginExists("Vault")){

            Log.info("Success!");

            economyHook = setupEconomy();
            Log.info("Hooked Economy: " + economyHook);

            permissionHook = setupPermissions();
            Log.info("Hooked Permissions: " + permissionHook);
			
        } else Log.info("Failed!");

        Log.info("Enabled KitPvP");
    }

	@Override
	public void onDisable() {
		if(fix != null) fix.unload();
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