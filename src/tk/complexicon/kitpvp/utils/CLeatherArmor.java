package tk.complexicon.kitpvp.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import tk.complexicon.kitpvp.utils.CItemStack;

import java.util.ArrayList;
import java.util.List;

public class CLeatherArmor {

    ItemStack i;
    LeatherArmorMeta m;

    List<Material> leather = new ArrayList();

    public CLeatherArmor(Material mat){

        leather.add(Material.LEATHER_BOOTS);
        leather.add(Material.LEATHER_LEGGINGS);
        leather.add(Material.LEATHER_CHESTPLATE);
        leather.add(Material.LEATHER_HELMET);

        if(leather.contains(mat)){
            i = new ItemStack(mat);
            m = (LeatherArmorMeta) i.getItemMeta();
        }
    }

    public CItemStack color(Color c){
        m.setColor(c);
        i.setItemMeta(m);
        return new CItemStack(i);
    }

}
