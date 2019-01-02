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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
    public void onDKArrow(EntityDamageByEntityEvent e) {
        if(!e.isCancelled()){
            if (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player) {
                ItemMeta handMeta = ((Player) ((Arrow) e.getDamager()).getShooter()).getItemInHand().getItemMeta();
                if (handMeta.hasDisplayName() && handMeta.getDisplayName().contains("Bogen des Meisters") && e.getEntity() instanceof LivingEntity) {
                    LivingEntity l = (LivingEntity) e.getEntity();
                    l.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 10));
                    l.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
                    l.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 1));
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
    public void onCocain(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType().equals(Material.SUGAR)){
            Player  p = e.getPlayer();
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && Utils.isNotCreative(e.getPlayer())){

                PotionEffect[] effects = {
                        new PotionEffect(PotionEffectType.SPEED, 100, 2),
                        new PotionEffect(PotionEffectType.REGENERATION, 100, 1),
                        new PotionEffect(PotionEffectType.CONFUSION, 140, 0)
                };

                if(p.getItemInHand().getAmount() == 1) p.setItemInHand(new ItemStack(Material.AIR));
                else p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

                for(PotionEffect pot : effects){
                    if(p.hasPotionEffect(pot.getType())) p.removePotionEffect(pot.getType());
                    p.addPotionEffect(pot);
                }
            }
        }
    }

    @EventHandler
    public void onDragonblood(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType().equals(Material.BLAZE_POWDER)
                && (e.getAction() == Action.RIGHT_CLICK_AIR
                || e.getAction() == Action.RIGHT_CLICK_BLOCK
                && Utils.isNotCreative(e.getPlayer()))) {

            Player p = e.getPlayer();
            ItemStack inhand = p.getItemInHand();

            if(inhand.getItemMeta().hasDisplayName() && inhand.getItemMeta().getDisplayName().contains("Drachenblut")){
                PotionEffect[] effects = {
                        new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 1),
                        new PotionEffect(PotionEffectType.SPEED, 200, 0),
                        new PotionEffect(PotionEffectType.REGENERATION, 80, 2),
                        new PotionEffect(PotionEffectType.HEALTH_BOOST, 120, 1)
                };

                if (p.getItemInHand().getAmount() == 1) p.setItemInHand(new ItemStack(Material.AIR));
                else p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

                for (PotionEffect pot : effects) {
                    if (p.hasPotionEffect(pot.getType())) p.removePotionEffect(pot.getType());
                    p.addPotionEffect(pot);
                }
            }
        }
    }

    @EventHandler
    public void ghostSneak(PlayerToggleSneakEvent e){
        if(e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY) && Utils.isNotCreative(e.getPlayer()) && m.invisible.hasEntry(e.getPlayer().getName())){
            if(!e.isSneaking()){
                e.getPlayer().getInventory().setBoots(new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build());
            }else{
                e.getPlayer().getInventory().setBoots(null);
            }
        }
    }

}
