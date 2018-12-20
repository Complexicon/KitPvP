package tk.complexicon.kitpvp.event;

import com.nametagedit.plugin.NametagEdit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tk.complexicon.kitpvp.Main;
import tk.complexicon.kitpvp.utils.Kit;
import tk.complexicon.kitpvp.utils.Utils;

import java.util.HashMap;

public class KitSelection implements Listener {

    private Main m;

    public KitSelection(Main m){
        this.m = m;
        m.l.info("Registered KitSelection Event!");
    }

    @EventHandler
    public void onKitSelect(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        ItemStack c = e.getCurrentItem();
        Inventory i = e.getInventory();

        if(c.getType() == Material.AIR || c == null) return;

        if(i.getName().contains(m.km.kits.getName())){

            e.setCancelled(true);

            for(Kit k : m.km.kitlist){

                if(c.getItemMeta().getDisplayName() == k.displayItem.getItemMeta().getDisplayName() && c.getType() == k.displayItem.getType()){

                    if(p.hasPermission(k.permission)){
                        if(!p.hasPermission("kitpvp.bypasscooldown")){
                            long cd = getCooldown(p, m.cooldown);
                            if(cd < 30000){
                                int wait = (int) ((30000 - cd) / 1000);
                                Utils.msg(p, "&cDu musst noch " + wait + " Sekunden Warten!");
                                return;
                            }
                            m.cooldown.remove(p);
                            startCooldown(p, m.cooldown);
                        }

                        Utils.msg(p, "&aErfolgreich das Kit " + c.getItemMeta().getDisplayName() + " &a Ausgewählt!");
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 3, 1);
                        p.closeInventory();

                        PlayerInventory pi = p.getInventory();

                        pi.clear();

                        if(m.invisible.hasEntry(p.getDisplayName())){
                            m.invisible.removeEntry(p.getDisplayName());

                            if(m.nteHook){
                                NametagEdit.getApi().clearNametag(p);
                                NametagEdit.getApi().reloadNametag(p);
                            }
                        }

                        for (PotionEffect effect : p.getActivePotionEffects()) p.removePotionEffect(effect.getType());

                        pi.setHelmet(k.helm);
                        pi.setChestplate(k.chest);
                        pi.setLeggings(k.legs);
                        pi.setBoots(k.boots);
                        p.setMaxHealth(k.maxHealth);
                        p.setGameMode(GameMode.SURVIVAL);

                        if(!k.effects.isEmpty()){
                            for(PotionEffect eff: k.effects){
                                p.addPotionEffect(eff);
                                if(eff.getType() == PotionEffectType.INVISIBILITY){
                                    m.invisible.addEntry(p.getDisplayName());
                                }
                            }
                        }

                        for(int x = 0; x < k.hotbar.length; x++){
                            pi.setItem(x, k.hotbar[x]);
                        }

                        for(ItemStack extra : k.extra){
                            pi.addItem(extra);
                        }

                        p.setHealth(p.getMaxHealth());

                    }else{

                        if(e.getClick() == ClickType.RIGHT){
                            if(m.economyHook && m.permissionHook){
                                if(k.buyable){
                                    if(m.econ.getBalance(p) >= k.price){

                                        m.econ.withdrawPlayer(p, k.price);
                                        m.perms.playerAdd(null, p, k.permission);

                                        Utils.msg(p, "&aDu hast erfolgreich das Kit: " + c.getItemMeta().getDisplayName() + "&a gekauft. Vielen Dank!");

                                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0.8F);
                                        p.closeInventory();
                                        return;
                                    }else{
                                        p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
                                        Utils.msg(p, "&cDu hast nicht genug Geld für das Kit: " + c.getItemMeta().getDisplayName());
                                        return;
                                    }
                                }else{
                                    p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
                                    Utils.msg(p, "&cDu kannst diese Kit nicht Kaufen!");
                                    return;
                                }
                            }
                        }
                        Utils.msg(p, "&cKeine Berechtigung auf das Kit: " + c.getItemMeta().getDisplayName());
                    }
                }
            }
        }

    }

    private long getCooldown(Player p, HashMap<Player, Long> l){
        if(l.containsKey(p)){
            return System.currentTimeMillis() - l.get(p);
        }else{
            return Integer.MAX_VALUE;
        }
    }

    private void startCooldown(Player p, HashMap<Player, Long> l){
        l.put(p, System.currentTimeMillis());
    }

}
