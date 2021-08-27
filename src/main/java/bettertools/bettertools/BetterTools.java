package bettertools.bettertools;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public final class BetterTools extends JavaPlugin {

    private static BetterTools plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(
                              "      \n" +
                              "      " + "§a###################################\n" +
                              "      " + " §a§l BBBBBBBBB         TTTTTTTTTTTTT\n" +
                              "      " + " §a§lBB      BB         TTTTTTTTTTTTT\n" +
                              "      " + " §a§lBB      BB               TT\n" +
                              "      " + " §a§lBB      BB               TT\n" +
                              "      " + " §a§lBB      BB               TT\n" +
                              "      " + " §a§lBBBBBBBBBB               TT\n" +
                              "      " + " §a§lBB        BB             TT\n" +
                              "      " + " §a§lBB        BB             TT\n" +
                              "      " + " §a§lBB        BB             TT\n" +
                              "      " + " §a§lBB        BB             TT\n" +
                              "      " + " §a§l BBBBBBBBB               TT\n" +
                              "      " + "\n" +
                              "      " + "§a###################################\n" +
                              "      " + "\n" +
                              "      " + "§a#########> §lBetterTools §r§a<#########\n" +
                              "      " + "\n" +
                              "      " + "§a-> Github: github.com/OFENROHR100/BetterTools");
        FileConfiguration config = BetterTools.getPlugin().getConfig();
        if (!config.contains("Recipe.Piratesword")) {
            config.set("Recipe.Piratesword", true);
            config.set("Recipe.Katanasword", true);
            config.set("Recipe.Triplebow", true);
            config.set("Recipe.Explosivepickaxe", true);
            config.set("Recipe.Knife", true);
            config.set("Recipe.Emeraldpickaxe", true);
            config.set("Recipe.Explosiveshovel", true);
            config.set("Knife.Damagewithoutchestplate", 2);
            config.set("Resourcepack", true);
            BetterTools.getPlugin().saveDefaultConfig();
        }
        if (config.get("Recipe.Piratesword") == "true") {
            Bukkit.addRecipe(pirateswordrecipe());
        }
        if (config.get("Recipe.Katanasword") == "true") {
            Bukkit.addRecipe(katanarecipe());
        }
        if (config.get("Recipe.Triplebow") == "true") {
            Bukkit.addRecipe(triplebowrecipe());
        }
        if (config.get("Recipe.Explosivepickaxe") == "true") {
            Bukkit.addRecipe(explosivepickaxe());
        }
        if (config.get("Recipe.Knife") == "true") {
            Bukkit.addRecipe(kniferecipe());
        }
        if (config.get("Recipe.Emeraldpickaxe") == "true") {
            Bukkit.addRecipe(emeraldrecipe());
        }
        if (config.get("Recipe.Explosiveshovel") == "true") {
            Bukkit.addRecipe(explosiveshovel());
        }
        getServer().getPluginManager().registerEvents(new usepiratesword(), this);
        getServer().getPluginManager().registerEvents(new usebow(), this);
        getServer().getPluginManager().registerEvents(new usekatana(), this);
        getServer().getPluginManager().registerEvents(new useexplosivepickaxe(), this);
        getServer().getPluginManager().registerEvents(new useknife(), this);
        getServer().getPluginManager().registerEvents(new useEmeraldpicksaxe(), this);
        getServer().getPluginManager().registerEvents(new useexplosiveshovel(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getCommand("Inventory").setExecutor(new InventoryCommand());
        getCommand("BetterToolsInventory").setExecutor(new InventoryCommand());
        CMP.setupCooldownP();
        CMK.setupCooldownK();
        CME.setupCooldownE();
    }

    @Override
    public void onDisable() { getServer().getConsoleSender().sendMessage("§c§lDisabling BetterTools."); }

    public ShapedRecipe pirateswordrecipe() {
        //Item
        ItemStack pirate_sword = new ItemStack(Material.GOLDEN_SWORD);
        pirate_sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        pirate_sword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 2);
        pirate_sword.addUnsafeEnchantment(Enchantment.IMPALING, 5);
        pirate_sword.addUnsafeEnchantment(Enchantment.DURABILITY, 40);
        pirate_sword.addUnsafeEnchantment(Enchantment.MENDING, 1);

        //Meta
        ItemMeta pirateMeta = pirate_sword.getItemMeta();
        pirateMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Pirate Sword");
        pirateMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A old but good pirate sword.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a pirate captain.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Seamen:",
                ChatColor.GRAY + "Gives you water breathing."));
        pirateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pirateMeta.setCustomModelData(1234567);
        pirate_sword.setItemMeta(pirateMeta);

        //Recipe
        ShapedRecipe pirate_recipe = new ShapedRecipe(new NamespacedKey(this, "pirate_sword"), pirate_sword);
        pirate_recipe.shape("DND", "SPS", "EHE");
        pirate_recipe.setIngredient('D', Material.TRIDENT);
        pirate_recipe.setIngredient('N', Material.NETHER_STAR);
        pirate_recipe.setIngredient('S', Material.SCUTE);
        pirate_recipe.setIngredient('P', Material.DIAMOND_SWORD);
        pirate_recipe.setIngredient('E', Material.NAUTILUS_SHELL);
        pirate_recipe.setIngredient('H', Material.HEART_OF_THE_SEA);


        return pirate_recipe;
    }

    public ShapedRecipe katanarecipe() {
        //Item
        ItemStack katana = new ItemStack(Material.DIAMOND_SWORD);
        katana.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        katana.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
        katana.addUnsafeEnchantment(Enchantment.IMPALING, 3);
        katana.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        katana.addUnsafeEnchantment(Enchantment.MENDING, 1);

        //Meta
        ItemMeta katanaMeta = katana.getItemMeta();
        katanaMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Katana");
        katanaMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very sharp katana.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a samurai.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Warrior:",
                ChatColor.GRAY + "Gives you resistance and slowness."));
        katanaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        katanaMeta.setCustomModelData(123456);
        katana.setItemMeta(katanaMeta);

        //Recipe
        ShapedRecipe katanarecipe = new ShapedRecipe(new NamespacedKey(this, "katana"), katana);
        katanarecipe.shape("EEE", "UPU", "EUE");
        katanarecipe.setIngredient('E', Material.IRON_BLOCK);
        katanarecipe.setIngredient('P', Material.DIAMOND_SWORD);
        katanarecipe.setIngredient('U', Material.FLINT);


        return katanarecipe;
    }
    public ShapedRecipe explosivepickaxe() {
        //Item
        ItemStack pickaxe = new ItemStack(Material.GOLDEN_SHOVEL);
        pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);


        //Meta
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Explosive Pickaxe");
        pickaxeMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very highly explosive pickaxe",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a Miner.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Multibreak (Stone):",
                ChatColor.GRAY + "Breaks stone blocks in 3x3 radius."));
        pickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pickaxeMeta.setCustomModelData(12345);
        pickaxe.setItemMeta(pickaxeMeta);

        //Recipe
        ShapedRecipe pickaxerecipe = new ShapedRecipe(new NamespacedKey(this, "pickaxe"), pickaxe);
        pickaxerecipe.shape("TPT", "DBD", "TPT");
        pickaxerecipe.setIngredient('P', Material.GUNPOWDER);
        pickaxerecipe.setIngredient('B', Material.IRON_PICKAXE);
        pickaxerecipe.setIngredient('D', Material.DIAMOND);
        pickaxerecipe.setIngredient('T', Material.TNT);


        return pickaxerecipe;
    }
    public ShapedRecipe triplebowrecipe() {
        //Item
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        //Meta
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Triple Shot Bow");
        bowMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very useful bow",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a Archer.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Triple Shot:",
                ChatColor.GRAY + "Shoots three arrows instead of one."));
        bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bowMeta.setCustomModelData(1234);
        bow.setItemMeta(bowMeta);

        //Recipe
        ShapedRecipe bowrecipe = new ShapedRecipe(new NamespacedKey(this, "bow"), bow);
        bowrecipe.shape("PPP", "BBB", "PPP");
        bowrecipe.setIngredient('P', Material.ARROW);
        bowrecipe.setIngredient('B', Material.BOW);


        return bowrecipe;
    }

    public ShapedRecipe kniferecipe() {
        //Item
        ItemStack knife = new ItemStack(Material.STONE_SWORD);
        knife.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);


        //Meta
        ItemMeta knifeMeta = knife.getItemMeta();
        FileConfiguration config = BetterTools.getPlugin().getConfig();
        if (!config.contains("Knife.Damagewithoutchestplate")) {
            config.set("Knife.Damagewithoutchestplate", 2);
            BetterTools.getPlugin().saveDefaultConfig();
        }
        Integer enemy = (Integer) config.get("Knife.Damagewithoutchestplate");
        knifeMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Knife");
        knifeMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very deadly knife.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a thief.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Stab:",
                ChatColor.GRAY + "Deals to " + enemy + " times more damage to player without a chestplate."));
        knifeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        knifeMeta.setCustomModelData(123);
        knife.setItemMeta(knifeMeta);

        //Recipe
        ShapedRecipe kniferecipe = new ShapedRecipe(new NamespacedKey(this, "knife"), knife);
        kniferecipe.shape("III", "IUI", "IPI");
        kniferecipe.setIngredient('I', Material.IRON_NUGGET);
        kniferecipe.setIngredient('P', Material.STICK);
        kniferecipe.setIngredient('U', Material.FLINT);


        return kniferecipe;
    }

    public ShapedRecipe emeraldrecipe() {
        //Item
        ItemStack emeraldpickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        emeraldpickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);


        //Meta
        ItemMeta emeraldpickaxeMeta = emeraldpickaxe.getItemMeta();
        emeraldpickaxeMeta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Emeraldpickaxe");
        emeraldpickaxeMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very fast pickaxe.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a rich merchant.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Superbreaker:",
                ChatColor.GRAY + "Lets you mine blocks really fast for 10 seconds."));
        emeraldpickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        emeraldpickaxeMeta.setCustomModelData(12);
        emeraldpickaxe.setItemMeta(emeraldpickaxeMeta);

        //Recipe
        ShapedRecipe emeraldpickaxerecipe = new ShapedRecipe(new NamespacedKey(this, "emeraldpickaxe"), emeraldpickaxe);
        emeraldpickaxerecipe.shape("EEE", "DUD", "DUD");
        emeraldpickaxerecipe.setIngredient('E', Material.EMERALD);
        emeraldpickaxerecipe.setIngredient('D', Material.DIAMOND);
        emeraldpickaxerecipe.setIngredient('U', Material.STICK);


        return emeraldpickaxerecipe;
    }
    public ShapedRecipe explosiveshovel() {
        //Item
        ItemStack shovel = new ItemStack(Material.GOLDEN_SHOVEL);
        shovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);


        //Meta
        ItemMeta shovelMeta = shovel.getItemMeta();
        shovelMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Explosive Shovel");
        shovelMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very highly explosive Shovel",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a digger.",
                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                "",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Multibreak (Dirt):",
                ChatColor.GRAY + "Breaks dirt blocks in 3x3 radius."));
        shovelMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shovelMeta.setCustomModelData(1);
        shovel.setItemMeta(shovelMeta);

        //Recipe
        ShapedRecipe shovelrecipe = new ShapedRecipe(new NamespacedKey(this, "shovel"), shovel);
        shovelrecipe.shape("TPT", "DBD", "TPT");
        shovelrecipe.setIngredient('P', Material.GUNPOWDER);
        shovelrecipe.setIngredient('B', Material.IRON_SHOVEL);
        shovelrecipe.setIngredient('D', Material.DIAMOND);
        shovelrecipe.setIngredient('T', Material.TNT);


        return shovelrecipe;
    }
    public class InventoryClick implements Listener {
        @EventHandler
        public void onInventoryClick(InventoryDragEvent event) {
            if (event.getInventory().equals("§a§lBetterTools Inventory")) {
                event.setCancelled(true);
            }
        }

        @EventHandler
        public void handleGuiClick(InventoryClickEvent event) {
            if(!(event.getWhoClicked() instanceof Player)) return;
            if(event.getView().getTitle().equals("§a§lBetterTools Inventory")) {
                event.setCancelled(true);
                if (event.isLeftClick() && event.getCurrentItem() != null) {
                switch (event.getCurrentItem().getType()) {
                    case GOLDEN_SWORD:
                        //Item
                        ItemStack pirate_sword = new ItemStack(Material.GOLDEN_SWORD);
                        pirate_sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
                        pirate_sword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 2);
                        pirate_sword.addUnsafeEnchantment(Enchantment.IMPALING, 5);
                        pirate_sword.addUnsafeEnchantment(Enchantment.DURABILITY, 40);
                        pirate_sword.addUnsafeEnchantment(Enchantment.MENDING, 1);

                        //Meta
                        ItemMeta pirateMeta = pirate_sword.getItemMeta();
                        pirateMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Pirate Sword");
                        pirateMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A old but good pirate sword.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a pirate captain.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Seamen:",
                                ChatColor.GRAY + "Gives you water breathing."));
                        pirateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        pirateMeta.setCustomModelData(1234567);
                        pirate_sword.setItemMeta(pirateMeta);
                        event.getWhoClicked().getInventory().addItem(pirate_sword);
                        break;
                    case DIAMOND_SWORD:
                        //Item
                        ItemStack katana = new ItemStack(Material.DIAMOND_SWORD);
                        katana.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
                        katana.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        katana.addUnsafeEnchantment(Enchantment.IMPALING, 3);
                        katana.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
                        katana.addUnsafeEnchantment(Enchantment.MENDING, 1);

                        //Meta
                        ItemMeta katanaMeta = katana.getItemMeta();
                        katanaMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Katana");
                        katanaMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very sharp katana.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a samurai.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Warrior:",
                                ChatColor.GRAY + "Gives you resistance and slowness."));
                        katanaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        katanaMeta.setCustomModelData(123456);
                        katana.setItemMeta(katanaMeta);
                        event.getWhoClicked().getInventory().addItem(katana);
                        break;
                    case GOLDEN_PICKAXE:
                        //Item
                        ItemStack pickaxe = new ItemStack(Material.GOLDEN_PICKAXE);
                        pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);


                        //Meta
                        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
                        pickaxeMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Explosive Pickaxe");
                        pickaxeMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very highly explosive pickaxe",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a Miner.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Multibreak (Stone):",
                                ChatColor.GRAY + "Breaks stone blocks in 3x3 radius."));
                        pickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        pickaxeMeta.setCustomModelData(12345);
                        pickaxe.setItemMeta(pickaxeMeta);
                        event.getWhoClicked().getInventory().addItem(pickaxe);
                        break;
                    case BOW:
                        //Item
                        ItemStack bow = new ItemStack(Material.BOW);
                        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
                        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

                        //Meta
                        ItemMeta bowMeta = bow.getItemMeta();
                        bowMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Triple Shot Bow");
                        bowMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very useful bow",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a Archer.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Triple Shot:",
                                ChatColor.GRAY + "Shoots three arrows instead of one."));
                        bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        bowMeta.setCustomModelData(12345);
                        bow.setItemMeta(bowMeta);
                        event.getWhoClicked().getInventory().addItem(bow);
                        break;
                    case STONE_SWORD:
                        //Item
                        ItemStack knife = new ItemStack(Material.STONE_SWORD);
                        knife.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);


                        //Meta
                        ItemMeta knifeMeta = knife.getItemMeta();
                        FileConfiguration config = BetterTools.getPlugin().getConfig();
                        if (!config.contains("Knife.Damagewithoutchestplate")) {
                            config.set("Knife.Damagewithoutchestplate", 2);
                            BetterTools.getPlugin().saveDefaultConfig();
                        }
                        Integer enemy = (Integer) config.get("Knife.Damagewithoutchestplate");
                        knifeMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Knife");
                        knifeMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very deadly knife.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a thief.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Stab:",
                                ChatColor.GRAY + "Deals to " + enemy + " times more damage to player without a chestplate."));
                        knifeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        knifeMeta.setCustomModelData(12345);
                        knife.setItemMeta(knifeMeta);
                        event.getWhoClicked().getInventory().addItem(knife);
                        break;
                    case NETHERITE_PICKAXE:
                        //Item
                        ItemStack emeraldpickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
                        emeraldpickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);


                        //Meta
                        ItemMeta emeraldpickaxeMeta = emeraldpickaxe.getItemMeta();
                        emeraldpickaxeMeta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Emeraldpickaxe");
                        emeraldpickaxeMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very fast pickaxe.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a rich merchant.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Superbreaker:",
                                ChatColor.GRAY + "Lets you mine blocks really fast for 10 seconds."));
                        emeraldpickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        emeraldpickaxeMeta.setCustomModelData(12345);
                        emeraldpickaxe.setItemMeta(emeraldpickaxeMeta);
                        event.getWhoClicked().getInventory().addItem(emeraldpickaxe);
                        break;
                    case GOLDEN_SHOVEL:
                        //Item
                        ItemStack shovel = new ItemStack(Material.GOLDEN_SHOVEL);
                        shovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);


                        //Meta
                        ItemMeta shovelMeta = shovel.getItemMeta();
                        shovelMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Explosive Shovel");
                        shovelMeta.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "A very highly explosive Shovel",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "Once used by a digger.",
                                ChatColor.AQUA + "" + ChatColor.ITALIC + "He said that ...",
                                "",
                                ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Multibreak (Dirt):",
                                ChatColor.GRAY + "Breaks dirt blocks in 3x3 radius."));
                        shovelMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        shovelMeta.setCustomModelData(12345);
                        shovel.setItemMeta(shovelMeta);
                        event.getWhoClicked().getInventory().addItem(shovel);
                        break;
                    case GRAY_STAINED_GLASS_PANE:
                        return;
                    }
                }
            }
        }
    }


    public class useEmeraldpicksaxe implements Listener {
        @EventHandler
        public void useemeraldpicksaxe(PlayerInteractEvent event) {
            if (event.getHand() == EquipmentSlot.OFF_HAND) {
                return;
            }
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Player player = event.getPlayer();
                try {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Superbreaker:"))) {
                        if(CME.checkCooldownE(event.getPlayer())){
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "You used that ability!"));
                            player.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(20 * 10, 3));
                            CME.setCooldownE(event.getPlayer(), 60);
                        }else {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "You have to wait" + " " + CME.getCooldownE(event.getPlayer()) + " " + "seconds to use the ability again!"));
                        }
                    }
                }catch (NullPointerException exception){}
            }
        }
    }

    public class useknife implements Listener {
        @EventHandler
        public void onDamage(EntityDamageByEntityEvent event) {
            if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            World world;
            Player player2;
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Stab:"))) {
                world = player.getWorld();
                player2 = (Player)event.getEntity();
                if (player2.getInventory().getChestplate() == null || player2.getInventory().getChestplate().getType() == Material.ELYTRA) {
                double dmg1 = event.getDamage();
                FileConfiguration config = BetterTools.getPlugin().getConfig();
                Integer multiplier = (Integer) config.get("Knife.Damagewithoutchestplate");
                event.setDamage(dmg1 * multiplier);
                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0F, 1.0F);
                return;
                    }
                }
            }
        }
    }


    public class useexplosivepickaxe implements Listener {
        BlockFace blockFace = null;

        @EventHandler
        public void onClick(PlayerInteractEvent event) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                blockFace = event.getBlockFace();
            }
        }

        @EventHandler
        public void onBreak(BlockBreakEvent event) {
            if (event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() == false) {
                return;
            }
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() == null || event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {
                return;
            }
            Block block = event.getBlock();
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Multibreak (Stone):") && event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null) {
                if (blockFace.equals(BlockFace.UP) || blockFace.equals(BlockFace.DOWN)) {
                    Block block1 = block.getRelative(BlockFace.EAST);
                    Block block2 = block.getRelative(BlockFace.WEST);
                    Block block3 = block.getRelative(BlockFace.NORTH);
                    Block block4 = block.getRelative(BlockFace.SOUTH);
                    Block block5 = block.getRelative(BlockFace.SOUTH_WEST);
                    Block block6 = block.getRelative(BlockFace.SOUTH_EAST);
                    Block block7 = block.getRelative(BlockFace.NORTH_WEST);
                    Block block8 = block.getRelative(BlockFace.NORTH_EAST);
                    if (block1.getType().equals(Material.STONE)) {
                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1 , 1);
                        block1.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block2.getType().equals(Material.STONE)) {
                        block2.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block3.getType().equals(Material.STONE)) {
                        block3.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block4.getType().equals(Material.STONE)) {
                        block4.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block5.getType().equals(Material.STONE)) {
                        block5.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block6.getType().equals(Material.STONE)) {
                        block6.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block7.getType().equals(Material.STONE)) {
                        block7.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block8.getType().equals(Material.STONE)) {
                        block8.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                }
                if (blockFace.equals(BlockFace.EAST) || blockFace.equals(BlockFace.WEST)) {
                    Block block1 = block.getRelative(BlockFace.UP);
                    Block block2 = block.getRelative(BlockFace.DOWN);
                    Block block3 = block.getRelative(BlockFace.NORTH);
                    Block block4 = block.getRelative(BlockFace.SOUTH);
                    Block block5 = block1.getRelative(BlockFace.NORTH);
                    Block block6 = block1.getRelative(BlockFace.SOUTH);
                    Block block7 = block2.getRelative(BlockFace.NORTH);
                    Block block8 = block2.getRelative(BlockFace.SOUTH);
                    if (block1.getType().equals(Material.STONE)) {
                        block1.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block2.getType().equals(Material.STONE)) {
                        block2.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block3.getType().equals(Material.STONE)) {
                        block3.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block4.getType().equals(Material.STONE)) {
                        block4.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block5.getType().equals(Material.STONE)) {
                        block5.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block6.getType().equals(Material.STONE)) {
                        block6.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block7.getType().equals(Material.STONE)) {
                        block7.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block8.getType().equals(Material.STONE)) {
                        block8.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                }
                if (blockFace.equals(BlockFace.NORTH) || blockFace.equals(BlockFace.SOUTH)) {
                    Block block1 = block.getRelative(BlockFace.UP);
                    Block block2 = block.getRelative(BlockFace.DOWN);
                    Block block3 = block.getRelative(BlockFace.EAST);
                    Block block4 = block.getRelative(BlockFace.WEST);
                    Block block5 = block1.getRelative(BlockFace.EAST);
                    Block block6 = block1.getRelative(BlockFace.WEST);
                    Block block7 = block2.getRelative(BlockFace.EAST);
                    Block block8 = block2.getRelative(BlockFace.WEST);
                    if (block1.getType().equals(Material.STONE)) {
                        block1.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block2.getType().equals(Material.STONE)) {
                        block2.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block3.getType().equals(Material.STONE)) {
                        block3.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block4.getType().equals(Material.STONE)) {
                        block4.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block5.getType().equals(Material.STONE)) {
                        block5.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block6.getType().equals(Material.STONE)) {
                        block6.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block7.getType().equals(Material.STONE)) {
                        block7.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                    if (block8.getType().equals(Material.STONE)) {
                        block8.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.STONE));
                        } else {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.COBBLESTONE));
                        }
                    }
                }
            }
        }
    }
    public class useexplosiveshovel implements Listener {
        BlockFace blockFace = null;

        @EventHandler
        public void onClick(PlayerInteractEvent event) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                blockFace = event.getBlockFace();
            }
        }

        @EventHandler
        public void onBreak(BlockBreakEvent event) {
            if (event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() == false) {
                return;
            }
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() == null || event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {
                return;
            }
            Block block = event.getBlock();
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Multibreak (Dirt):") && event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null) {
                if (blockFace.equals(BlockFace.UP) || blockFace.equals(BlockFace.DOWN)) {
                    Block block1 = block.getRelative(BlockFace.EAST);
                    Block block2 = block.getRelative(BlockFace.WEST);
                    Block block3 = block.getRelative(BlockFace.NORTH);
                    Block block4 = block.getRelative(BlockFace.SOUTH);
                    Block block5 = block.getRelative(BlockFace.SOUTH_WEST);
                    Block block6 = block.getRelative(BlockFace.SOUTH_EAST);
                    Block block7 = block.getRelative(BlockFace.NORTH_WEST);
                    Block block8 = block.getRelative(BlockFace.NORTH_EAST);
                    if (block1.getType().equals(Material.DIRT)) {
                        block1.setType(Material.AIR);
                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1 , 1);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block2.getType().equals(Material.DIRT)) {
                        block2.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block3.getType().equals(Material.DIRT)) {
                        block3.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block4.getType().equals(Material.DIRT)) {
                        block4.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block5.getType().equals(Material.DIRT)) {
                        block5.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block6.getType().equals(Material.DIRT)) {
                        block6.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block7.getType().equals(Material.DIRT)) {
                        block7.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block8.getType().equals(Material.DIRT)) {
                        block8.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                }
                if (blockFace.equals(BlockFace.EAST) || blockFace.equals(BlockFace.WEST)) {
                    Block block1 = block.getRelative(BlockFace.UP);
                    Block block2 = block.getRelative(BlockFace.DOWN);
                    Block block3 = block.getRelative(BlockFace.NORTH);
                    Block block4 = block.getRelative(BlockFace.SOUTH);
                    Block block5 = block1.getRelative(BlockFace.NORTH);
                    Block block6 = block1.getRelative(BlockFace.SOUTH);
                    Block block7 = block2.getRelative(BlockFace.NORTH);
                    Block block8 = block2.getRelative(BlockFace.SOUTH);
                    if (block1.getType().equals(Material.DIRT)) {
                        block1.setType(Material.AIR);
                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1 , 1);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block2.getType().equals(Material.DIRT)) {
                        block2.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block3.getType().equals(Material.DIRT)) {
                        block3.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block4.getType().equals(Material.DIRT)) {
                        block4.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block5.getType().equals(Material.DIRT)) {
                        block5.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block6.getType().equals(Material.DIRT)) {
                        block6.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block7.getType().equals(Material.DIRT)) {
                        block7.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block8.getType().equals(Material.DIRT)) {
                        block8.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                }
                if (blockFace.equals(BlockFace.NORTH) || blockFace.equals(BlockFace.SOUTH)) {
                    Block block1 = block.getRelative(BlockFace.UP);
                    Block block2 = block.getRelative(BlockFace.DOWN);
                    Block block3 = block.getRelative(BlockFace.EAST);
                    Block block4 = block.getRelative(BlockFace.WEST);
                    Block block5 = block1.getRelative(BlockFace.EAST);
                    Block block6 = block1.getRelative(BlockFace.WEST);
                    Block block7 = block2.getRelative(BlockFace.EAST);
                    Block block8 = block2.getRelative(BlockFace.WEST);
                    if (block1.getType().equals(Material.DIRT)) {
                        block1.setType(Material.AIR);
                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1 , 1);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block1.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block2.getType().equals(Material.DIRT)) {
                        block2.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block2.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block3.getType().equals(Material.DIRT)) {
                        block3.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block3.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block4.getType().equals(Material.DIRT)) {
                        block4.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block4.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block5.getType().equals(Material.DIRT)) {
                        block5.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block5.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block6.getType().equals(Material.DIRT)) {
                        block6.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block6.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block7.getType().equals(Material.DIRT)) {
                        block7.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block7.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                    if (block8.getType().equals(Material.DIRT)) {
                        block8.setType(Material.AIR);
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.DIRT));
                        } else {
                            block.getWorld().dropItemNaturally(block8.getLocation(), new ItemStack(Material.DIRT));
                        }
                    }
                }
            }
        }
    }

    public class usebow implements Listener {
        @EventHandler
        public void usebow(EntityShootBowEvent event) {
            if (event.getEntity() instanceof Player) {
                if (event.getProjectile() instanceof Arrow) {
                    if (event.getBow() != null && event.getBow().getItemMeta() != null && event.getBow().getItemMeta().getLore() != null && event.getBow().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Triple Shot:")) {
                        Arrow arrow = (Arrow) event.getProjectile();

                        Arrow arrow1 = event.getEntity().getWorld().spawn(event.getEntity().getEyeLocation(), Arrow.class);
                        arrow1.setDamage(arrow.getDamage());
                        arrow1.setShooter(event.getEntity());
                        arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(30)));

                        Arrow arrow2 = event.getEntity().getWorld().spawn(event.getEntity().getEyeLocation(), Arrow.class);
                        arrow2.setDamage(arrow.getDamage());
                        arrow2.setShooter(event.getEntity());
                        arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-30)));
                    }
                }
            }
        }
    }

    public class usekatana implements Listener {
        @EventHandler
        public void usekatana(PlayerInteractEvent event) {
            if (event.getHand() == EquipmentSlot.OFF_HAND) {
                return;
            }
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Player player = event.getPlayer();
                try {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Warrior:"))) {
                        if(CMK.checkCooldownK(event.getPlayer())){
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "You used that ability!"));
                            player.addPotionEffect(PotionEffectType.SLOW.createEffect(20 * 10, 1));
                            player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20 * 10, 1));
                            player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(20 * 10, 1));
                            CMK.setCooldownK(event.getPlayer(), 60);
                        }else {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "You have to wait" + " " + CMK.getCooldownK(event.getPlayer()) + " " + "seconds to use the ability again!"));
                        }
                    }
                }catch (NullPointerException exception){}
            }
        }
    }


    public class usepiratesword implements Listener {
        @EventHandler
        public void usepiratesword(PlayerInteractEvent event) {
            if (event.getHand() == EquipmentSlot.OFF_HAND) {
                return;
            }
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Player player = event.getPlayer();
                try {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore() && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ability: Seamen:")) {
                        if(CMP.checkCooldownP(event.getPlayer())){
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "You used that ability!"));
                            player.addPotionEffect(PotionEffectType.SPEED.createEffect(20 * 10, 1));
                            player.addPotionEffect(PotionEffectType.WATER_BREATHING.createEffect(20 * 10, 1));
                            player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(20 * 10, 1));
                            CMP.setCooldownP(event.getPlayer(), 60);
                        }else {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "You have to wait" + " " + CMP.getCooldownP(event.getPlayer()) + " " + "seconds to use the ability again!"));
                        }
                    }
                }catch (NullPointerException exception){}
            }
        }
    }
    public static BetterTools getPlugin() {
        return plugin;
    }
}

