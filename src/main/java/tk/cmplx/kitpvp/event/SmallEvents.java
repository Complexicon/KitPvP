package tk.cmplx.kitpvp.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.Log;
import tk.cmplx.kitpvp.utils.Utils;

public class SmallEvents implements Listener {

    public SmallEvents(){
        Log.info("Registered Small Events!");
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e){
        e.setFoodLevel(100);
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (Utils.isNotCreative(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (Utils.isNotCreative(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.teleport(e.getPlayer().getWorld().getSpawnLocation());
        p.setHealth(p.getMaxHealth());

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        }, 5);

    }

    @EventHandler
    public void onArmorClick(InventoryClickEvent e){
        if (Utils.isNotCreative(e.getWhoClicked()) && e.getSlotType() == InventoryType.SlotType.ARMOR) e.setCancelled(true);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        clearPlayer(e.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e){
        clearPlayer(e.getPlayer());
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        e.getEntity().remove();
    }

    private void clearPlayer(Player p){
        p.getActivePotionEffects().forEach(effect -> p.removePotionEffect(effect.getType()));
        p.getInventory().clear();
        p.getInventory().setArmorContents(new ItemStack[4]);
        p.setExp(0F);
        p.setLevel(0);
        p.setMaxHealth(20);
    }

}
