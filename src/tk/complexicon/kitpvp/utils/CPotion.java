package tk.complexicon.kitpvp.utils;

import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

@SuppressWarnings("deprecation")
public class CPotion {

    private Potion p;

    public CPotion(PotionType type, int level, boolean splash, boolean extend){
        p = new Potion(type, level, splash);
        if(extend) p.setHasExtendedDuration(true);
    }

    public CPotion(PotionType type, int level, boolean extend){
        p = new Potion(type, level, false);
        if(extend) p.setHasExtendedDuration(true);
    }

    public CPotion(PotionType type, int level){
        p = new Potion(type, level, false);
    }

    public CPotion(PotionType type){
        p = new Potion(type, 1, false);
    }

    public CItemStack toStack(int amount){
        return new CItemStack(p.toItemStack(amount));
    }

    public CItemStack toStack(){
        return new CItemStack(p.toItemStack(1));
    }

}
