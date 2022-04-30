package tk.cmplx.kitpvp.event;

import com.nametagedit.plugin.NametagEdit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import tk.cmplx.kitpvp.utils.Log;

public class InvisibilityBugFix  implements Listener {
	
    Team invisible;
	String teamName = "kPvPinvisibleFix";

    public InvisibilityBugFix(){
        Log.info("Registered InvisibilityBugFix Event!");
    }

	@EventHandler
	public void onHasInvisibility(PlayerMoveEvent e) {
		/// TODO: Optimize
		boolean hasInvis = e.getPlayer().getActivePotionEffects().stream().anyMatch(p -> p.getType().equals(PotionEffectType.INVISIBILITY));
		if(hasInvis) {
			NametagEdit.getApi().clearNametag(e.getPlayer());
		} else {
			NametagEdit.getApi().reloadNametag(e.getPlayer());
		}
	}

	public void unload(){
		//invisible.unregister();
	}

}
