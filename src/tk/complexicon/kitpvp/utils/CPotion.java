package tk.complexicon.kitpvp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class CPotion extends CItemStack {

    PotionMeta pMeta;
    Potion p;

    public CPotion() {
        super(Material.POTION);
        p = new Potion(PotionType.JUMP);
        pMeta = (PotionMeta) i.getItemMeta();
        meta = pMeta;
    }

    public CPotion setType(PotionType type){
        p.setType(type);
        return this;
    }

    public CPotion splash(){
        p.setSplash(true);
        return this;
    }

    public CPotion addPotionEffect(PotionEffectType type,int amplifier, int duration){
        pMeta.addCustomEffect(new PotionEffect(type, duration*20, amplifier), true);
        i.setItemMeta(pMeta);
        return this;
    }

    public CPotion addPotionEffect(PotionEffectType type, int duration){
        return addPotionEffect(type, 0, duration);
    }

    public CPotion addInstantEffect(boolean isHeal, int amplifier){
        if(isHeal){
            return addPotionEffect(PotionEffectType.HEAL, amplifier, 1).setType(PotionType.INSTANT_HEAL);
        }
        return addPotionEffect(PotionEffectType.HARM, amplifier, 1).setType(PotionType.INSTANT_DAMAGE);
    }

    public CPotion addInstantEffect(boolean isHeal){
        if(isHeal){
            return addPotionEffect(PotionEffectType.HEAL, 0, 1).setType(PotionType.INSTANT_HEAL);
        }
        return addPotionEffect(PotionEffectType.HARM, 0, 1).setType(PotionType.INSTANT_DAMAGE);
    }

    @Override
    public ItemStack build(){
        p.apply(i);
        i.setItemMeta(pMeta);
        return i;
    }

}
