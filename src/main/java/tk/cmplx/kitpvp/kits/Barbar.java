package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Barbar extends Kit {
	public Barbar() {
		
        permission = "kitpvp.barbar";

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinaxt",
                "&2- Regenerationstrank Level 2",
                "&2- Eisenhelm",
                "&2- Kettenbrust",
                "&2- Lederhose",
                "&2- Lederschuhe"
        };

        displayItem = new CItemStack(Material.STONE_AXE).setName("&eBarbar").build();
        helm = new CItemStack(Material.IRON_HELMET).makeUnbreakable().build();
        chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        legs = new CItemStack(Material.LEATHER_LEGGINGS).makeUnbreakable().build();
        boots = new CItemStack(Material.LEATHER_BOOTS).makeUnbreakable().build();
        hotbar[0] = new CItemStack(Material.STONE_AXE).makeUnbreakable().build();
        hotbar[1] = new CPotion().addPotionEffect(PotionEffectType.REGENERATION, 1, 20).setType(PotionType.REGEN).build();
	}
}
