package tk.cmplx.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import tk.cmplx.kitpvp.Main;
import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.Kit;
import tk.cmplx.kitpvp.utils.Utils;

class Fuck extends BukkitRunnable {

	int x = 0;
	int z = 0;
	Location starting;

	public Fuck(Location starting, float yaw) {
		if(yaw >= -360 && yaw < -270) z = 1;
		else if(yaw >= 135 && yaw < 180) x = -1;
		else if(yaw >= 180 && yaw < 225) z = -1;
		else x = 1;

		this.starting = starting.add(x, 0, z);
	}

	int iter = 0;
	int repeat = 6;

	@Override
	public void run() {

		Block prev = starting.getWorld().getBlockAt(starting);
		if(prev.getType() == Material.FIRE) prev.setType(Material.AIR); 

		if(iter == repeat) {
			this.cancel();
			return;
		}

		this.starting = starting.add(x, 0, z);
		Block newFire = starting.getWorld().getBlockAt(starting);
		if(newFire.getType() == Material.AIR) {
			newFire.setType(Material.FIRE);
			newFire.getWorld().playSound(newFire.getLocation(), Sound.FIRE_IGNITE, 1, 1);
		}
		iter++;
	}
	
}

public class David extends Kit {
	public David() {
		displayItem = new CItemStack(Material.TNT).setName("&cDAVID MACH DAS AUS").build();
		chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10).build();
		hotbar[0] = new ItemStack(Material.TNT, 5);
		hotbar[1] = new ItemStack(Material.BRICK, 64);
		hotbar[2] = new ItemStack(Material.TRIPWIRE_HOOK, 1);
	}

	@Override
	public void onItemUse(PlayerInteractEvent event, Player player, ItemStack item) {
		if(item.getType() == Material.TRIPWIRE_HOOK) {
			//float yaw = player.getLocation().getYaw();
			//Log.info((-yaw / 90) + "" );
			//new Fuck(player.getLocation(), (-yaw / 90)).runTaskTimer(Main.instance, 0, 2);

			for(int i = 0; i < 30; i++) {
				TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
				tnt.setFuseTicks(10);
				player.setHealth(1);
				Utils.putMetadata(tnt, "placer", player);
			}
			player.setItemInHand(AIR.clone());

			Utils.bcast("&4" + player.getDisplayName() + " &cmacht Allahu Akbar!!");

		} else if(item.getType() == Material.BLAZE_ROD) {
			String[] loc = item.getItemMeta().getLore().get(0).split(";");
			Location l = new Location(player.getWorld(), Integer.parseInt(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[2]));
			player.getWorld().getBlockAt(l).setType(Material.AIR);
			TNTPrimed tnt = player.getWorld().spawn(l, TNTPrimed.class);
			tnt.setFuseTicks(30);
			Utils.putMetadata(tnt, "placer", player);
			if(Utils.consumeItem(item)) player.setItemInHand(new ItemStack(Material.AIR));
		}
	}

	BlockFace[] faces = new BlockFace[] {
		BlockFace.DOWN,
		BlockFace.SOUTH,
		BlockFace.EAST,
		BlockFace.NORTH,
		BlockFace.WEST,
	};

	@Override
	public void onBlockPlace(BlockPlaceEvent event, Player player) {
		if(event.getBlock().getType() == Material.TNT) {

			if(player.isSneaking()) {
				for(BlockFace face : faces) {
					if(event.getBlock().getRelative(face).getType() != Material.AIR) {
						event.getBlock().setType(event.getBlock().getRelative(face).getType());
						Location l = event.getBlock().getLocation();
						player.getInventory().addItem(new CItemStack(Material.BLAZE_ROD).setName("&cZünder").addLore(new String[]{
							l.getBlockX() + ";" + l.getBlockY() + ";" + l.getBlockZ()
						}).build());
						return;
					}
				}
			} else {
				event.getBlock().setType(Material.AIR);
				TNTPrimed tnt = event.getBlock().getWorld().spawn(event.getBlock().getLocation(), TNTPrimed.class);
				tnt.setFuseTicks(30);
				Utils.putMetadata(tnt, "placer", player);
			}

		} else {
			Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
				event.getBlock().setType(Material.AIR);
				event.getBlock().getWorld().spigot().playEffect(event.getBlock().getLocation(), Effect.CLOUD, 0, 0, 0.5f, 0.5f, 0.5f, 0.06f, 10, 256);
				event.getBlock().getWorld().playSound(event.getBlock().getLocation(), Sound.ITEM_PICKUP , 1.f, .8f);
			}, 100);
		}
	}

	@Override
	public void onItemDrop(PlayerDropItemEvent event, ItemStack item, Player player) {
		if(item.getType() == Material.TNT) {
			event.setCancelled(false);
			event.getItemDrop().remove();
			TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
			tnt.setFuseTicks(30);
			Utils.putMetadata(tnt, "placer", player);
		}
	}

}
