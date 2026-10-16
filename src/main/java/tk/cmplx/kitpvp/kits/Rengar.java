package tk.cmplx.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Utils;

public class Rengar extends Kit {
	public Rengar() {
		price = 5000;
		permission = "kitpvp.rengar";
		lore = new String[] {
				"&6Dieses Kit beinhaltet:",
				"&2- Eisenschwert",
				"&2- Bogen mit Langsamkeit 2",
				"&4- Jagdfieber",
				"&2- 3 Pfeile",
				"&2- Eisenbrustpanzer",
				"&2- Lederhose",
				"&2- Lederschuhe federfall 5",
				"&5- Nach 3 Kills verbessert sich das Eisenschwert auf Schärfe 1"
		};

		displayItem = new CItemStack(Material.IRON_SWORD)
		.addEnchantment(Enchantment.DAMAGE_ALL, 1)
		.setName("&4Rengar")
		.addFlag(ItemFlag.HIDE_ENCHANTS)
		.build();

		chest = new CItemStack(Material.IRON_CHESTPLATE).makeUnbreakable().build();
		legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).makeUnbreakable().build();
		boots = new CLeatherArmor(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_FALL, 5)
				.makeUnbreakable().build();
		hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();
		hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().build();
		hotbar[2] = new CItemStack(Material.INK_SACK).setDurability(1).setName("&4Jagdfieber").setAmt(1).build();
		hotbar[8] = new CItemStack(Material.ARROW).setAmt(3).build();
	}

	PotionEffect[] Rengararrow = {
			new PotionEffect(PotionEffectType.SLOW, 40, 1),

	};

	@Override
	public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {
		if (event.getDamager() instanceof Arrow) {
			for (PotionEffect eff : Rengararrow) {
				receiver.addPotionEffect(eff);
			}
		}
	}

	PotionEffect[] Jagdfieber = {
			new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 0),
			new PotionEffect(PotionEffectType.SPEED, 120, 1),
			new PotionEffect(PotionEffectType.JUMP, 120, 3),
	};

	@Override
	public void onItemUse(PlayerInteractEvent event, Player p, ItemStack item) {
		if (item.getType() != Material.INK_SACK)
			return;

		if(Utils.consumeItem(item)) p.setItemInHand(AIR.clone());

		for (PotionEffect eff : Jagdfieber) {
			p.addPotionEffect(eff);
		}

		Bukkit.getOnlinePlayers().forEach(p2 -> {
			if(p2.getDisplayName() == p.getDisplayName()) return;
			p2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
		});

	}

	@Override
	public void onKill(Player p) {
		
		if(p.getLevel() == 3) {
			p.getInventory().getItem(p.getInventory().first(Material.IRON_SWORD)).addEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
		
		if(p.getLevel() % 3 == 0 && !p.getInventory().contains(Material.INK_SACK)) {
			p.getInventory().addItem(hotbar[2].clone());
		}
	}

}