package tk.complexicon.kitpvp.utils;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CItemStack {

    ItemStack i;
    ItemMeta meta;

    public CItemStack(Material m){
        this(new ItemStack(m));
    }

    public CItemStack(ItemStack i){
        this.i = i;
        this.meta = i.getItemMeta();
    }

    public CItemStack(Material m, ItemMeta meta){
        this.i = new ItemStack(m);
        this.meta = meta;
    }

    public CItemStack(Material m, int amount){
        this(new ItemStack(m, amount));
    }

    public CItemStack addEnchantment(Enchantment e, int lvl){
        meta.addEnchant(e, lvl, true);
        return this;
    }

    public CItemStack setName(String name){
        meta.setDisplayName(name.replace("&", "§"));
        return this;
    }

    public CItemStack makeUnbreakable(){
        meta.spigot().setUnbreakable(true);
        return this;
    }

    public CItemStack addLore(String[] lore){

        List<String> loreList = new ArrayList();

        for(String s : lore){
            loreList.add(s.replace("&", "§"));
        }

        meta.setLore(loreList);
        return this;
    }

    public CItemStack setDurability(int durability){
        i.setDurability((short) durability);
        return this;
    }

    public CItemStack setAmt(int amt){
        i.setAmount(amt);
        return this;
    }

    public CItemStack hideFlag(ItemFlag i){
        meta.addItemFlags(i);
        return this;
    }

    public List<String> getLore(){
        return meta.getLore();
    }

    public ItemStack build(){
        i.setItemMeta(meta);
        return i;
    }

}
