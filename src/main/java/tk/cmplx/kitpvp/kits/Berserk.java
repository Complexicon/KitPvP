package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;

public class Berserk extends Kit {
	public Berserk() {
		permission = "kitpvp.berserk";
		price = 5500;

		lore = new String[] {
				"&6Dieses Kit beinhaltet:",
				"&2- Eisenschwert",
				"&2- Lederjacke",
				"&5- Permanent Stärke 1"
		};

		displayItem = new CItemStack(Material.IRON_SWORD).setName("&4Berserker").build();
		chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().build();

		hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();

		addEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
	}
}
