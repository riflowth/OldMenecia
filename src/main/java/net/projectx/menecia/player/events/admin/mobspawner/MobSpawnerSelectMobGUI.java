package net.projectx.menecia.player.events.admin.mobspawner;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobManager;
import net.projectx.menecia.mobs.MobUtil;
import net.projectx.menecia.player.guis.GUI;
import net.projectx.menecia.player.guis.GUISize;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class MobSpawnerSelectMobGUI implements GUI, MobSpawnerGUI {

    private static final int size = GUISize.SIX_ROW;
    private static final String title = "Which mob?";
    private final Inventory inventory = Bukkit.createInventory(this, size, title);
    private Menecia plugin;

    public MobSpawnerSelectMobGUI(Menecia plugin) {
        this.plugin = plugin;
        for (int i = 0; i < MobManager.getAllMobs().size(); i++) {
            Mob mob = MobManager.getAllMobs().get(i);
            ItemStack itemStack = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(Collections.singletonList(String.valueOf(mob.getId())));
            itemMeta.setDisplayName(MobUtil.getDisplayNameWithLevel(mob));
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(i, itemStack);
        }
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        ItemStack clickedItem = player.getOpenInventory().getItem(slot);
        if ((clickedItem != null) && (clickedItem.getLore() != null)) {
            Mob mob = MobManager.getMob(Integer.parseInt(clickedItem.getLore().get(0)));
            if (mob != null) {
                plugin.getManagers().getMobSpawnerSetUpManager().getSpawnerTemp(player).setMob(mob);
                plugin.getManagers().getMobSpawnerSetUpManager().setState(player, MobSpawnerStepUpState.SELECT_SPAWN_RATE);
                player.closeInventory();
            }
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
