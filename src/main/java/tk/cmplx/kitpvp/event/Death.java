package tk.cmplx.kitpvp.event;

import com.nametagedit.plugin.NametagEdit;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.Utils;

import java.util.Random;

public class Death implements Listener {

    private Main m;

    public Death(Main m){
        this.m = m;
        m.l.info("Registered PlayerDeath Event!");
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {

        e.setDroppedExp(0);
        e.getDrops().clear();

        Player death = e.getEntity();
        Player killer;

        e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c ist gestorben."));

        death.setMaxHealth(20);
        death.setLevel(0);

        m.cooldown.remove(death);

        if(m.invisible.hasEntry(death.getDisplayName())){
            m.invisible.removeEntry(death.getDisplayName());
            if(m.nteHook){
                NametagEdit.getApi().clearNametag(death);
                NametagEdit.getApi().reloadNametag(death);
            }
        }

        Bukkit.getScheduler().runTaskLater(m, () -> death.spigot().respawn(), 20L);

        if (e.getEntity().getKiller() != null) {

            killer = e.getEntity().getKiller().getPlayer();

            if(killer.getName() == death.getName()){
                e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c wollte nicht mehr Leben"));
                return;
            }

            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 4));
            killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 3, 2);
            killer.setLevel(killer.getLevel() + 1);

            e.setDeathMessage(Utils.tr("&4" + death.getDisplayName() + "&c wurde von &a" + killer.getDisplayName() + "&c getötet!"));

            if(m.economyHook){
                Random r = new Random();
                int base = 4 + r.nextInt(6);
                int boost = 0;
                int killstreakBonus = 0;

                switch (killer.getLevel()){
                    case 3:{
                        Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &d3er-Killstreak!");
                        killstreakBonus = 10;
                        Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    case 5:{
                        Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &55er-Killstreak!");
                        killstreakBonus = 30;
                        Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    case 10:{
                        Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &410er-Killstreak!");
                        killstreakBonus = 50;
                        Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    case 50:{
                        Utils.bcast("&a" + killer.getDisplayName() + " &6hat eine &050er-Killstreak!");
                        killstreakBonus = 2000;
                        Utils.msg(killer, "&aDu erhälst einen Killstreak-Bonus von: " + killstreakBonus + "©!");
                        break;
                    }
                    default:
                        break;
                }

                String boostType = "";

                if(killer.hasPermission("kitpvp.coinboost.2x")) {
                    boost = 5 + r.nextInt(5);
                    boostType = "2x";
                }

                if(killer.hasPermission("kitpvp.coinboost.4x")) {
                    boost = 15 + r.nextInt(15);
                    boostType = "4x";
                }

                if(boost == 0){
                    m.econ.depositPlayer(killer, base + killstreakBonus);
                    Utils.msg(killer, "&aDu hast: &6" + base + "© &aErhalten!");
                }else{
                    m.econ.depositPlayer(killer, base + boost + killstreakBonus);
                    Utils.msg(killer, "&aDu hast: &6+" + (base + boost) + "© &aErhalten! &d(" + boostType + " Coinboost Aktiv!)");
                }

            }
        } else {
            return;
        }
    }
    
}
