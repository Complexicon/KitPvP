package tk.cmplx.kitpvp.event;

import com.nametagedit.plugin.NametagEdit;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import tk.cmplx.kitpvp.utils.Log;

public class InvisibilityBugFix  implements Listener {
	
    Team invisible;
	String teamName = "kPvPinvisibleFix";

    public InvisibilityBugFix(){

        Scoreboard b = Bukkit.getScoreboardManager().getMainScoreboard();

        if(b.getTeam(teamName) != null){
            b.getTeam(teamName).unregister();
        }

        invisible = b.registerNewTeam(teamName);
        invisible.setNameTagVisibility(NameTagVisibility.NEVER);

        Log.info("Registered InvisibilityBugFix Event!");
    }

	@EventHandler
	public void onHasInvisibility(PlayerMoveEvent e) {
		String uuid = e.getPlayer().getUniqueId().toString();
		boolean hasInvis = e.getPlayer().getActivePotionEffects().stream().anyMatch(p -> p.getType().equals(PotionEffectType.INVISIBILITY));
		if(hasInvis && !invisible.hasEntry(uuid)) {
			invisible.addEntry(uuid);
			NametagEdit.getApi().clearNametag(e.getPlayer());
			NametagEdit.getApi().reloadNametag(e.getPlayer());
		} else if(!hasInvis && invisible.hasEntry(uuid)) {
			invisible.removeEntry(uuid);
			NametagEdit.getApi().clearNametag(e.getPlayer());
			NametagEdit.getApi().reloadNametag(e.getPlayer());
		}
	}

	public void unload(){
		invisible.unregister();
	}

}
