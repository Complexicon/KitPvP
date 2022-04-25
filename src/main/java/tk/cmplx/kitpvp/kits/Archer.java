package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Archer extends Kit {
	public Archer() {
		lore = new String[] {
				"&6Dieses Kit beinhaltet:",
				"&2- Holzschwert Schärfe 1",
				"&2- Bogen Stärke 1, Schlag 1, Unendlichkeit",
				"&2- Werfbarer Trank der Heilung",
				"&2- Lederkappe",
				"&2- Kettenbrustpanzer",
				"&2- Lederhose",
				"&2- Lederschuhe",
				"&5- Permanent Geschwindigkeit 1"
		};

		displayItem = new CItemStack(Material.BOW).setName("&2Archer").build();
		helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.GREEN).makeUnbreakable().build();
		chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
		legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.GREEN).makeUnbreakable().build();
		boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GREEN).makeUnbreakable().build();
		hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().build();
		hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable()
				.addEnchantment(Enchantment.ARROW_INFINITE, 1)
				.addEnchantment(Enchantment.ARROW_DAMAGE, 1)
				.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).build();
		hotbar[7] = new CPotion().addInstantEffect(true).splash().build();
		hotbar[8] = new CItemStack(Material.ARROW).setName("&6Legendärer Pfeil").build();
		addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

	}

}
