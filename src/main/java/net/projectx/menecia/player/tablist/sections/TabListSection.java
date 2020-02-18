package net.projectx.menecia.player.tablist.sections;

import org.bukkit.entity.Player;

public interface TabListSection {

    TabListSectionType getSectionType();

    void addTitle(Player player);

}
