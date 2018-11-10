package tk.complexicon.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
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
        barbar();
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
        soup();
        templar();
        mage();
        supporter();
        oneHit();
        skeleton();

        ghost();
        ender();

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

        k.displayItem = new CItemStack(Material.DIAMOND_SWORD).setName("&9Schwertkämpfer").addLore(lore).build();

        k.helm = new CItemStack(Material.IRON_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.IRON_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CItemStack(Material.IRON_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.IRON_BOOTS).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.DIAMOND_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CItemStack(Material.GOLDEN_APPLE, 2).build();

        addKit(k);

    }

    private void archer() {
        Kit k = new Kit();

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzschwert Schärfe 1",
                "&2- Bogen Stärke 1, Schlag 1, Unendlichkeit",
                "&2- Werfbarer Trank der Heilung",
                "&2- Lederkappe",
                "&2- Kettenbrustpanzer",
                "&2- Lederhose",
                "&2- Lederschuhe",
                "&5- Permanent Geschwindigkeit 1"
        };

        k.displayItem = new CItemStack(Material.BOW).setName("&2Archer").addLore(lore).build();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.GREEN).makeUnbreakable().addLore(lore).build();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.GREEN).makeUnbreakable().build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GREEN).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable()
                .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                .addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                .addEnchantment(Enchantment.ARROW_KNOCKBACK, 1).build();
        k.hotbar[7] = new CPotion().addInstantEffect(true).splash().build();
        k.hotbar[8] = new CItemStack(Material.ARROW).setName("&6Legendärer Pfeil").build();

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

        k.displayItem = new CItemStack(Material.FIREBALL).setName("&6Pyro").addLore(lore).build();

        k.helm = new CItemStack(Material.GOLD_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.GOLD_CHESTPLATE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build();
        k.legs = new CItemStack(Material.GOLD_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.GOLD_BOOTS).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.GOLD_SWORD).makeUnbreakable().addEnchantment(Enchantment.FIRE_ASPECT, 1).setName("&6Flammenschwert").build();
        k.hotbar[1] = new CItemStack(Material.BOW).makeUnbreakable().addEnchantment(Enchantment.ARROW_FIRE, 1).setName("&6Flammenbogen").build();
        k.hotbar[7] = new CItemStack(Material.GOLDEN_APPLE, 2).build();
        k.hotbar[8] = new CItemStack(Material.ARROW, 32).build();


        k.addEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void tank() {
        Kit k = new Kit();

        k.price = 500;
        k.permission = "kitpvp.tank";

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Diamantrüstung",
                "&c- Permanent Langsamkeit 2"
        };

        k.displayItem = new CItemStack(Material.DIAMOND_CHESTPLATE).setName("&bTank").addLore(lore).build();

        k.helm = new CItemStack(Material.DIAMOND_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.DIAMOND_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CItemStack(Material.DIAMOND_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.DIAMOND_BOOTS).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();

        k.addEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));

        addKit(k);
    }

    private void berserk() {
        Kit k = new Kit();

        k.permission = "kitpvp.berserk";
        k.price = 5500;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Lederjacke",
                "&5- Permanent Stärke 1"
        };

        k.displayItem = new CItemStack(Material.IRON_SWORD).setName("&4Berserker").addLore(lore).build();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();

        k.addEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void snowman() {
        Kit k = new Kit();

        k.permission = "kitpvp.snowman";
        k.price = 5000;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert Schärfe 1",
                "&2- 64 Schneebälle",
                "&2- Komplette Lederrüstung",
                "&5- Schneebälle Verlangsamen den Getroffenen",
                "&5- Schneebälle Heilen beim Treffen Für Extrem Kurze Zeit",
                "&5- Permanent Geschwindigkeit 1"

        };

        k.displayItem = new CItemStack(Material.SNOW_BALL).setName("&7Schneemann").addLore(lore).build();
        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.WHITE).makeUnbreakable().build();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.WHITE).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE).makeUnbreakable().build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.WHITE).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().addEnchantment(Enchantment.DAMAGE_ALL, 1).build();
        k.hotbar[1] = new CItemStack(Material.SNOW_BALL, 64).build();

        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void ender() {
        Kit k = new Kit();

        k.permission = "kitpvp.ender";
        k.buyable = false;

        String[] lore = new String[]{
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

        k.displayItem = new CItemStack(Material.ENDER_PEARL).setName("&3Ender").addLore(lore).build();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.BLACK).makeUnbreakable().build();
        k.chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1).makeUnbreakable().build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLACK).makeUnbreakable().build();
        k.boots = new CItemStack(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_FALL, 10).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CItemStack(Material.ENDER_PEARL, 16).build();
        k.hotbar[8] = new CItemStack(Material.GOLDEN_APPLE, 2).build();

        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void soup() {
        Kit k = new Kit();

        k.permission = "kitpvp.soup";
        k.price = 3500;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Kettenrüstung",
                "&2- 8 Zaubersuppen",
                "&5- Heilung beim nutzen der Suppen"
        };

        k.displayItem = new CItemStack(Material.MUSHROOM_SOUP).setName("&eSoup").addLore(lore).build();

        k.helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        k.multiStack(new CItemStack(Material.MUSHROOM_SOUP).setName("&eZaubersuppe").build(), 8);

        addKit(k);
    }

    private void rod() {
        Kit k = new Kit();

        k.permission = "kitpvp.rod";
        k.price = 1250;

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

        k.displayItem = new CItemStack(Material.FISHING_ROD).setName("&bAngler").addLore(lore).build();

        k.helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.LEATHER_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CItemStack(Material.FISHING_ROD).makeUnbreakable().build();
        k.hotbar[8] = new CItemStack(Material.GOLDEN_APPLE, 2).build();

        addKit(k);
    }

    private void templar() {
        Kit k = new Kit();

        k.permission = "kitpvp.templar";
        k.price = 3750;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Eisenschwert",
                "&2- Eisenbrustplatte Schutz 1",
                "&2- 2 Werfbare Heiltränke",
                "&5- 14 Goldene Extraherzen"
        };

        k.displayItem = new CItemStack(Material.GOLDEN_APPLE).setName("&6Templer").addLore(lore).build();

        k.chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.IRON_SWORD).makeUnbreakable().build();
        k.hotbar[8] = new CPotion().addInstantEffect(true).splash().setAmt(2).build();

        k.addEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 6));

        addKit(k);
    }

    private void mage() {
        Kit k = new Kit();

        k.permission = "kitpvp.mage";
        k.price = 4500;

        String[] lore = new String[]{
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

        k.displayItem = new CPotion().setType(PotionType.NIGHT_VISION).hideFlag(ItemFlag.HIDE_POTION_EFFECTS).setName("&6Magier").addLore(lore).build();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.FUCHSIA).makeUnbreakable().build();
        k.chest = new CItemStack(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).makeUnbreakable().build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.FUCHSIA).makeUnbreakable().build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.FUCHSIA).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addInstantEffect(false, 1).splash().build();
        k.hotbar[2] = new CPotion().addInstantEffect(false).splash().setAmt(2).build();

        k.hotbar[4] = new CPotion().addInstantEffect(true).splash().setAmt(2).build();
        k.hotbar[5] = new CPotion().addPotionEffect(PotionEffectType.SPEED, 1, 60).setType(PotionType.SPEED).build();
        k.hotbar[7] = new CPotion().addPotionEffect(PotionEffectType.REGENERATION, 30).setType(PotionType.REGEN).build();
        k.hotbar[8] = new CPotion().addPotionEffect(PotionEffectType.FIRE_RESISTANCE, 120).setType(PotionType.FIRE_RESISTANCE).build();


        k.addEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));

        addKit(k);
    }

    private void rabbit() {
        Kit k = new Kit();

        k.permission = "kitpvp.rabbit";
        k.price = 2650;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Kettenrüstung",
                "&2- Federfall X Schuhe",
                "&2- 1 Werfbarer Heilungstrank",
                "&5- Permanent Sprungkraft",
                "&5- Permanent Geschwindigkeit"
        };

        k.displayItem = new CItemStack(Material.RABBIT_FOOT).setName("&aHase").addLore(lore).build();

        k.helm = new CItemStack(Material.CHAINMAIL_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CItemStack(Material.CHAINMAIL_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.CHAINMAIL_BOOTS).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_FALL, 10).build();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addInstantEffect(true, 1).splash().build();

        k.addEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        addKit(k);
    }

    private void witch() {
        Kit k = new Kit();

        k.permission = "kitpvp.witch";
        k.price = 3250;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinschwert",
                "&2- Volle Lederrüstung",
                "&2- 2 Werfbare Schwächetränke",
                "&2- Werfbarer Langsamkeitstrank",
                "&2- Werfbarer Gifttrank",
                "&2- Heilungstrank"
        };

        k.displayItem = new CItemStack(Material.SPIDER_EYE).addLore(lore).setName("&2Witch").build();
        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.GRAY).makeUnbreakable().build();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.GRAY).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addEnchantment(Enchantment.THORNS, 1).build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.GRAY).makeUnbreakable().build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.GRAY).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.STONE_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addPotionEffect(PotionEffectType.WEAKNESS, 120).setType(PotionType.WEAKNESS).splash().setAmt(2).build();
        k.hotbar[2] = new CPotion().addPotionEffect(PotionEffectType.SLOW, 30).setType(PotionType.SLOWNESS).splash().build();
        k.hotbar[3] = new CPotion().addPotionEffect(PotionEffectType.POISON, 30).setType(PotionType.POISON).splash().build();
        k.hotbar[4] = new CPotion().addInstantEffect(true, 1).build();

        addKit(k);
    }

    private void canada() {
        Kit k = new Kit();

        k.permission = "kitpvp.canada";
        k.price = 3000;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzfäleraxt",
                "&2- Rot Karriertes Hemd",
                "&2- Blaue Hose",
                "&2- Schwarze Arbeitsstiefel",
                "&2- Kanadischer Sirup"
        };

        k.displayItem = new CItemStack(Material.IRON_AXE).addLore(lore).setName("&cDer Kanadier").build();

        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.RED).makeUnbreakable().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.BLUE).makeUnbreakable().build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.IRON_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 1).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addInstantEffect(true).setName("&6Sirup").build();

        addKit(k);
    }

    private void ghost() {
        Kit k = new Kit();

        k.permission = "kitpvp.ghost";
        k.buyable = false;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Holzchwert",
                "&2- Werfbarer Heilungstränke",
                "&5- *Geisterhaft*"
        };

        k.displayItem = new CItemStack(Material.GLASS_BOTTLE).addLore(lore).setName("Geist").build();

        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.BLACK).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addInstantEffect(true, 1).splash().setAmt(2).build();

        k.addEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
        
        addKit(k);
    }

    private void housewife() {
        Kit k = new Kit();

        k.permission = "kitpvp.housewife";
        k.price = 2150;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Versace Highheels",
                "&2- Gucci-Top",
                "&2- Kochlöffel der Verdammnis",
                "&2- 2 Himbeersäfte",
                "&2- Karamellapfel"
        };

        k.displayItem = new CItemStack(Material.CAKE).addLore(lore).setName("&bDie Hausfrau").build();

        k.boots = new CItemStack(Material.DIAMOND_BOOTS)
                .setName("&9Versace Highheels")
                .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7)
                .addEnchantment(Enchantment.THORNS, 2)
                .makeUnbreakable()
                .build();

        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.FUCHSIA).makeUnbreakable().setName("&dGucci-Top").build();

        k.hotbar[0] = new CItemStack(Material.WOOD_SPADE)
                .makeUnbreakable()
                .setName("&cKochlöffel der Verdammnis")
                .addEnchantment(Enchantment.DAMAGE_ALL, 4)
                .addEnchantment(Enchantment.KNOCKBACK, 4)
                .build();

        k.hotbar[1] = new CPotion().addInstantEffect(true, 1).setName("&dHimbeersaft").build();
        k.hotbar[2] = new CItemStack(Material.GOLDEN_APPLE).makeUnbreakable().setName("&6Karamellapfel").build();

        addKit(k);
    }

    private void barbar() {
        Kit k = new Kit();

        k.permission = "kitpvp.barbar";
        k.price = 2600;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Steinaxt",
                "&2- Regenerationstrank Level 2",
                "&2- Eisenhelm",
                "&2- Kettenbrust",
                "&2- Lederhose",
                "&2- Lederschuhe"
        };

        k.displayItem = new CItemStack(Material.STONE_AXE).addLore(lore).setName("&eBarbar").build();
        k.helm = new CItemStack(Material.IRON_HELMET).makeUnbreakable().build();
        k.chest = new CItemStack(Material.CHAINMAIL_CHESTPLATE).makeUnbreakable().build();
        k.legs = new CItemStack(Material.LEATHER_LEGGINGS).makeUnbreakable().build();
        k.boots = new CItemStack(Material.LEATHER_BOOTS).makeUnbreakable().build();
        k.hotbar[0] = new CItemStack(Material.STONE_AXE).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addPotionEffect(PotionEffectType.REGENERATION, 1, 20).setType(PotionType.REGEN).build();


        addKit(k);
    }

    private void supporter() {
        Kit k = new Kit();

        k.permission = "kitpvp.supporter";
        k.price = 2250;

        k.maxHealth = 30;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Goldbrustplatte Schutz 3",
                "&2- Holzschwert Rückstoß 1",
                "&2- 5x Werfbare Direktheilung 2",
                "&2- 3x Werfbarer Resistenztrank",
                "&2- 2x Werfbarer Schildtrank",
                "&2- 2x Werfbare Regeneration 2",
                "&2- 3x Werfbare Regeneration",
                "&2- Kräfties Gebräu der Hexenmutter",
                "&5- Permanent 4 Extraherzen",
                "&c- Permanent Schwäche 2"
        };

        k.displayItem = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.RED).hideFlag(ItemFlag.HIDE_ATTRIBUTES).addLore(lore).setName("&cSupporter").build();

        k.helm = new CLeatherArmor(Material.LEATHER_HELMET).color(Color.RED).makeUnbreakable().build();
        k.chest = new CItemStack(Material.GOLD_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).makeUnbreakable().build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE).makeUnbreakable().build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.RED).makeUnbreakable().build();

        k.hotbar[0] = new CItemStack(Material.WOOD_SWORD).addEnchantment(Enchantment.KNOCKBACK, 1).makeUnbreakable().build();
        k.hotbar[1] = new CPotion().addInstantEffect(true, 1).splash().setAmt(5).build();
        k.hotbar[2] = new CPotion().addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90).setType(PotionType.NIGHT_VISION).splash().setAmt(3).setName("&1Resistenztrank").build();
        k.hotbar[3] = new CPotion().addPotionEffect(PotionEffectType.ABSORPTION, 2, 90).splash().setType(PotionType.FIRE_RESISTANCE).setAmt(2).setName("&6Schildtrank").build();
        k.hotbar[4] = new CPotion().addPotionEffect(PotionEffectType.REGENERATION, 1, 30).splash().setType(PotionType.REGEN).setAmt(2).build();
        k.hotbar[5] = new CPotion().addPotionEffect(PotionEffectType.REGENERATION, 0, 60).splash().setType(PotionType.REGEN).setAmt(3).build();
        k.hotbar[8] = new CPotion()
                .addPotionEffect(PotionEffectType.FIRE_RESISTANCE, 36)
                .addInstantEffect(true, 2)
                .addPotionEffect(PotionEffectType.REGENERATION, 1, 44)
                .addPotionEffect(PotionEffectType.ABSORPTION, 25)
                .addPotionEffect(PotionEffectType.BLINDNESS, 3)
                .addPotionEffect(PotionEffectType.CONFUSION, 2)
                .setType(PotionType.JUMP)
                .setName("&2Kräftiges Gebräu der Hexenmutter").build();

        k.effects.add(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 2));

        addKit(k);
    }

    public void oneHit(){
        Kit k = new Kit();

        k.price = 15000;
        k.permission = "kitpvp.onehit";

        k.maxHealth = 2;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Töten oder Getötet Werden ;)"
        };

        k.displayItem = new CItemStack(Material.SPECKLED_MELON).setName("&4Onehit").addLore(lore).build();

        k.hotbar[0] = new CItemStack(Material.GOLD_SWORD).setName("&6Dämmerbrecher").addEnchantment(Enchantment.DAMAGE_ALL, 10).build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.RED).addEnchantment(Enchantment.PROTECTION_FALL, 10).makeUnbreakable().build();

        k.addEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 100));

        addKit(k);
    }

    public void skeleton(){
        Kit k = new Kit();

        k.price = 3000;
        k.permission = "kitpvp.skeleton";

        k.maxHealth = 10;

        String[] lore = new String[]{
                "&6Dieses Kit beinhaltet:",
                "&2- Knochenkeule",
                "&2- Knochenweiße Lederrüstung",
                "&5- Permanent Resistenz und Schnelligkeit",
                "&c- Permanent 5 Herzen"
        };

        k.displayItem = new CItemStack(Material.BONE).setName("&7Skelett").addLore(lore).build();

        k.hotbar[0] = new CItemStack(Material.BONE).setName("&7Knochenkeule").addEnchantment(Enchantment.DAMAGE_ALL, 5).build();
        k.boots = new CLeatherArmor(Material.LEATHER_BOOTS).color(Color.WHITE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).makeUnbreakable().build();
        k.legs = new CLeatherArmor(Material.LEATHER_LEGGINGS).color(Color.WHITE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).makeUnbreakable().build();
        k.chest = new CLeatherArmor(Material.LEATHER_CHESTPLATE).color(Color.WHITE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).makeUnbreakable().build();
        k.helm = new CItemStack(Material.SKULL).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).makeUnbreakable().build();

        k.addEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 2));
        k.addEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
        k.addEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        addKit(k);
    }

}
