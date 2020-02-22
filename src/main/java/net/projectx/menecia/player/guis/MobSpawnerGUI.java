package net.projectx.menecia.player.guis;

import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.MobUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MobSpawnerGUI implements GUI {

    private static final int size = GUISize.SIX_ROW;
    private static final String title = "Which mob?";
    private final Inventory inventory = Bukkit.createInventory(this, size, title);

    public MobSpawnerGUI() {
        for (int i = 0; i < MobManager.getAllMobs().size(); i++) {
            System.out.println(i);
            Mob mob = MobManager.getAllMobs().get(i);
            ItemStack itemStack = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(MobUtil.getDisplayNameWithLevel(mob));
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(i, itemStack);
        }
    }

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
