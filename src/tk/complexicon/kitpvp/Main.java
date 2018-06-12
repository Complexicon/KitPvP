package tk.complexicon.kitpvp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public KitManager km = new KitManager();
    public boolean economyHook;

    public void onEnable(){
        km.initKits();
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
        economyHook = setupEconomy();
        System.out.println("Enabled KitPvP");
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

}