package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;

public class Ender extends Kit{
	public Ender() {

        permission = "kitpvp.ender";
        buyable = false;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Lederhelm",
                "&2- Eisenbrustpanzer Schussicher 1",
                "&2- 8 Enderperlen",
                "&2- Lederhose",
                "&2- Eisenschuhe Federfall 10",
                "&5- Heilung beim Treffen der Enderperle für Kurze Zeit",
                "&5- Permanent Geschwindigkeit 1"
        };

        displayItem = new CItemStack(Material.ENDER_PEARL).setName("&3Ender").build();

        helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.BLACK).makeUnbreakable().build();
        chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLACK).makeUnbreakable().build();
        boots = new CItemStack(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_FALL, 10).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        hotbar[1] = new CItemStack(Material.ENDER_PEARL, 16).build();
        hotbar[8] = new CItemStack(Material.GOLDEN_APPLE, 2).build();

        addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}

	@Override
	public void onProjectileHit(ProjectileHitEvent e, Player p) {
		if(e.getEntityType() == EntityType.ENDER_PEARL)
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 2));
	}

}
