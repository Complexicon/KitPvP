package tk.cmplx.kitpvp.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Log;
import tk.cmplx.kitpvp.utils.Utils;

public class KitSpecific implements Listener {

    public KitSpecific(){
        Log.info("Registered KitSpecific Events!");
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
		if(e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			
			Kit playerKit = Kit.getKit(p);

			if(playerKit != null)
				playerKit.onProjectileHit(e, p); 

		}

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
		if(e.isCancelled()) return;

		Kit playerKit = Kit.getKit(e.getPlayer());

		if(playerKit != null)
			playerKit.onInteract(e, e.getPlayer()); 
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSnowball(EntityDamageByEntityEvent e) {
		if(e.isCancelled()) return;

		if(e.getEntity() instanceof Player) {
			Player receiver = (Player) e.getEntity();
			Player dealer = null;

			if(e.getDamager() instanceof Player)
				dealer = (Player) e.getDamager();
			else if (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player)
				dealer = (Player) ((Projectile) e.getDamager()).getShooter();

			if(dealer != null) {
				Kit dealerKit = Kit.getKit(dealer);
				Kit receiverKit = Kit.getKit(receiver);

				if(dealerKit != null)
					dealerKit.onDamageDeal(e, dealer, receiver);

				if(receiverKit != null)
					receiverKit.onDamageReceive(e, dealer, receiver); 
			}
		}
    }

    @EventHandler
    public void onInstantUse(PlayerInteractEvent e) {
        if(
			(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
			Utils.isNotCreative(e.getPlayer()) &&
			e.getMaterial() != Material.AIR
		) {
			Kit playerKit = Kit.getKit(e.getPlayer());

			if(playerKit != null)
				playerKit.onItemUse(e, e.getPlayer(), e.getItem()); 
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
		Kit playerKit = Kit.getKit(e.getPlayer());

		if(playerKit != null)
			playerKit.onSneak(e, e.getPlayer());
    }

}
