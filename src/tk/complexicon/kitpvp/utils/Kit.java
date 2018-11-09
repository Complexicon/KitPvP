package tk.complexicon.kitpvp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Kit {

    private ItemStack air = new ItemStack(Material.AIR);
    
    public String permission = "kitpvp.default";

    public boolean buyable = true;
    public int price = 0;
    public double maxHealth = 20;

    public ItemStack displayItem = new CItemStack(Material.BARRIER).setName("§aDEFAULT").build();

    public ItemStack helm = air;
    public ItemStack chest = air;
    public ItemStack legs = air;
    public ItemStack boots = air;

    public ItemStack[] hotbar = {air, air, air, air, air, air, air, air, air};

    public List<ItemStack> extra = new ArrayList();

    public List<PotionEffect> effects = new ArrayList();

    public void multiStack(ItemStack stack, int amount) {
        for (int x = 0; x < amount; x++) {
            extra.add(stack);
        }
    }

    public void addEffect(PotionEffect e){
        effects.add(e);
    }

}
