package tk.cmplx.kitpvp.event;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.EconomyHelper;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Log;
import tk.cmplx.kitpvp.utils.Utils;

public class Death implements Listener {

	public Death() {
		Log.info("Registered PlayerDeath Event!");
	}

	@EventHandler
	public void onKill(PlayerDeathEvent e) {

		Player death = e.getEntity();

		e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c ist gestorben."));

		death.setMaxHealth(20);
		death.setLevel(0);

		Bukkit.getScheduler().runTaskLater(Main.instance, () -> death.spigot().respawn(), 20L);

		Player killer = e.getEntity().getKiller();

		// TNT Handler;
		if(e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent dmgEnv = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
			if(dmgEnv.getDamager() instanceof TNTPrimed) {
				killer = Utils.getMetadata((TNTPrimed) dmgEnv.getDamager(), "placer", Player.class);
			}
		}

		if (killer == null) return;

		if (killer.getName() == death.getName()) {
			e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c wollte nicht mehr Leben"));
			return;
		}

		//killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 4));
		killer.setHealth(killer.getMaxHealth());
		killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 3, 2);
		killer.setLevel(killer.getLevel() + 1);

		Kit k = Kit.getKit(killer);

		if(k != null)
			k.onKill(killer); 

		e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c wurde von &a" + killer.getDisplayName() + "&c getötet!"));

		int killstreakBonus = 0;

		switch (killer.getLevel()) {
			case 3: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &d3er-Killstreak!");
				killstreakBonus = 10;
				break;
			}
			case 5: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &55er-Killstreak!");
				killstreakBonus = 30;
				break;
			}
			case 10: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &410er-Killstreak!");
				killstreakBonus = 50;
				break;
			}
			case 50: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &050er-Killstreak!");
				killstreakBonus = 2000;
				break;
			}
		}

		if(EconomyHelper.economy())
			EconomyHelper.giveKillReward(killer, killstreakBonus, "Killstreak");

	}

}
