package tk.cmplx.kitpvp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import tk.cmplx.kitpvp.kits.*;
import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.Kit;

public class KitManager {

	public static final Kit[] kits = {
			new Swordsman(),
			new Barbar(),
			new Archer(),
			new Pyro(),
			new Tank(),

			new Snowman(),
			new Fisherman(),
			new Witch(),
			new Rabbit(),
			new Canadian(),
			new Housewife(),
			new Berserk(),
			new Soup(),
			new Templar(),
			new Mage(),
			new Supporter(),
			new Onehit(),
			new Skeleton(),
			new Vampire(),
			new Wither(),
			new Drugaddict(),

			new Ghost(),
			new Ender(),

			new Jesus(),
			new Hunter(),
			new Dragonknight(),

			new David(),
	};

	public static void openKitSelection(Player p) {
		Inventory dummy = Bukkit.createInventory(null, 9 * (int) Math.ceil(KitManager.kits.length / 9.0), ChatColor.LIGHT_PURPLE + "Kits");

		int slot = 0;
		for (Kit k : KitManager.kits) {

			CItemStack displayItem = new CItemStack(k.displayItem.clone());
			List<String> lore = new ArrayList<>(Arrays.asList(k.lore));

			if (!p.hasPermission(k.permission)) {
				if (Main.economyHook && Main.permissionHook) {
					if (k.buyable) {
						lore.add(0, ChatColor.RED + "Nicht Gekauft!");
						lore.add(0, ChatColor.GOLD + "Preis: " + k.price);
						lore.add(0, ChatColor.AQUA + "Kaufe mit Rechtsklick!");
					} else if (k.dropExclusive) {
						lore.add(0, ChatColor.RED + "Nicht Kaufbar!");
						lore.add(0, ChatColor.AQUA + "Nur als Drop Erhältlich!");
					} else {
						lore.add(0, ChatColor.RED + "Nicht Kaufbar!");
						lore.add(0, ChatColor.LIGHT_PURPLE + "Exklusiv Für VIP!");
					}
				}
			} else
				lore.add(0, ChatColor.GREEN + "Bereits Gekauft!");

			dummy.setItem(slot, displayItem.addLore(lore.toArray(new String[0])).build());
			slot++;
		}
		p.openInventory(dummy);
	}

}
