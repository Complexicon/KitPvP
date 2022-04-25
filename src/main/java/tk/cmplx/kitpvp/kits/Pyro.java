package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.*;

public class Pyro extends Kit {
	
	public Pyro() {
		lore = new String[] {
				"&6Dieses Kit beinhaltet:",
				"&2- Goldschwert Verbrennung 1",
				"&2- Bogen Flamme 1",
				"&2- 2 Goldäpfel",
				"&2- 32 Pfeile",
				"&2- Goldhelm",
				"&2- Goldbrustpanzer Schutz 2",
				"&2- Goldhose",
				"&2- Goldschuhe",
				"&5- Permanent Feuerschutz 1"
		};

		displayItem = new CItemStack(Material.FIREBALL).setName("&6Pyro").build();

		helm = new CItemStack(Material.GOLD_HELMET).makeUnbreakable().build();
		chest = new CItemStack(Material.GOLD_CHESTPLATE).makeUnbreakable()
				.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build();

		legs = new CItemStack(Material.GOLD_LEGGINGS).makeUnbreakable().build();
		boots = new CItemStack(Material.GOLD_BOOTS).makeUnbreakable().build();

		hotbar[0] = new CItemStack(Material.GOLD_SWORD).makeUnbreakable().addEnchantment(Enchantment.FIRE_ASPECT, 1)
				.setName("&6Flammenschwert").build();

		hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().addEnchantment(Enchantment.ARROW_FIRE, 1)
				.setName("&6Flammenbogen").build();

		hotbar[7] = new CItemStack(Material.GOLDEN_APPLE, 2).build();
		hotbar[8] = new CItemStack(Material.ARROW, 32).build();

		addEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
	}
}
