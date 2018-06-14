package tk.complexicon.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import tk.complexicon.kitpvp.utils.CItemStack;
import tk.complexicon.kitpvp.utils.CLeatherArmor;
import tk.complexicon.kitpvp.utils.CPotion;
import tk.complexicon.kitpvp.utils.Kit;

import java.util.ArrayList;
import java.util.List;

class KitManager {

    List<Kit> kitlist;
    Inventory kits;

    void initKits() {

        kitlist = new ArrayList();

        swordsman();
        archer();
        pyro();
        tank();
        snowman();
        rod();
        witch();
        rabbit();
        canada();
        housewife();

        berserk();
        ender();
        soup();
        luca();
        templar();
        mage();
        ghost();

        int multiplier = (int) Math.ceil(kitlist.size() / 9.0);

        kits = Bukkit.createInventory(null, 9 * multiplier, ChatColor.LIGHT_PURPLE + "Kits");
        int x = 0;
        for (Kit k : kitlist) {
            kits.setItem(x, k.displayItem);
            x++;
        }
    }

    private void addKit(Kit k) {
        kitlist.add(k);
    }

    private void swordsman() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenrüstung",
                "&2- Diamantschwert",
                "&2- 2 Goldäpfel"
        };

        k.displayItem = new CItemStack(Material.DIAMOND_SWORD).setName("&9Schwertkämpfer").addLore(lore).finish();

        k.helm = new CItemStack(Material.IRON_HELMET).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.IRON_CHESTPLATE).makeUnbreakable().finish();
        k.legs = new CItemStack(Material.IRON_LEGGINGS).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.IRON_BOOTS).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.DIAMOND_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CItemStack(Material.GOLDEN_APPLE, 2).finish();

        addKit(k);

    }

    private void archer() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzschwert Schärfe 1",
                "&2- Bogen Stärke 2 Schlag 1",
                "&2- Trank der Heilung",
                "&2- 64 Pfeile",
                "&2- Lederkappe",
                "&2- Kettenbrustpanzer",
                "&2- Lederhose",
                "&2- Lederschuhe",
                "&5- Permanent Geschwindigkeit 1"
        };

        k.displayItem = new CItemStack(Material.BOW).setName("&2Archer").addLore(lore).finish();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.GREEN).makeUnbreakable().addLore(lore).finish();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().finish();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.GREEN).makeUnbreakable().finish();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GREEN).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().addEnchantment(Enchantment.DAMAGE_ALL, 1).finish();
        k.hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().addEnchantment(Enchantment.ARROW_DAMAGE, 2).addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).finish();
        k.hotbar[7] = new CPotion(PotionType.INSTANT_HEAL).toStack().finish();
        k.hotbar[8] = new CItemStack(Material.ARROW, 64).finish();

        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void pyro() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Goldschwert Verbrennung 1",
                "&2- Bogen Flamme 1",
                "&2- 2 Goldäpfel",
                "&2- 32 Pfeile",
                "&2- Goldhelm",
                "&2- Goldbrustpanzer Schutz 2",
                "&2- Goldhose",
                "&2- Goldschuhe",
                "&5- Permanent Feuerschutz 1"
        };

        k.displayItem = new CItemStack(Material.FIREBALL).setName("&6Pyro").addLore(lore).finish();

        k.helm = new CItemStack(Material.GOLD_HELMET).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.GOLD_CHESTPLATE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).finish();
        k.legs = new CItemStack(Material.GOLD_LEGGINGS).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.GOLD_BOOTS).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.GOLD_SWORD).makeUnbreakable().addEnchantment(Enchantment.FIRE_ASPECT, 1).setName("&6Flammenschwert").finish();
        k.hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().addEnchantment(Enchantment.ARROW_FIRE, 1).setName("&6Flammenbogen").finish();
        k.hotbar[7] = new CItemStack(Material.GOLDEN_APPLE, 2).finish();
        k.hotbar[8] = new CItemStack(Material.ARROW, 32).finish();


        k.addEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void tank() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Diamantrüstung",
                "&c- Permanent Langsamkeit 1"
        };

        k.displayItem = new CItemStack(Material.DIAMOND_CHESTPLATE).setName("&bTank").addLore(lore).finish();

        k.helm = new CItemStack(Material.DIAMOND_HELMET).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.DIAMOND_CHESTPLATE).makeUnbreakable().finish();
        k.legs = new CItemStack(Material.DIAMOND_LEGGINGS).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.DIAMOND_BOOTS).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().finish();

        k.addEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void berserk() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Lederjacke",
                "&5- Permanent Stärke 1"
        };

        k.permission = "kitpvp.donator";

        k.displayItem = new CItemStack(Material.IRON_SWORD).setName("&4Berserker").addLore(lore).finish();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().finish();

        k.addEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void snowman() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert Schärfe 1",
                "&2- 64 Schneebälle",
                "&2- Komplette Lederrüstung",
                "&5- Schneebälle Verlangsamen den Getroffenen",
                "&5- Schneebälle Heilen beim Treffen Für Extrem Kurze Zeit",
                "&5- Permanent Geschwindigkeit 1"

        };

        k.displayItem = new CItemStack(Material.SNOW_BALL).setName("&7Schneemann").addLore(lore).finish();
        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.WHITE).makeUnbreakable().finish();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).finish();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE).makeUnbreakable().finish();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.WHITE).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().addEnchantment(Enchantment.DAMAGE_ALL, 1).finish();
        k.hotbar[1] = new CItemStack(Material.SNOW_BALL, 64).finish();

        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void ender() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Lederhelm",
                "&2- Eisenbrustpanzer Schussicher 1",
                "&2- 8 Enderperlen",
                "&2- Lederhose",
                "&2- Eisenschuhe Federfall 10",
                "&5- Heilung beim Treffen der Enderperle für Kurze Zeit",
                "&5- Permanent Geschwindigkeit 1"
        };

        k.permission = "kitpvp.donator";

        k.displayItem = new CItemStack(Material.ENDER_PEARL).setName("&3Ender").addLore(lore).finish();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.BLACK).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1).makeUnbreakable().finish();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLACK).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_FALL, 10).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CItemStack(Material.ENDER_PEARL, 16).finish();
        k.hotbar[8] = new CItemStack(Material.GOLDEN_APPLE, 2).finish();

        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void soup() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Kettenrüstung",
                "&2- 8 Zaubersuppen",
                "&5- Heilung beim nutzen der Suppen"
        };

        k.permission = "kitpvp.donator";

        k.displayItem = new CItemStack(Material.MUSHROOM_SOUP).setName("&eSoup").addLore(lore).finish();

        k.helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().finish();
        k.legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().finish();
        k.multiStack(new CItemStack(Material.MUSHROOM_SOUP).setName("&eZaubersuppe").finish(), 8);

        addKit(k);
    }

    private void rod() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Angel",
                "&2- 2 Goldäpfel",
                "&2- Kettenhelm",
                "&2- Lederbrustplatte",
                "&2- Kettenhose",
                "&2- Kettenschuhe"
        };

        k.displayItem = new CItemStack(Material.FISHING_ROD).setName("&bAngler").addLore(lore).finish();

        k.helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.LEATHER_CHESTPLATE).makeUnbreakable().finish();
        k.legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CItemStack(Material.FISHING_ROD).makeUnbreakable().finish();
        k.hotbar[8] = new CItemStack(Material.GOLDEN_APPLE, 2).finish();

        addKit(k);
    }

    private void templar() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Eisenbrustplatte Schutz 1",
                "&2- 2 Werfbare Heiltränke",
                "&5- 14 Goldene Extraherzen"
        };

        k.permission = "kitpvp.donator";

        k.displayItem = new CItemStack(Material.GOLDEN_APPLE).setName("&6Templer").addLore(lore).finish();

        k.chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().finish();
        k.hotbar[8] = new CPotion(PotionType.INSTANT_HEAL, 1, true, false).toStack(2).finish();

        k.addEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 6));

        addKit(k);
    }

    private void mage() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
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

        k.permission = "kitpvp.donator";

        k.displayItem = new CItemStack(Material.POTION).setName("&6Magier").addLore(lore).finish();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.FUCHSIA).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().finish();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.FUCHSIA).makeUnbreakable().finish();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.FUCHSIA).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CPotion(PotionType.INSTANT_DAMAGE, 2, true, false).toStack().finish();
        k.hotbar[2] = new CPotion(PotionType.INSTANT_DAMAGE, 1, true, false).toStack(2).finish();

        k.hotbar[4] = new CPotion(PotionType.INSTANT_HEAL, 2, true, false).toStack(2).finish();
        k.hotbar[5] = new CPotion(PotionType.SPEED, 1, true, false).toStack().finish();
        k.hotbar[7] = new CPotion(PotionType.REGEN, 1, false, true).toStack().finish();
        k.hotbar[8] = new CPotion(PotionType.FIRE_RESISTANCE, 1, false, false).toStack().finish();


        k.addEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void rabbit() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Kettenrüstung",
                "&2- 1 Werfbarer Heilungstrank",
                "&5- Permanent Sprungkraft",
                "&5- Permanent Geschwindigkeit"
        };

        k.displayItem = new CItemStack(Material.RABBIT_FOOT).setName("&aHase").addLore(lore).finish();

        k.helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().finish();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().finish();
        k.legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().finish();
        k.boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CPotion(PotionType.INSTANT_HEAL, 1, true, false).toStack().finish();

        k.addEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        addKit(k);
    }

    private void witch() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Lederrüstung",
                "&2- 2 Werfbare Schwächetränke",
                "&2- Werfbarer Langsamkeitstrank",
                "&2- Werfbarer Gifttrank",
                "&2- Heilungstrank"
        };

        k.displayItem = new CItemStack(Material.SPIDER_EYE).addLore(lore).setName("&2Witch").finish();
        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.GRAY).makeUnbreakable().finish();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.GRAY).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addEnchantment(Enchantment.THORNS, 1).finish();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.GRAY).makeUnbreakable().finish();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GRAY).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CPotion(PotionType.WEAKNESS, 1, true, false).toStack(2).finish();
        k.hotbar[2] = new CPotion(PotionType.SLOWNESS, 1, true, false).toStack().finish();
        k.hotbar[3] = new CPotion(PotionType.POISON, 1, true, false).toStack().finish();
        k.hotbar[4] = new CPotion(PotionType.INSTANT_HEAL, 2).toStack().finish();

        addKit(k);
    }

    private void canada() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzfäleraxt",
                "&2- Rot Karriertes Hemd",
                "&2- Blaue Hose",
                "&2- Schwarze Arbeitsstiefel",
                "&2- Kanadischer Sirup"
        };

        k.displayItem = new CItemStack(Material.IRON_AXE).addLore(lore).setName("&cDer Kanadier").finish();

        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).finish();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLUE).makeUnbreakable().finish();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.IRON_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 1).makeUnbreakable().finish();
        k.hotbar[1] = new CPotion(PotionType.INSTANT_HEAL, 2).toStack().setName("&6Sirup").finish();

        addKit(k);
    }

    private void ghost() {
        Kit k = new Kit();

        k.permission = "kitpvp.donator";

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
                "&6Dieses Kit beinhaltet:",
                "&2- Holzchwert",
                "&2- Werfbarer Heilungstränke",
                "&5- *Geisterhaft*"
        };

        k.displayItem = new CItemStack(Material.GLASS_BOTTLE).addLore(lore).setName("Geist").finish();

        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().finish();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().finish();
        k.hotbar[1] = new CPotion(PotionType.INSTANT_HEAL, 1, true, false).toStack().finish();

        k.addEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
        
        addKit(k);
    }

    private void housewife() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Versace Highheels",
                "&2- Gucci-Top",
                "&2- Kochlöffel der Verdammnis",
                "&2- 2 Himbeersäfte",
                "&2- Karamellapfel"
        };

        k.displayItem = new CItemStack(Material.CAKE).addLore(lore).setName("&bDie Hausfrau").finish();

        k.boots = new CItemStack(Material.DIAMOND_BOOTS)
                .setName("&9Versace Highheels")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.THORNS, 2)
                .makeUnbreakable()
                .finish();

        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.FUCHSIA).setName("&dGucci-Top").finish();

        k.hotbar[0] = new CItemStack(Material.WOOD_SPADE)
                .makeUnbreakable()
                .setName("&cKochlöffel der Verdammnis")
                .addEnchantment(Enchantment.DAMAGE_ALL, 5)
                .addEnchantment(Enchantment.KNOCKBACK, 4)
                .finish();

        k.hotbar[1] = new CPotion(PotionType.INSTANT_HEAL, 2, false, false).toStack().setName("&dHimbeersaft").finish();
        k.hotbar[2] = new CItemStack(Material.GOLDEN_APPLE).makeUnbreakable().setName("&6Karamellapfel").finish();

        addKit(k);
    }

    private void luca() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&3Dies ist ein Kit für Donator",
                "&6Dieses Kit beinhaltet:",
                "&2- Rotzball",
                "&2- Rotztop",
                "&2- Rotzboots",
                "&2- Schleimbrühe"
        };

        k.displayItem = new CItemStack(Material.SLIME_BALL).addLore(lore).setName("&2Rotzi").finish();

        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.GREEN).setName("&2Rotz-Top").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).finish();

        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GREEN).setName("&2Verrotzte-Weiße-AirMax").addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).finish();

        k.hotbar[0] = new CItemStack(Material.SLIME_BALL)
                .setName("&2Popel der Schleimigkeit")
                .addEnchantment(Enchantment.DAMAGE_ALL, 6)
                .finish();

        k.hotbar[1] = new CPotion(PotionType.REGEN, 1, false, true).toStack(2).setName("&2Schleimbrühe").finish();

        k.permission = "kitpvp.donator";

        addKit(k);
    }

}
