package net.projectx.menecia.items.currency;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Currency {

    void drop(Location location);

    void give(Player player);

    int getAmount();

    ItemStack getItemStack();

}
