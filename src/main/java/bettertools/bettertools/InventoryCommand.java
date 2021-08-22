package bettertools.bettertools;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("BetterTools >>" + "You need to ba player for that!");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("Inventory")) {
            if (player.hasPermission("bettertools.inventory")) {
                Inventory inventory = Bukkit.createInventory(player, 9 * 3, "§a§lBetterTools Inventory");
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
                emeraldpickaxeMeta.setCustomModelData(12345);
                emeraldpickaxe.setItemMeta(emeraldpickaxeMeta);
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

                inventory.setItem(0, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(1, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(2, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(3, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(4, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(5, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(6, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(7, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(8, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(10, new ItemStack(pirate_sword));
                inventory.setItem(11, new ItemStack(katana));
                inventory.setItem(12, new ItemStack(pickaxe));
                inventory.setItem(13, new ItemStack(bow));
                inventory.setItem(14, new ItemStack(knife));
                inventory.setItem(15, new ItemStack(emeraldpickaxe));
                inventory.setItem(16, new ItemStack(shovel));
                inventory.setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(18, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(19, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(20, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(21, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(22, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(23, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(24, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(25, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                inventory.setItem(26, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));


                player.openInventory(inventory);

            } else {
                player.sendMessage("§a BetterTools >>" + "§cYou dont have the permission: bettertools.inventory");
            }
        }
        return true;
    }
}
