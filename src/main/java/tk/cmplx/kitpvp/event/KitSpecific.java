package tk.cmplx.kitpvp.event;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

	final List<PotionEffectType> positive = Arrays.asList(new PotionEffectType[] {
		PotionEffectType.ABSORPTION,
		PotionEffectType.DAMAGE_RESISTANCE,
		PotionEffectType.FAST_DIGGING,
		PotionEffectType.FIRE_RESISTANCE,
		PotionEffectType.HEAL,
		PotionEffectType.HEALTH_BOOST,
		PotionEffectType.INCREASE_DAMAGE,
		PotionEffectType.INVISIBILITY,
		PotionEffectType.JUMP,
		PotionEffectType.NIGHT_VISION,
		PotionEffectType.REGENERATION,
		PotionEffectType.SATURATION,
		PotionEffectType.SPEED,
		PotionEffectType.WATER_BREATHING
	});

	@EventHandler
	public void onPotion(PotionSplashEvent e) {
		if(e.getPotion().getShooter() instanceof Player) {
			Player p = (Player) e.getPotion().getShooter();
			List<PotionEffect> customEffects = ((PotionMeta)e.getPotion().getItem().getItemMeta()).getCustomEffects();
			boolean isPositive = customEffects.stream().allMatch(potioneffect -> positive.contains(potioneffect.getType()));
			
			e.getAffectedEntities().forEach(
				entity -> e.setIntensity(entity, entity.equals(p) ? (isPositive ? e.getIntensity(entity) : 0 ) : (!isPositive ? e.getIntensity(entity) : 0 ))
			);
		}
	}

	// @EventHandler
	// public void onBowShoot(EntityShootBowEvent e) {
		
	// 	TNTPrimed newent = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), TNTPrimed.class);
	// 	//p.setItem(new CPotion().addInstantEffect(true, 50).splash().build());
	// 	newent.setFuseTicks(50);

	// 	newent.setVelocity(e.getProjectile().getVelocity());
	// 	e.setProjectile(newent);
	// }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
		if (Utils.isNotCreative(e.getPlayer())) e.setCancelled(true);
		Kit playerKit = Kit.getKit(e.getPlayer());

		if(playerKit != null)
			playerKit.onItemDrop(e, e.getItemDrop().getItemStack(), e.getPlayer());
    }

	@EventHandler
	public void onTryPlace(BlockPlaceEvent e) {
		Kit playerKit = Kit.getKit(e.getPlayer());

		if(playerKit != null)
			playerKit.onBlockPlace(e, e.getPlayer());
	}

}
