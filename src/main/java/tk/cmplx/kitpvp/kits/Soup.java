package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.Kit;

public class Soup extends Kit {
	public Soup() {
        permission = "kitpvp.soup";
        price = 3500;

       	lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Kettenrüstung",
                "&2- 8 Zaubersuppen",
                "&5- Heilung beim nutzen der Suppen"
        };

        displayItem = new CItemStack(Material.MUSHROOM_SOUP).setName("&eSoup").build();

        helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().build();
        chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().build();
        boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        multiStack(new CItemStack(Material.MUSHROOM_SOUP).setName("&eZaubersuppe").build(), 8);
	}

	@Override
	public void onItemUse(PlayerInteractEvent e, Player p, ItemStack item) {
		if (item.getType().equals(Material.MUSHROOM_SOUP) && p.getHealth() + 5 <= p.getMaxHealth()) {
			p.setHealth(p.getHealth() + 5);
			p.getItemInHand().setType(Material.BOWL);
		}
	}

}
