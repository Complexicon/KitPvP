package tk.cmplx.kitpvp.event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.Log;
import tk.cmplx.kitpvp.utils.Utils;

public class Death implements Listener {

	public Death() {
		Log.info("Registered PlayerDeath Event!");
	}

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		e.setDroppedExp(0);
		e.getDrops().clear();

		Player death = e.getEntity();

		e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c ist gestorben."));

		death.setMaxHealth(20);
		death.setLevel(0);

		Bukkit.getScheduler().runTaskLater(Main.instance, () -> death.spigot().respawn(), 20L);

		Player killer = e.getEntity().getKiller();

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

		e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c wurde von &a" + killer.getDisplayName() + "&c getötet!"));

		if (!Main.economyHook) return;

		Random r = new Random();
		int base = 4 + r.nextInt(6);
		int boost = 0;
		int killstreakBonus = 0;

		switch (killer.getLevel()) {
			case 3: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &d3er-Killstreak!");
				killstreakBonus = 10;
				Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
				break;
			}
			case 5: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &55er-Killstreak!");
				killstreakBonus = 30;
				Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
				break;
			}
			case 10: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &410er-Killstreak!");
				killstreakBonus = 50;
				Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
				break;
			}
			case 50: {
				Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &050er-Killstreak!");
				killstreakBonus = 2000;
				Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
				break;
			}
		}

		String boostType = "";

		if (killer.hasPermission("kitpvp.coinboost.4x")) {
			boost = 15 + r.nextInt(15);
			boostType = "4x";
		} else if (killer.hasPermission("kitpvp.coinboost.2x")) {
			boost = 5 + r.nextInt(5);
			boostType = "2x";
		}

		if (boost == 0) {
			Main.instance.econ.depositPlayer(killer, base + killstreakBonus);
			Utils.msg(killer, "&aDu hast: &6" + base + "© &aErhalten!");
		} else {
			Main.instance.econ.depositPlayer(killer, base + boost + killstreakBonus);
			Utils.msg(killer, "&aDu hast: &6+" + (base + boost) + "© &aErhalten! &d(" + boostType + " Coinboost Aktiv!)");
		}

	}

}
