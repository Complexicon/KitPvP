package tk.complexicon.kitpvp.event;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tk.complexicon.kitpvp.Main;
import tk.complexicon.kitpvp.utils.CLeatherArmor;
import tk.complexicon.kitpvp.utils.Utils;

public class KitSpecific implements Listener {

    private Main m;

    public KitSpecific(Main m){
        this.m = m;
        m.l.info("Registered KitSpecific Events!");
    }

    @EventHandler
    public void onEnder(ProjectileHitEvent e) {

        if(e.getEntityType() == EntityType.ENDER_PEARL){
            if(e.getEntity().getShooter() instanceof Player){
                Player p = (Player) e.getEntity().getShooter();
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 2));
            }
        }

        e.getEntity().remove();

    }

    @EventHandler
    public void onSoup(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP)){
            Player  p = e.getPlayer();
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && Utils.isNotCreative(e.getPlayer())){
                if(p.getHealth() != p.getMaxHealth()){
                    if(p.getHealth() + 5 <= p.getMaxHealth()){
                        p.setHealth(p.getHealth() + 5);
                        p.getItemInHand().setType(Material.BOWL);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSnowball(EntityDamageByEntityEvent e) {
        if(!e.isCancelled()){
            if (e.getDamager() instanceof Snowball) {
                if(((Snowball) e.getDamager()).getShooter() instanceof Player){
                    Player shooter = (Player) ((Snowball) e.getDamager()).getShooter();
                    shooter.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
                }
                if(e.getEntity() instanceof LivingEntity){
                    LivingEntity l = (LivingEntity) e.getEntity();
                    l.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLifeDrain(EntityDamageByEntityEvent e) {

        int healingFactor = 3;

        if(!e.isCancelled()){
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                if (p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null || !p.getItemInHand().getItemMeta().hasDisplayName()) return;
                if(p.getItemInHand().getItemMeta().getDisplayName().contains(Utils.tr("&cStab des Lebensentzugs"))){
                    if(p.getHealth() + healingFactor > p.getMaxHealth()) p.setHealth(p.getMaxHealth());
                    else p.setHealth(p.getHealth() + healingFactor);
                    if(e.getEntity() instanceof Damageable){
                        Damageable d = (Damageable) e.getEntity();
                        if (!(d.getHealth() - (healingFactor + 1) < 0)) d.setHealth(d.getHealth() - (healingFactor + 1));
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWither(EntityDamageByEntityEvent e) {
        if(!e.isCancelled()){
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                if (p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null || !p.getItemInHand().getItemMeta().hasDisplayName()) return;
                if (p.getItemInHand().getItemMeta().getDisplayName().contains(Utils.tr("&8Wither-Schwert")) && e.getEntity() instanceof LivingEntity) {
                    LivingEntity l = (LivingEntity) e.getEntity();
                    l.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
                }
            }
        }
    }

    @EventHandler
    public void ghostSneak(PlayerToggleSneakEvent e){
        if(e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY) && Utils.isNotCreative(e.getPlayer())){
            Player p = e.getPlayer();
            if(!e.isSneaking()){
                p.getInventory().setBoots(new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build());
            }else{
                p.getInventory().setBoots(null);
            }
        }
    }

}
