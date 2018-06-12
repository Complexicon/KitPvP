package tk.complexicon.kitpvp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tk.complexicon.kitpvp.utils.CLeatherArmor;
import tk.complexicon.kitpvp.utils.Kit;

import java.util.HashMap;
import java.util.Random;

public class Events implements Listener {

    Main m;
    HashMap<Player, Long> cooldown = new HashMap();

    public Events(Main m){
        this.m = m;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
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
    public void onHunger(FoodLevelChangeEvent e){
        e.setFoodLevel(100);
        e.setCancelled(true);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[kitpvp]")) {
            e.setLine(0, "§c-§bKitPvP§c-");
            e.setLine(1, "§2Wähle dein");
            e.setLine(2, "§c-Kit-");

        }
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getState() instanceof Sign) {
                Player p = e.getPlayer();
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).contains("§c-§bKitPvP§c-")) {
                    p.openInventory(m.km.kits);
                }
            }
        }
    }

    @EventHandler
    public void onSoup(PlayerInteractEvent e) {

        if(e.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP)){
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(e.getPlayer().getHealth() != 20){
                    if(e.getPlayer().getHealth() + 5 <= 20){
                        e.getPlayer().setHealth(e.getPlayer().getHealth() + 5);
                        e.getPlayer().getItemInHand().setType(Material.BOWL);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSnowball(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball) {
            if(((Snowball) e.getDamager()).getShooter() instanceof Player){
                Player shooter = (Player) ((Snowball) e.getDamager()).getShooter();
                shooter.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
            }
            if(e.getEntity() instanceof Player){
                Player p = (Player) e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
            }
        }
    }

    @EventHandler
    public void onKitSelect(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        ItemStack c = e.getCurrentItem();
        Inventory i = e.getInventory();

        if(c == null || c.getType() == Material.AIR){
            return;
        }

        if(i.getName() == m.km.kits.getName()){
            e.setCancelled(true);
            for(Kit k : m.km.kitlist){
                if(c.getItemMeta().getDisplayName() == k.displayItem.getItemMeta().getDisplayName() && c.getType() == k.displayItem.getType()){
                    if(p.hasPermission(k.permission)){
                        long cd = getCooldown(p, cooldown);

                        if(!p.hasPermission("kitpvp.bypasscooldown")){
                            if(cd < 30000){
                                int wait = (int) ((30000 - cd) / 1000);
                                p.sendMessage("§cDu musst noch " + wait + " Sekunden Warten!");
                                return;
                            }
                            cooldown.remove(p);
                            startCooldown(p, cooldown);
                        }

                        p.sendMessage("§aErfolgreich das Kit " + c.getItemMeta().getDisplayName() + " §a Ausgewählt!");
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 3, 1);
                        p.closeInventory();
                        p.setHealth(20);

                        PlayerInventory pi = p.getInventory();

                        pi.clear();

                        for (PotionEffect effect : p.getActivePotionEffects()){
                            p.removePotionEffect(effect.getType());
                        }

                        pi.setHelmet(k.helm);
                        pi.setChestplate(k.chest);
                        pi.setLeggings(k.legs);
                        pi.setBoots(k.boots);
                        if(!k.effects.isEmpty()){
                            for(PotionEffect eff: k.effects){
                                p.addPotionEffect(eff);
                            }
                        }

                        for(int x = 0; x < k.hotbar.length; x++){
                            pi.setItem(x, k.hotbar[x]);
                        }

                        for(ItemStack extra : k.extra){
                            pi.addItem(extra);
                        }

                    }else{
                        p.sendMessage("§cKeine Berechtigung auf das Kit: " + c.getItemMeta().getDisplayName());
                    }
                }
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setArmorContents(new ItemStack[4]);
        e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
    }

    @EventHandler
    public void ghostSneak(PlayerToggleSneakEvent e){
        if(e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)){
            Player p = e.getPlayer();
            if(!e.isSneaking()){
                p.getInventory().setBoots(new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().finish());
            }else{
                p.getInventory().setBoots(null);
            }
        }
    }

    @EventHandler
    public void onArmorClick(InventoryClickEvent e){
        if(e.getWhoClicked().getGameMode() != GameMode.CREATIVE){
            if(e.getSlotType() == InventoryType.SlotType.ARMOR){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {

        e.setDroppedExp(0);
        e.getDrops().clear();

        Player death = e.getEntity();
        Player killer;

        e.setDeathMessage(ChatColor.DARK_RED + death.getDisplayName() + "§c ist gestorben.");

        cooldown.remove(death);

        Bukkit.getScheduler().runTaskLater(m, new Runnable() {

            @Override
            public void run() {
                death.spigot().respawn();
            }

        }, 20L);

        if (e.getEntity().getKiller() instanceof Player) {
            killer = e.getEntity().getKiller().getPlayer();
            if(killer.getName() == death.getName()){
                e.setDeathMessage(ChatColor.DARK_RED + death.getDisplayName() + "§c wollte nicht mehr Leben");
                return;
            }
            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 4));
            killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 3, 2);
            e.setDeathMessage(ChatColor.DARK_RED + death.getDisplayName() + "§c wurde von " + ChatColor.GREEN + killer.getDisplayName() + "§c getötet!");
            if(m.economyHook){
                Random r = new Random();
                RegisteredServiceProvider<Economy> rsp = m.getServer().getServicesManager().getRegistration(Economy.class);
                Economy econ = rsp.getProvider();
                int money = 4 + r.nextInt(6);
                econ.depositPlayer(killer, money);
                Bukkit.getScheduler().runTaskLater(m, new Runnable() {

                    @Override
                    public void run() {
                        killer.sendMessage("§aDu hast: " + ChatColor.GOLD + "+" + money + "G §aErhalten!");
                    }

                }, 5L);
            }
        } else {
            return;
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
