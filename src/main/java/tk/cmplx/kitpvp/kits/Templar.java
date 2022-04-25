package tk.cmplx.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Templar extends Kit{
	public Templar() {

        permission = "kitpvp.templar";
        price = 3750;

       	lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Eisenbrustplatte Schutz 1",
                "&2- 2 Werfbare Heiltränke",
                "&5- 14 Goldene Extraherzen"
        };

        displayItem = new CItemStack(Material.GOLDEN_APPLE).setName("&6Templer").build();

        chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();
        hotbar[8] = new CPotion().addInstantEffect(true).splash().setAmt(2).build();

        addEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 6));
	}
}
