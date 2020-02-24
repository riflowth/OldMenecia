package net.projectx.menecia.player.events.admin.mobspawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.spawner.MobSpawnRate;
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

public class MobSpawnerSelectSpawnRateGUI implements GUI, MobSpawnerGUI {

    private static final int size = GUISize.THREE_ROW;
    private static final String title = "Which Spawn rate?";
    private final Inventory inventory = Bukkit.createInventory(this, size, title);
    private Menecia plugin;

    public MobSpawnerSelectSpawnRateGUI(Menecia plugin) {
        this.plugin = plugin;

        ItemStack itemStack = new ItemStack(Material.OAK_SAPLING);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Abundant");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(11, itemStack);

        itemStack = new ItemStack(Material.GRASS);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Common");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(12, itemStack);

        itemStack = new ItemStack(Material.DEAD_BUSH);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Rare");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(13, itemStack);

        itemStack = new ItemStack(Material.COBWEB);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Extremely Rare");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(14, itemStack);

        itemStack = new ItemStack(Material.WITHER_ROSE);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Mythical");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(15, itemStack);
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        MobSpawner mobSpawner = plugin.getManagers().getMobSpawnerSetUpManager().getSpawnerTemp(player);
        switch (slot){
            case 11:
                mobSpawner.setSpawnRate(MobSpawnRate.ABUNDANT);
                plugin.getManagers().getMobSpawnerSetUpManager().setState(player, MobSpawnerStepUpState.SELECT_MAXIMUM_AMOUNT);
                player.closeInventory();
                break;
            case 12:
                mobSpawner.setSpawnRate(MobSpawnRate.COMMON);
                plugin.getManagers().getMobSpawnerSetUpManager().setState(player, MobSpawnerStepUpState.SELECT_MAXIMUM_AMOUNT);
                player.closeInventory();
                break;
            case 13:
                mobSpawner.setSpawnRate(MobSpawnRate.RARE);
                plugin.getManagers().getMobSpawnerSetUpManager().setState(player, MobSpawnerStepUpState.SELECT_MAXIMUM_AMOUNT);
                player.closeInventory();
                break;
            case 14:
                mobSpawner.setSpawnRate(MobSpawnRate.EXTREMELY_RATE);
                plugin.getManagers().getMobSpawnerSetUpManager().setState(player, MobSpawnerStepUpState.SELECT_MAXIMUM_AMOUNT);
                player.closeInventory();
                break;
            case 15:
                mobSpawner.setSpawnRate(MobSpawnRate.MYTHICAL);
                plugin.getManagers().getMobSpawnerSetUpManager().setState(player, MobSpawnerStepUpState.SELECT_MAXIMUM_AMOUNT);
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
