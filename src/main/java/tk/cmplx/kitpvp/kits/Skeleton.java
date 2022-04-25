package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;

public class Skeleton extends Kit {
	public Skeleton() {
        price = 3000;
        permission = "kitpvp.skeleton";

        maxHealth = 10;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Knochenkeule",
                "&2- Knochenweiße Lederrüstung",
                "&5- Permanent Resistenz und Schnelligkeit",
                "&c- Permanent 5 Herzen"
        };

        displayItem = new CItemStack(Material.BONE).setName("&7Skelett").build();

        hotbar[0] = new CItemStack(Material.BONE).setName("&7Knochenkeule").addEnchantment(Enchantment.DAMAGE_ALL, 5).build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.WHITE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .addEnchantment(Enchantment.DEPTH_STRIDER, 1)
                .makeUnbreakable().build();
        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.WHITE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).makeUnbreakable().build();
        helm = new CItemStack(Material.SKULL_ITEM).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).makeUnbreakable().build();

        addEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 2));
        addEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
        addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}
}
