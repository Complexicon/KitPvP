package tk.cmplx.kitpvp.kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import tk.cmplx.kitpvp.utils.CItemStack;
import tk.cmplx.kitpvp.utils.CLeatherArmor;
import tk.cmplx.kitpvp.utils.CPotion;
import tk.cmplx.kitpvp.utils.Kit;

public class Mage extends Kit {
	public Mage() {
        permission = "kitpvp.mage";
        price = 4500;

        lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzschwert",
                "&2- Lederhelm",
                "&2- Eisenbrustplatte",
                "&2- Lederhose",
                "&2- Lederschuhe",
                "&2- Werfbarer Schadenstrank Level 2",
                "&2- 2 Werfbarer Schadenstrank Level 1",
                "&2- Trank der Regeneration",
                "&2- 2 Werfbare Heiltränke",
                "&2- Feuerresistenz Trank",
                "&2- Geschwindigkeits Trank",
                "&5- Permanent Nachtsicht"
        };

        displayItem = new CPotion().setType(PotionType.NIGHT_VISION).hideFlag(ItemFlag.HIDE_POTION_EFFECTS).setName("&6Magier").build();

        helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.FUCHSIA).makeUnbreakable().build();
        chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().build();
        legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.FUCHSIA).makeUnbreakable().build();
        boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.FUCHSIA).makeUnbreakable().build();

        hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().build();
        hotbar[1] = new CPotion().addInstantEffect(false, 1).splash().build();
        hotbar[2] = new CPotion().addInstantEffect(false).splash().setAmt(2).build();

        hotbar[4] = new CPotion().addInstantEffect(true).splash().setAmt(2).build();
        hotbar[5] = new CPotion().addPotionEffect(PotionEffectType.SPEED, 1, 60).setType(PotionType.SPEED).build();
        hotbar[7] = new CPotion().addPotionEffect(PotionEffectType.REGENERATION, 30).setType(PotionType.REGEN).build();
        hotbar[8] = new CPotion().addPotionEffect(PotionEffectType.FIRE_RESISTANCE, 120).setType(PotionType.FIRE_RESISTANCE).build();


        addEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
	}
}
