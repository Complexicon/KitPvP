package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.Kit;

public class Swordsman extends Kit {

	public Swordsman() {
		lore = new String[] {
				"&6Dieses Kit beinhaltet:",
				"&2- Eisenrüstung",
				"&2- Diamantschwert",
				"&2- 2 Goldäpfel"
		};

		displayItem = new CItemStack(Material.DIAMOND_SWORD).setName("&9Schwertkämpfer").build();

		helm = new CItemStack(Material.IRON_HELMET).makeUnbreakable().build();
		chest = new CItemStack(Material.IRON_CHESTPLATE).makeUnbreakable().build();
		legs = new CItemStack(Material.IRON_LEGGINGS).makeUnbreakable().build();
		boots = new CItemStack(Material.IRON_BOOTS).makeUnbreakable().build();

		hotbar[0] = new CItemStack(Material.DIAMOND_SWORD).makeUnbreakable().build();
		hotbar[1] = new CItemStack(Material.GOLDEN_APPLE, 2).build();

	}

}
