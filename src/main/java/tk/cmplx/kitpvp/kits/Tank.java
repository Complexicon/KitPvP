package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.*;

public class Tank extends Kit {
	public Tank() {
		price = 500;
        permission = "kitpvp.tank";

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Diamantrüstung",
                "&c- Permanent Langsamkeit 2"
        };

        displayItem = new CItemStack(Material.DIAMOND_CHESTPLATE).setName("&bTank").build();

        helm = new CItemStack(Material.DIAMOND_HELMET).makeUnbreakable().build();
        chest = new CItemStack(Material.DIAMOND_CHESTPLATE).makeUnbreakable().build();
        legs = new CItemStack(Material.DIAMOND_LEGGINGS).makeUnbreakable().build();
        boots = new CItemStack(Material.DIAMOND_BOOTS).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();

        addEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
	}
}
