package tk.cmplx.kitpvp.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SignInteraction implements Listener {

    private Main m;

    public SignInteraction(Main m){
        this.m = m;
        m.l.info("Registered SignInteraction Event!");
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[kitpvp]")) {
            e.setLine(0, Utils.tr("&d»&2KitPVP&d«"));
            e.setLine(1, Utils.tr("&dWähle dein"));
            e.setLine(2, Utils.tr("&c[Kit]"));

        }
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).contains(Utils.tr("&d»&2KitPVP&d«"))) {

                Player p = e.getPlayer();
                Inventory dummy = Bukkit.createInventory(null, 9 * (int) Math.ceil(m.km.kitlist.size() / 9.0), ChatColor.LIGHT_PURPLE + "Kits");

                int x = 0;
                for (Kit k : m.km.kitlist) {

                    CItemStack displayItem = new CItemStack(k.displayItem.clone());
                    List<String> curLore = displayItem.getLore();
                    List<String> statusLore = new ArrayList<>();

                    if (p.hasPermission(k.permission)) {
                        statusLore.add(ChatColor.GREEN + "Bereits Gekauft!");
                    } else {
                        if(m.economyHook && m.permissionHook){
                            if (k.buyable) {
                                statusLore.add(ChatColor.RED + "Nicht Gekauft!");
                                statusLore.add(ChatColor.GOLD + "Preis: " + k.price);
                                statusLore.add(ChatColor.AQUA + "Kaufe mit Rechtsklick!");
                            } else if(k.dropExclusive){
                                statusLore.add(ChatColor.RED + "Nicht Kaufbar!");
                                statusLore.add(ChatColor.AQUA + "Nur als Drop Erhältlich!");
                            } else {
                                statusLore.add(ChatColor.RED + "Nicht Kaufbar!");
                                statusLore.add(ChatColor.LIGHT_PURPLE + "Exklusiv Für VIP!");
                            }
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
