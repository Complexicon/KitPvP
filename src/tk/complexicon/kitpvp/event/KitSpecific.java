package tk.complexicon.kitpvp.event;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
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
            PotionEffect[] dkArrow = {
                new PotionEffect(PotionEffectType.SLOW, 60, 254),
                new PotionEffect(PotionEffectType.JUMP, 60, 254),
                new PotionEffect(PotionEffectType.BLINDNESS, 60, 1),
                new PotionEffect(PotionEffectType.WEAKNESS, 60, 1)
            };

            arrowHit(e, "Bogen des Meisters", dkArrow);

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
    public void onInstantUse(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && Utils.isNotCreative(e.getPlayer())){
            PotionEffect[] dragonblood = {
                    new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 0),
                    new PotionEffect(PotionEffectType.SPEED, 200, 0),
                    new PotionEffect(PotionEffectType.HEALTH_BOOST, 140, 1),
                    new PotionEffect(PotionEffectType.HEAL, 1, 10)
            };

            PotionEffect[] cocain = {
                    new PotionEffect(PotionEffectType.SPEED, 100, 2),
                    new PotionEffect(PotionEffectType.REGENERATION, 100, 1),
                    new PotionEffect(PotionEffectType.CONFUSION, 140, 0)
            };

            instantUse(e.getPlayer(), "Drachenblut", Material.BLAZE_POWDER, dragonblood);
            instantUse(e.getPlayer(), "Kokain", Material.SUGAR, cocain);

            if(checkInstantUse(e.getPlayer(), "Geisterhafter Pilz", Material.BROWN_MUSHROOM)){
                for(Player online : Bukkit.getOnlinePlayers()){
                    online.hidePlayer(e.getPlayer());
                }

                m.invisible.addEntry(e.getPlayer().getDisplayName());

                Bukkit.getScheduler().runTaskLater(this.m, () -> {
                    for(Player online : Bukkit.getOnlinePlayers()){
                        online.showPlayer(e.getPlayer());
                    }
                    m.invisible.removeEntry(e.getPlayer().getDisplayName());
                }, 300);

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

    private void arrowHit(EntityDamageByEntityEvent e, String itemQuery, PotionEffect[] effects){
        if (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player) {
            for (MetadataValue val : e.getDamager().getMetadata("kSourceItem")){
                if(val.asString().contains(itemQuery)){
                    if(e.getEntity() instanceof LivingEntity){
                        LivingEntity l = (LivingEntity) e.getEntity();
                        for(PotionEffect eff : effects){
                            l.addPotionEffect(eff);
                        }
                    }
                }
            }
        }
    }

    private void instantUse(Player p, String itemQuery, Material type, PotionEffect[] effects){
        if(checkInstantUse(p, itemQuery, type)){
            for (PotionEffect pot : effects) {
                if (p.hasPotionEffect(pot.getType())) p.removePotionEffect(pot.getType());
                p.addPotionEffect(pot);
            }
        }
    }

    private boolean checkInstantUse(Player p, String itemQuery, Material type){
        if (p.getItemInHand().getType().equals(type)) {

            ItemStack inhand = p.getItemInHand();

            if(inhand.getItemMeta().hasDisplayName() && inhand.getItemMeta().getDisplayName().contains(itemQuery)){

                if (p.getItemInHand().getAmount() == 1) p.setItemInHand(new ItemStack(Material.AIR));
                else p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

                return true;
            }
        }
        return false;
    }

}
