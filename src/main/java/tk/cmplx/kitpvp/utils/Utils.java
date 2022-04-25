package tk.cmplx.kitpvp.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import tk.cmplx.kitpvp.Main;

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

	public static boolean consumeItem(ItemStack i) {
		if (i.getAmount() == 1) return true;
		else i.setAmount(i.getAmount() - 1);
		return false;
	}

	public static void putMetadata(Player player, String key, Object value) {
		player.setMetadata(key, new FixedMetadataValue(Main.instance, value));
	}

	public static <T> T getMetadata(Player player, String key, Class<T> target) {
		List<MetadataValue> metaList = player.getMetadata(key);

		for(MetadataValue v : metaList) {
			if(v.getOwningPlugin() == Main.instance && target.isAssignableFrom(v.value().getClass())) {
				return target.cast(v.value());
			}
		}

		return null;
	}

}
