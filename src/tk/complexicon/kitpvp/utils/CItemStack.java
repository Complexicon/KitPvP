package tk.complexicon.kitpvp.utils;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CItemStack {

    private ItemStack i;
    private ItemMeta meta;

    public CItemStack(Material m){
        this.i = new ItemStack(m);
        this.meta = i.getItemMeta();
    }

    public CItemStack(ItemStack i){
        this.i = i;
        this.meta = i.getItemMeta();
    }

    public CItemStack(Material m, int amount){
        this.i = new ItemStack(m, amount);
        this.meta = i.getItemMeta();
    }

    public CItemStack addEnchantment(Enchantment e, int lvl){
        meta.addEnchant(e, lvl, true);
        return applyMeta();
    }

    public CItemStack setName(String name){
        meta.setDisplayName(name.replace("&", "§"));
        return applyMeta();
    }

    public CItemStack makeUnbreakable(){
        meta.spigot().setUnbreakable(true);
        return applyMeta();
    }

    public CItemStack addLore(String[] lore){

        List<String> loreList = new ArrayList();

        for(String s : lore){
            loreList.add(s.replace("&", "§"));
        }

        meta.setLore(loreList);
        return applyMeta();
    }

    public ItemStack finish(){
        return i;
    }

    private CItemStack applyMeta(){
        i.setItemMeta(meta);
        return new CItemStack(i);
    }

}
