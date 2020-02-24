package net.projectx.menecia.player.events.admin.mobspawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.spawner.MobSpawner;
import net.projectx.menecia.player.guis.GUI;
import net.projectx.menecia.player.guis.GUISize;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MobSpawnerSelectAreaGUI implements GUI, MobSpawnerGUI {

    private static final int size = GUISize.THREE_ROW;
    private static final String title = "How much area";
    private final Inventory inventory = Bukkit.createInventory(this, size, title);
    private Menecia plugin;

    public MobSpawnerSelectAreaGUI(Menecia plugin) {
        this.plugin = plugin;

        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("1x1");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(11, itemStack);

        itemStack = new ItemStack(Material.YELLOW_WOOL);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("2x2");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(12, itemStack);

        itemStack = new ItemStack(Material.ORANGE_WOOL);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("5x5");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(13, itemStack);

        itemStack = new ItemStack(Material.RED_WOOL);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("10x10");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(14, itemStack);

        itemStack = new ItemStack(Material.BLACK_WOOL);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("15x15");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(15, itemStack);
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        MobSpawner mobSpawner = plugin.getManagers().getMobSpawnerSetUpManager().getSpawnerTemp(player);
        switch (slot){
            case 11:
                plugin.getManagers().getMobSpawnerSetUpManager().success(player);
                player.closeInventory();
                break;
            case 12:
                mobSpawner.getSpawningArea().expand(1);
                plugin.getManagers().getMobSpawnerSetUpManager().success(player);
                player.closeInventory();
                break;
            case 13:
                mobSpawner.getSpawningArea().expand(4);
                plugin.getManagers().getMobSpawnerSetUpManager().success(player);
                player.closeInventory();
                break;
            case 14:
                mobSpawner.getSpawningArea().expand(9);
                plugin.getManagers().getMobSpawnerSetUpManager().success(player);
                player.closeInventory();
                break;
            case 15:
                mobSpawner.getSpawningArea().expand(14);
                plugin.getManagers().getMobSpawnerSetUpManager().success(player);
                player.closeInventory();
                break;
        }
    }

    @Override
    public void onOpen(Player player) {

    }

    @Override
    public void onClose(Player player) {

    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
