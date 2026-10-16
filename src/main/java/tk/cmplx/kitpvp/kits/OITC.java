package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class OITC extends Kit {
    public OITC() {
        buyable = true;
        price = 5000;
        permission = "kitpvp.oitc";

        displayItem = new CItemStack(Material.BOW).setName("&2OITC").build();
        helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.GREEN).makeUnbreakable().build();
        chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.GREEN).makeUnbreakable().build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GREEN).makeUnbreakable().build();
        hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().build();
        hotbar[2] = new CItemStack(Material.ARROW).setName("&6Legendärer Pfeil").build();
        hotbar[7] = new CPotion().addInstantEffect(true).splash().build();
    }

    @Override
    public void onKill(Player player) {
        player.getInventory().addItem(hotbar[2].clone());
    }

    @Override
    public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {
        if(event.getDamager() instanceof Arrow) {
            event.setDamage(100);
		}
    }

}
