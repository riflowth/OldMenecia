package net.projectx.menecia.player.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public class MobSpawnerGUI implements GUI {

    private static final int size = GUISize.SIX_ROW;
    private static final String title = "Which mob do you want to create a spawner?";
    private final Inventory inventory = Bukkit.createInventory(this, size, title);

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {

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
