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
import org.bukkit.metadata.Metadatable;

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

	public static void putMetadata(Metadatable player, String key, Object value) {
		player.setMetadata(key, new FixedMetadataValue(Main.instance, value));
	}

	public static boolean hide(Player p) {
		if(getMetadata(p, "kitpvp.hidden", Boolean.class) != null) return false;
		putMetadata(p, "kitpvp.hidden", true);
        Bukkit.getOnlinePlayers().forEach(p2 -> p2.hidePlayer(p));
		return true;

	}

	public static boolean show(Player p) {
		if(getMetadata(p, "kitpvp.hidden", Boolean.class) == null) return false;
		p.removeMetadata("kitpvp.hidden", Main.instance);
		Bukkit.getOnlinePlayers().forEach(p2 -> p2.showPlayer(p));
		return false;
	}

	public static <T> T getMetadata(Metadatable player, String key, Class<T> target) {
		List<MetadataValue> metaList = player.getMetadata(key);

		for(MetadataValue v : metaList) {
			if(v.getOwningPlugin() == Main.instance && target.isAssignableFrom(v.value().getClass())) {
				return target.cast(v.value());
			}
		}

		return null;
	}

}
