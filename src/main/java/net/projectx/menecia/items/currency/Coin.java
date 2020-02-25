package net.projectx.menecia.items.currency;

import net.projectx.menecia.resources.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Coin implements Currency {

    private static final String name = Utils.color("&eCoin");
    private static final Material material = Material.GOLD_NUGGET;
    private int amount;

    public Coin(int amount) {
        this.amount = amount;
    }

    @Override
    public void drop(Location location) {
        ItemStack itemStack = getItemStack();
        itemStack.setAmount(amount);
        location.getWorld().dropItemNaturally(location, itemStack);
    }

    @Override
    public void give(Player player) {
        int stackCount = (int) Math.ceil(amount / 64D);
        int currentEmptySize = (player.getInventory().getMaxStackSize() - player.getInventory().getSize());
        if (currentEmptySize >= stackCount) {
            ItemStack itemStack = getItemStack();
            itemStack.setAmount(amount);
            player.getInventory().addItem(itemStack);
        } else {
            player.sendMessage(Utils.color("&7&oYour bag is full, try to clear something."));
        }
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
