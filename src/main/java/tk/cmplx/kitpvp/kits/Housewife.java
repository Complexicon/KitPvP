package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Housewife extends Kit {
	public Housewife() {
        permission = "kitpvp.housewife";
        price = 2150;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Versace Highheels",
                "&2- Gucci-Top",
                "&2- Kochlöffel der Verdammnis",
                "&2- 2 Himbeersäfte",
                "&2- Karamellapfel"
        };

        displayItem = new CItemStack(Material.CAKE).setName("&bDie Hausfrau").build();

        boots = new CItemStack(Material.DIAMOND_BOOTS)
                .setName("&9Versace Highheels")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.THORNS, 2)
                .makeUnbreakable()
                .build();

        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.FUCHSIA).makeUnbreakable().setName("&dGucci-Top").build();

        hotbar[0] = new CItemStack(Material.WOOD_SPADE)
                .makeUnbreakable()
                .setName("&cKochlöffel der Verdammnis")
                .addEnchantment(Enchantment.DAMAGE_ALL, 4)
                .addEnchantment(Enchantment.KNOCKBACK, 4)
                .build();

        hotbar[1] = new CPotion().addInstantEffect(true, 1).setName("&dHimbeersaft").build();
        hotbar[2] = new CItemStack(Material.GOLDEN_APPLE).makeUnbreakable().setName("&6Karamellapfel").build();
	}
}
