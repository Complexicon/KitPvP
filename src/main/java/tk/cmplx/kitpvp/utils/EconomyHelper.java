package tk.cmplx.kitpvp.utils;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import tk.cmplx.kitpvp.Main;

public class EconomyHelper {

    private static Economy econ;
    private static Permission perms;
    private static boolean ecoOK = false;
    private static boolean permOK = false;
    private static boolean initOK = false;

    public static void init() {
        if (initOK)
            return;

        RegisteredServiceProvider<Economy> ecoProv = Main.instance.getServer().getServicesManager()
                .getRegistration(Economy.class);

        if (ecoProv != null) {
            econ = ecoProv.getProvider();
            ecoOK = econ != null;
            Log.info("Hooked Economy: " + (ecoOK ? econ.getName() : "No"));
        }

        RegisteredServiceProvider<Permission> permProv = Main.instance.getServer().getServicesManager()
                .getRegistration(Permission.class);
        if (permProv != null) {
            perms = permProv.getProvider();
            permOK = perms != null;
            Log.info("Hooked Permissions: " + (permOK ? perms.getName() : "No"));
        }

        initOK = true;

    }

    public static boolean economy() {
        return ecoOK;
    }

    public static boolean permissions() {
        return permOK;
    }

    public static void buyKit(Player p, Kit k) {
        if (!k.buyable) {
            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
            Utils.msg(p, "&cDu kannst diese Kit nicht Kaufen!");
            return;
        }

        if (econ.getBalance(p) < k.price) {
            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
            Utils.msg(p, "&cDu hast nicht genug Geld für das Kit: " + k.displayItem.getItemMeta().getDisplayName());
            return;
        }

        econ.withdrawPlayer(p, k.price);
        perms.playerAdd(null, p, k.permission);

        Utils.msg(p, "&aDu hast erfolgreich das Kit: " + k.displayItem.getItemMeta().getDisplayName()
                + "&a gekauft. Vielen Dank!");

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0.8F);
        p.closeInventory();
    }

    public static void giveKillReward(Player p) {
        giveKillReward(p, 0, "");
    }

    public static void giveKillReward(Player p, int bonus, String reason) {

        if (bonus > 0) {
            econ.depositPlayer(p, bonus);
            Utils.msg(p, "&aDu erhälst einen " + reason + "-Bonus von: " + bonus + "©!");
        }

        Random r = new Random();
        int coins = 4 + r.nextInt(6);

        if (p.hasPermission("kitpvp.coinboost.4x")) {
            coins += 15 + r.nextInt(15);
            Utils.msg(p, "&aDu hast: &6+" + coins + "© &aErhalten! &d(4x Coinboost Aktiv!)");
        } else if (p.hasPermission("kitpvp.coinboost.2x")) {
            coins += 5 + r.nextInt(5);
            Utils.msg(p, "&aDu hast: &6+" + coins + "© &aErhalten! &d(2x Coinboost Aktiv!)");
        } else {
            Utils.msg(p, "&aDu hast: &6+" + coins + "© &aErhalten!");
        }

        econ.depositPlayer(p, coins);

    }

}
