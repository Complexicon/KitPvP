package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Utils;

public class Ghost extends Kit {
    public Ghost() {
        permission = "kitpvp.ghost";
        buyable = false;

        lore = new String[] {
                "&6Dieses Kit beinhaltet:",
                "&2- Holzchwert",
                "&2- Werfbarer Heilungstränke",
                "&5- *Geisterhaft*"
        };

        displayItem = new CItemStack(Material.GLASS_BOTTLE).setName("Geist").build();

        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().build();
        hotbar[1] = new CPotion().addInstantEffect(true, 1).splash().setAmt(2).build();

        addEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
    }

    @Override
    public void onItemHeld(PlayerItemHeldEvent event, Player player) {
        if(player.getItemInHand().getType() == Material.AIR && player.isSneaking()) {
            Utils.hide(player);
        } else {
            Utils.show(player);
        }
    }

    @Override
    public void onSneak(PlayerToggleSneakEvent event, Player player) {
        if (event.isSneaking()){
            player.getInventory().setBoots(null);
            if(player.getItemInHand().getType() == Material.AIR) Utils.hide(player);
        } else {
            player.getInventory().setBoots(boots.clone());
            Utils.show(player);
        }
    }

}
