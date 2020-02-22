package net.projectx.menecia.player.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;

public interface GUI extends InventoryHolder {

    void onClick(Player player, int slot, ClickType clickType);

    void onOpen(Player player);

    void onClose(Player player);

}
