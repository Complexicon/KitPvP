package tk.cmplx.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import tk.cmplx.kitpvp.event.Death;
import tk.cmplx.kitpvp.event.KitSelection;
import tk.cmplx.kitpvp.event.KitSpecific;
import tk.cmplx.kitpvp.event.SignInteraction;
import tk.cmplx.kitpvp.event.SmallEvents;
import tk.cmplx.kitpvp.utils.EconomyHelper;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Log;

public class Main extends JavaPlugin implements Listener {

    public KitManager km = new KitManager();

	public static Main instance;

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

        Log.info("Loading Plugin Hooks...");

        Log.info("Trying to Hook Vault...");

        if(pluginExists("Vault")){

            EconomyHelper.init();
            Log.info("Success!");
			
        } else Log.info("Failed!");

        Log.info("Enabled KitPvP");
    }

    private boolean pluginExists(String plName){
        return getServer().getPluginManager().getPlugin(plName) != null;
    }

}