package tk.cmplx.kitpvp.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;

import tk.cmplx.kitpvp.Main;

public abstract class Kit {

    public static final ItemStack AIR = new ItemStack(Material.AIR);
    
    public String permission = "kitpvp.default";
	public String[] lore = new String[] { "keine lore!" };

    public boolean buyable = true;
    public boolean dropExclusive = false;
    public int price = 0;
    public double maxHealth = 20;

    public ItemStack displayItem = new CItemStack(Material.BARRIER).setName("&aDEFAULT").build();

    public ItemStack helm = AIR;
    public ItemStack chest = AIR;
    public ItemStack legs = AIR;
    public ItemStack boots = AIR;

    public ItemStack[] hotbar = {AIR, AIR, AIR, AIR, AIR, AIR, AIR, AIR, AIR};

    public List<ItemStack> extra = new ArrayList<ItemStack>();

    public List<PotionEffect> effects = new ArrayList<PotionEffect>();

    protected void multiStack(ItemStack stack, int amount) {
        for (int x = 0; x < amount; x++) {
            extra.add(stack);
        }
    }

    protected void addEffect(PotionEffect e){
        effects.add(e);
    }

	public void applyKit(Player p) {
		p.setMetadata("kitpvp.kit", new FixedMetadataValue(Main.instance, this));

		PlayerInventory pi = p.getInventory();

		// CLEARING

		pi.clear();
		p.setGameMode(GameMode.SURVIVAL);
	
		for (PotionEffect effect : p.getActivePotionEffects())
			p.removePotionEffect(effect.getType());

		// APPLY

		pi.setHelmet(helm);
		pi.setChestplate(chest);
		pi.setLeggings(legs);
		pi.setBoots(boots);
		p.setMaxHealth(maxHealth);


		for(int x = 0; x < hotbar.length; x++){
			pi.setItem(x, hotbar[x]);
		}

		for(ItemStack extra : extra){
			pi.addItem(extra);
		}

		for (PotionEffect eff : effects) 
			p.addPotionEffect(eff);

		p.setHealth(p.getMaxHealth());

	}

	public static Kit getKit(Player player) {
		return Utils.getMetadata(player, "kitpvp.kit", Kit.class);
	}

	public void onProjectileHit(ProjectileHitEvent event, Player player) {}
	public void onInteract(PlayerInteractEvent event, Player player) {}
	public void onItemUse(PlayerInteractEvent event, Player player, ItemStack item) {}
	public void onDamageDeal(EntityDamageByEntityEvent event, Player dealer, Player receiver) {}
	public void onDamageReceive(EntityDamageByEntityEvent event, Player dealer, Player receiver) {}
	public void onSneak(PlayerToggleSneakEvent event, Player player) {}

}
