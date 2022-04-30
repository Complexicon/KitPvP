package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Hunter extends Kit {
    public Hunter() {
        lore = new String[] {
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Bogen",
                "&2- Werfbarer Trank der Heilung",
                "&2- Lederkappe",
                "&2- LederPunika",
                "&2- Lederhose",
                "&2- Lederschuhe",
                "&2- 6 Pfeile",
                "&5- Permanent Geschwindigkeit 1"
        };
        buyable = true;
        price = 2600;
        displayItem = new CItemStack(Material.SKULL_ITEM).setName("&cHunter").build();
        helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.BLACK).makeUnbreakable().build();
        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.GREEN)
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLACK).makeUnbreakable().build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();
        hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().build();
        hotbar[7] = new CPotion().addInstantEffect(true).splash().build();
        hotbar[8] = new CItemStack(Material.ARROW).setAmt(6).build();
        addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }

    PotionEffect[] hunterArrow = {
            new PotionEffect(PotionEffectType.SLOW, 40, 0),
            new PotionEffect(PotionEffectType.POISON, 60, 0)
    };

    @Override
    public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {
        if (event.getDamager() instanceof Arrow) {
            for (PotionEffect eff : hunterArrow) {
                receiver.addPotionEffect(eff);
            }
        } else if(dealer.getItemInHand().getType() == Material.STONE_SWORD) {
			receiver.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
		}
    }
}