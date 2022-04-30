package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Jesus extends Kit {
	public Jesus() {
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

		price = 2000;
		permission = "kitpvp.jesus";

		displayItem = new CItemStack(Material.BREAD).setName("Jesus").build();

		helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
		chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
		legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
		boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();

		hotbar[0] = new CItemStack(Material.BREAD).setName("&eLeib Christi vom Vortag").addEnchantment(Enchantment.DAMAGE_ALL, 8).build();
		hotbar[1] = new CItemStack(Material.BOOK).setName("&6Bibel").addEnchantment(Enchantment.FIRE_ASPECT, 2).build();
		hotbar[2] = new CPotion().setType(PotionType.SPEED).addPotionEffect(PotionEffectType.WITHER, 1, 3).splash().setName("&7Weihwasser").build();

		addEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 0));
	}
}
