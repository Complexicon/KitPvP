package tk.cmplx.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

public class Utils {

    public static void bcast(String s){
        Bukkit.broadcastMessage(tr(s));
    }

    public static void msg(CommandSender c, String msg){
        c.sendMessage(tr(msg));
    }

    public static String tr(String s){
        return s.replaceAll("&", "§");
    }

    public static boolean isNotCreative(HumanEntity p){
        return p.getGameMode() != GameMode.CREATIVE;
    }

}
