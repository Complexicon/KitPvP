package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;

public class Onehit extends Kit {
	public Onehit() {
        price = 15000;
        permission = "kitpvp.onehit";

        maxHealth = 2;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Töten oder Getötet Werden ;)"
        };

        displayItem = new CItemStack(Material.SPECKLED_MELON).setName("&4Onehit").build();

        hotbar[0] = new CItemStack(Material.GOLD_SWORD).makeUnbreakable().setName("&6Dämmerbrecher").addEnchantment(Enchantment.DAMAGE_ALL, 10).build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.RED).addEnchantment(Enchantment.PROTECTION_FALL, 10).makeUnbreakable().build();

        addEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 100));
	}
}
