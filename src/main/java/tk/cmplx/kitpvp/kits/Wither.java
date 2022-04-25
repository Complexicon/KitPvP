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

public class Wither extends Kit {
	public Wither() {
        price = 4000;
        permission = "kitpvp.wither";

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Schwert des Withers",
                "&2- 2x Trank der Blindheit",
                "&5- Permanent Nachtsicht"
        };

        displayItem = new CItemStack(Material.SKULL_ITEM).setDurability(1).setName("&8Wither").build();

        hotbar[0] = new CItemStack(Material.STONE_SWORD).setName("&8Wither-Schwert").makeUnbreakable().addEnchantment(Enchantment.DAMAGE_ALL, 2).build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLACK).makeUnbreakable().build();
        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.BLACK).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).makeUnbreakable().build();
        helm = new CItemStack(Material.SKULL_ITEM).setDurability(1).makeUnbreakable().build();

        hotbar[1] = new CPotion().setType(PotionType.STRENGTH)
                .addPotionEffect(PotionEffectType.BLINDNESS, 2).splash()
                .setName("&4Trank der Blindheit").setAmt(2).build();

        addEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2));
	}

	@Override
	public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {
		if(dealer.getItemInHand().getType() == Material.STONE_SWORD)
			receiver.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
	}

}
