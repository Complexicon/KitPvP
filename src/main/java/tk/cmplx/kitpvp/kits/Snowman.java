package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;

public class Snowman extends Kit {
	public Snowman() {
		permission = "kitpvp.snowman";
		price = 5000;

		lore = new String[] {
				"&6Dieses Kit beinhaltet:",
				"&2- Steinschwert Schärfe 1",
				"&2- 64 Schneebälle",
				"&2- Komplette Lederrüstung",
				"&5- Schneebälle Verlangsamen den Getroffenen",
				"&5- Schneebälle Heilen beim Treffen Für Extrem Kurze Zeit",
				"&5- Permanent Geschwindigkeit 1"

		};

		displayItem = new CItemStack(Material.SNOW_BALL).setName("&7Schneemann").build();
		helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.WHITE).makeUnbreakable().build();
		chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
		legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE).makeUnbreakable().build();
		boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.WHITE).makeUnbreakable().build();

		hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().addEnchantment(Enchantment.DAMAGE_ALL, 1).build();
		hotbar[1] = new CItemStack(Material.SNOW_BALL, 64).build();

		addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}

	@Override
	public void onDamageDeal(EntityDamageByEntityEvent e, Player dealer, Player receiver) {
		if (e.getDamager() instanceof Snowball) {
			dealer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
			receiver.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
		}
	}

}
