package tk.cmplx.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Utils;

public class Shadow extends Kit {
    public Shadow() {
        buyable = true;
        price = 5000;
        permission = "kitpvp.shadow";

        maxHealth = 16;
        lore = new String[] {
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Bogen",
                "&2- Lederkappe",
                "&2- Lederbrustplatte",
                "&2- Lederhose",
                "&2- Lederschuhe",
                "&2- 2 Enderperlen",
                "&2- 2 Pfeile",
                "&0- Schattenmantel",
                "&c- Permanent nur 8 Herzen",
        };

        displayItem = new CItemStack(Material.INK_SACK).setName("&0Shadow").build();
        helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.BLACK).makeUnbreakable().build();
        chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.BLACK).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLACK).makeUnbreakable().build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();
        hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();
        hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().build();
        hotbar[2] = new CItemStack(Material.ENDER_PEARL).setAmt(2).build();
        hotbar[7] = new CItemStack(Material.INK_SACK).setAmt(2).setName("&0Schattenmantel").build();
        hotbar[8] = new CItemStack(Material.ARROW).setAmt(2).build();
    }

    PotionEffect[] dragonblood = {
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0),
            new PotionEffect(PotionEffectType.SPEED, 20, 0),
            new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0),
    };

    @Override
    public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {
        if(dealer.getItemInHand().getType() == Material.IRON_SWORD)
            receiver.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
    }

    @Override
    public void onItemUse(PlayerInteractEvent event, Player p, ItemStack item) {
        if (item.getType() != Material.INK_SACK)
            return;

        if(!Utils.hide(p))
            return;

        if (Utils.consumeItem(item))
            p.setItemInHand(AIR.clone());


        Bukkit.getScheduler().runTaskLater(Main.instance, () -> Utils.show(p), 20);

        for (PotionEffect pot : dragonblood) {
            if (p.hasPotionEffect(pot.getType()))
                p.removePotionEffect(pot.getType());
            p.addPotionEffect(pot);
        }
    }

}