package tk.complexicon.kitpvp.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class CLeatherArmor extends CItemStack {

    LeatherArmorMeta lMeta;

    List<Material> leather = new ArrayList();

    public CLeatherArmor(Material mat){
        super(mat);

        leather.add(Material.LEATHER_BOOTS);
        leather.add(Material.LEATHER_LEGGINGS);
        leather.add(Material.LEATHER_CHESTPLATE);
        leather.add(Material.LEATHER_HELMET);

        if(leather.contains(mat)){
            i = new ItemStack(mat);
            lMeta = (LeatherArmorMeta) i.getItemMeta();
            meta = lMeta;
        }
    }

    public CLeatherArmor color(Color c){
        lMeta.setColor(c);
        i.setItemMeta(lMeta);
        return this;
    }

    @Override
    public ItemStack build(){
        i.setItemMeta(lMeta);
        return i;
    }

}
