package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Canadian extends Kit {
	public Canadian() {
        permission = "kitpvp.canada";
        price = 3000;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzfäleraxt",
                "&2- Rot Karriertes Hemd",
                "&2- Blaue Hose",
                "&2- Schwarze Arbeitsstiefel",
                "&2- Kanadischer Sirup"
        };

        displayItem = new CItemStack(Material.IRON_AXE).setName("&cDer Kanadier").build();

        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLUE).makeUnbreakable().build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.IRON_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 1).makeUnbreakable().build();
        hotbar[1] = new CPotion().addInstantEffect(true).setName("&6Sirup").build();
	}
}
