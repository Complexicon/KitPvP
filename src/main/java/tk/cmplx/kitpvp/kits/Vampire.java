package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Vampire extends Kit {
	public Vampire() {
        price = 5000;
        permission = "kitpvp.vampire";

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Stab des Lebensentzugs",
                "&2- Umhang des Vampirs",
                "&2- 2x Frischgebraute Blutige Mara",
                "&5- Permanent Nachtsicht"
        };

        displayItem = new CItemStack(Material.STICK).setName("&cVampir").build();

        hotbar[0] = new CItemStack(Material.STICK).setName("&cStab des Lebensentzugs").addEnchantment(Enchantment.DAMAGE_ALL, 1).build();

        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.PURPLE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).makeUnbreakable().build();

        hotbar[8] = new CPotion().setType(PotionType.STRENGTH)
                .addPotionEffect(PotionEffectType.REGENERATION, 30)
                .addPotionEffect(PotionEffectType.BLINDNESS, 2)
                .setName("&4Blutige Mara").setAmt(2).build();

        addEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2));
	}

	@Override
	public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {
		if(dealer.getItemInHand().getType() != Material.STICK) return;

		int healingFactor = 3;

		if(dealer.getHealth() + healingFactor > dealer.getMaxHealth())
			dealer.setHealth(dealer.getMaxHealth());
		else
			dealer.setHealth(dealer.getHealth() + healingFactor);

		if (!(receiver.getHealth() - (healingFactor + 1) < 0))
			receiver.setHealth(receiver.getHealth() - (healingFactor + 1));
	}

}
