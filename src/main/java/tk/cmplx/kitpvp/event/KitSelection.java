package tk.cmplx.kitpvp.event;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import tk.cmplx.kitpvp.KitManager;
import tk.cmplx.kitpvp.utils.EconomyHelper;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Log;
import tk.cmplx.kitpvp.utils.Utils;

public class KitSelection implements Listener {

	public KitSelection() {
		Log.info("Registered KitSelection Event!");
	}

	@EventHandler
	public void onKitSelect(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		ItemStack c = e.getCurrentItem();
		Inventory i = e.getInventory();

		if (c == null || c.getType() == Material.AIR)
			return;
		if (!i.getName().contains(ChatColor.LIGHT_PURPLE + "Kits"))
			return;

		e.setCancelled(true);

		Kit k = KitManager.kits[e.getRawSlot()];

		if (p.hasPermission(k.permission)) {

			if (!p.hasPermission("kitpvp.bypasscooldown")) {
				Long lastSelected = Utils.getMetadata(p, "kitpvp.lastSelected", Long.class);
				long cooldown = System.currentTimeMillis() - (lastSelected == null ? 0 : lastSelected);
				if (cooldown < 30000) {
					int wait = (int) ((30000 - cooldown) / 1000);
					Utils.msg(p, "&cDu musst noch " + wait + " Sekunden Warten!");
					return;
				}
			}

			Utils.msg(p, "&aErfolgreich das Kit " + c.getItemMeta().getDisplayName() + " &a Ausgewählt!");
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 3, 1);
			p.closeInventory();

			k.applyKit(p);
			Utils.putMetadata(p, "kitpvp.lastSelected", System.currentTimeMillis());
			Utils.show(p);

		} else {
			if (e.getClick() == ClickType.RIGHT && EconomyHelper.economy() && EconomyHelper.permissions())
				EconomyHelper.buyKit(p, k);
			else
				Utils.msg(p, "&cKeine Berechtigung auf das Kit: " + c.getItemMeta().getDisplayName());
		}

	}

}
