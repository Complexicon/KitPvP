package tk.complexicon.kitpvp;

import com.nametagedit.plugin.NametagEdit;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tk.complexicon.kitpvp.utils.CItemStack;
import tk.complexicon.kitpvp.utils.CLeatherArmor;
import tk.complexicon.kitpvp.utils.Kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Events implements Listener {

    private Main m;
    private HashMap<Player, Long> cooldown = new HashMap<>();

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
            e.setLine(0, m.trMsg("&d»&2KitPVP&d«"));
            e.setLine(1, m.trMsg("&dWähle dein"));
            e.setLine(2, m.trMsg("&c[Kit]"));

        }
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getState() instanceof Sign) {
                Player p = e.getPlayer();
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).contains(m.trMsg("&d»&2KitPVP&d«"))) {
                    int multiplier = (int) Math.ceil(m.km.kitlist.size() / 9.0);
                    Inventory dummy = Bukkit.createInventory(null, 9 * multiplier, ChatColor.LIGHT_PURPLE + "Kits");
                    int x = 0;
                    for (Kit k : m.km.kitlist) {

                        CItemStack displayItem = new CItemStack(k.displayItem.clone());
                        List<String> curLore = displayItem.getLore();
                        List<String> statusLore = new ArrayList<>();

                        if(p.hasPermission(k.permission)){
                            statusLore.add(ChatColor.GREEN + "Bereits Gekauft!");
                        }else{
                            if(k.buyable){
                                statusLore.add(ChatColor.RED + "Nicht Gekauft!");
                                statusLore.add(ChatColor.GOLD + "Preis: " + k.price);
                                statusLore.add(ChatColor.AQUA + "Kaufe mit Rechtsklick!");
                            }else{
                                statusLore.add(ChatColor.RED + "Nicht Kaufbar!");
                                statusLore.add(ChatColor.LIGHT_PURPLE + "Exklusiv Für VIP!");
                            }
                        }

                        curLore.addAll(0, statusLore);

                        dummy.setItem(x, displayItem.addLore(curLore.toArray(new String[0])).build());
                        x++;
                    }

                    p.openInventory(dummy);
                }
            }
        }
    }

    @EventHandler
    public void onSoup(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType().equals(Material.MUSHROOM_SOUP)){
            Player  p = e.getPlayer();
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
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
                if(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null) return;
                if(!p.getItemInHand().getItemMeta().hasDisplayName()) return;
                if(p.getItemInHand().getItemMeta().getDisplayName().contains(m.trMsg("&cStab des Lebensentzugs"))){
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
                if(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null) return;
                if(!p.getItemInHand().getItemMeta().hasDisplayName()) return;
                if(p.getItemInHand().getItemMeta().getDisplayName().contains(m.trMsg("&8Wither-Schwert"))){
                    if(e.getEntity() instanceof LivingEntity){
                        LivingEntity l = (LivingEntity) e.getEntity();
                        l.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player p = e.getPlayer();

        for (PotionEffect effect : p.getActivePotionEffects()){
            p.removePotionEffect(effect.getType());
        }

        m.invisible.removeEntry(p.getDisplayName());

    }

    @EventHandler
    public void onKick(PlayerKickEvent e){

        Player p = e.getPlayer();

        for (PotionEffect effect : p.getActivePotionEffects()){
            p.removePotionEffect(effect.getType());
        }

        m.invisible.removeEntry(p.getDisplayName());

    }

    @EventHandler
    public void onKitSelect(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        ItemStack c = e.getCurrentItem();
        Inventory i = e.getInventory();

        if(c == null || c.getType() == Material.AIR){
            return;
        }

        if(i.getName().contains(m.km.kits.getName())){
            e.setCancelled(true);
            for(Kit k : m.km.kitlist){
                if(c.getItemMeta().getDisplayName() == k.displayItem.getItemMeta().getDisplayName() && c.getType() == k.displayItem.getType()){
                    if(p.hasPermission(k.permission)){
                        long cd = getCooldown(p, cooldown);

                        if(!p.hasPermission("kitpvp.bypasscooldown")){
                            if(cd < 30000){
                                int wait = (int) ((30000 - cd) / 1000);
                                m.sendMsg(p, "&cDu musst noch " + wait + " Sekunden Warten!");
                                return;
                            }
                            cooldown.remove(p);
                            startCooldown(p, cooldown);
                        }

                        m.sendMsg(p, "&aErfolgreich das Kit " + c.getItemMeta().getDisplayName() + " &a Ausgewählt!");
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

                        for (PotionEffect effect : p.getActivePotionEffects()){
                            p.removePotionEffect(effect.getType());
                        }

                        pi.setHelmet(k.helm);
                        pi.setChestplate(k.chest);
                        pi.setLeggings(k.legs);
                        pi.setBoots(k.boots);
                        p.setMaxHealth(k.maxHealth);
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
                            RegisteredServiceProvider<Economy> rspE = m.getServer().getServicesManager().getRegistration(Economy.class);
                            Economy econ = rspE.getProvider();
                            RegisteredServiceProvider<Permission> rspP = m.getServer().getServicesManager().getRegistration(Permission.class);
                            Permission perms = rspP.getProvider();

                            if(k.buyable){
                                if(econ.getBalance(p) >= k.price){
                                    econ.withdrawPlayer(p, k.price);
                                    perms.playerAdd(null, p, k.permission);
                                    m.sendMsg(p, "&aDu hast erfolgreich das Kit: " + c.getItemMeta().getDisplayName() + "&a gekauft. Vielen Dank!");
                                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0.8F);
                                    p.closeInventory();
                                    return;
                                }else{
                                    p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
                                    m.sendMsg(p, "&cDu hast nicht genug Geld für das Kit: " + c.getItemMeta().getDisplayName());
                                    return;
                                }
                            }else{
                                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
                                m.sendMsg(p, "&cDu kannst diese Kit nicht Kaufen!");
                                return;
                            }



                        }

                        m.sendMsg(p, "&cKeine Berechtigung auf das Kit: " + c.getItemMeta().getDisplayName());
                    }
                }
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setArmorContents(new ItemStack[4]);
        p.teleport(e.getPlayer().getWorld().getSpawnLocation());
        p.setMaxHealth(20);
        p.setExp(0F);
        p.setLevel(0);
        p.setHealth(p.getMaxHealth());

        Bukkit.getScheduler().runTaskLater(m, new Runnable() {
            @Override
            public void run() {
                e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            }
        }, 5);

    }

    @EventHandler
    public void ghostSneak(PlayerToggleSneakEvent e){
        if(e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)){
            Player p = e.getPlayer();
            if(!e.isSneaking()){
                p.getInventory().setBoots(new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build());
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

        e.setDeathMessage(m.trMsg("&4" + death.getDisplayName() + "&c ist gestorben."));

        death.setMaxHealth(20);
        death.setLevel(0);

        cooldown.remove(death);

        if(m.invisible.hasEntry(death.getDisplayName())){
            m.invisible.removeEntry(death.getDisplayName());
            if(m.nteHook){
                NametagEdit.getApi().clearNametag(death);
                NametagEdit.getApi().reloadNametag(death);
            }
        }

        Bukkit.getScheduler().runTaskLater(m, () -> death.spigot().respawn(), 20L);

        if (e.getEntity().getKiller() instanceof Player) {
            killer = e.getEntity().getKiller().getPlayer();
            if(killer.getName() == death.getName()){
                e.setDeathMessage(m.trMsg("&4" + death.getDisplayName() + "&c wollte nicht mehr Leben"));
                return;
            }
            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 4));
            killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 3, 2);
            killer.setLevel(killer.getLevel() + 1);

            e.setDeathMessage(m.trMsg("&4" + death.getDisplayName() + "&c wurde von &a" + killer.getDisplayName() + "&c getötet!"));

            if(m.economyHook){
                Random r = new Random();
                RegisteredServiceProvider<Economy> rsp = m.getServer().getServicesManager().getRegistration(Economy.class);
                Economy econ = rsp.getProvider();
                int base = 4 + r.nextInt(6);
                int boost = 0;
                int killstreakBonus = 0;

                switch (killer.getLevel()){
                    case 3:{
                        m.bcast("&a" + killer.getDisplayName() + " &6hat eine &d3er-Killstreak!");
                        killstreakBonus = 10;
                        m.sendMsg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    case 5:{
                        m.bcast("&a" + killer.getDisplayName() + " &6hat eine &55er-Killstreak!");
                        killstreakBonus = 30;
                        m.sendMsg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    case 10:{
                        m.bcast("&a" + killer.getDisplayName() + " &6hat eine &410er-Killstreak!");
                        killstreakBonus = 50;
                        m.sendMsg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    case 50:{
                        m.bcast("&a" + killer.getDisplayName() + " &6hat eine &050er-Killstreak!");
                        killstreakBonus = 2000;
                        m.sendMsg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    default:
                        break;
                }
                String boostType = "";
                if(killer.hasPermission("kitpvp.coinboost.2x")) boost = 5 + r.nextInt(5); boostType = "2x";

                if(killer.hasPermission("kitpvp.coinboost.4x")) boost = 15 + r.nextInt(15); boostType = "4x";

                if(boost == 0){
                    econ.depositPlayer(killer, base + killstreakBonus);
                    m.sendMsg(killer, "&aDu hast: &6" + base + "© &aErhalten!");
                }else{
                    econ.depositPlayer(killer, base + boost + killstreakBonus);
                    m.sendMsg(killer, "&aDu hast: &6+" + (base + boost) + "© &aErhalten! &d(" + boostType + " Coinboost Aktiv!)");
                }

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
