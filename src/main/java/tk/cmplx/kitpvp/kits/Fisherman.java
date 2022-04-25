package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.Kit;

public class Fisherman extends Kit{
	public Fisherman() {
        permission = "kitpvp.rod";
        price = 1250;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Angel",
                "&2- 2 Goldäpfel",
                "&2- Kettenhelm",
                "&2- Lederbrustplatte",
                "&2- Kettenhose",
                "&2- Kettenschuhe"
        };

        displayItem = new CItemStack(Material.FISHING_ROD).setName("&bAngler").build();

        helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().build();
        chest = new CItemStack(Material.LEATHER_CHESTPLATE).makeUnbreakable().build();
        legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().build();
        boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();
        hotbar[1] = new CItemStack(Material.FISHING_ROD).makeUnbreakable().build();
        hotbar[8] = new CItemStack(Material.GOLDEN_APPLE, 2).build();
	}
}
