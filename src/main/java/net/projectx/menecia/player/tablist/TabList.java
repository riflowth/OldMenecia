package net.projectx.menecia.player.tablist;

import net.projectx.menecia.player.tablist.sections.TabListSection;
import net.projectx.menecia.player.tablist.sections.TabListSectionType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TabList {

    //TODO: Complete this system!

    private UUID ownerUuid;
    private Map<TabListSectionType, TabListSection> sectionMap = new HashMap<>();

    public TabList(Player player) {
        ownerUuid = player.getUniqueId();
    }

    public UUID getOwnerUuid() {
        return ownerUuid;
    }

    public void addSection(TabListSection section) {
        sectionMap.put(section.getSectionType(), section);
    }

    public TabListSection TabListSection(TabListSectionType type) {
        return sectionMap.get(type);
    }

}
