package net.projectx.menecia.player.tablist.sections;

import com.comphenix.protocol.wrappers.EnumWrappers;
import net.projectx.menecia.player.tablist.TabListUtil;
import net.projectx.menecia.resources.SkinData;
import net.projectx.menecia.npcs.NPC;
import net.projectx.menecia.npcs.NPCBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FriendTabList implements TabListSection {

    //TODO: Complete this system!

    private static final String title = "&fFriends";
    private static final ChatColor onlineColor = ChatColor.GREEN;
    private static final ChatColor offlineColor = ChatColor.GRAY;
    private static final int[] positions = {1, 20};
    private int counter = 0;
    private Map<Integer, UUID> friendList = new HashMap<>();

    @Override
    public TabListSectionType getSectionType() {
        return TabListSectionType.FRIEND;
    }

    @Override
    public void addTitle(Player player) {
        NPC header = new NPCBuilder()
                .setGameProfile(" " + positions[0])
                .setDisplayName(title)
                .setGameMode(EnumWrappers.NativeGameMode.NOT_SET)
                .setLatency(0)
                .setSkin(SkinData.FRIEND_VALUE, SkinData.FRIEND_SIGNATURE)
                .build();
        TabListUtil.show(Collections.singletonList(header.getPlayerInfoData()), player);
    }

    public void addFriend(Player player, Player friend) {
        if (friendList.isEmpty()) {
            addTitle(player);
            counter++;
        }
        counter++;
        friendList.put(counter, friend.getUniqueId());

        NPC header = new NPCBuilder()
                .setGameProfile(" " + positions[0])
                .setDisplayName(title)
                .setGameMode(EnumWrappers.NativeGameMode.NOT_SET)
                .setLatency(0)
                .setSkin(SkinData.FRIEND_VALUE, SkinData.FRIEND_SIGNATURE)
                .build();
        TabListUtil.show(Collections.singletonList(header.getPlayerInfoData()), player);

        //TODO: Check target friend!
    }

    public void showOnline() {

    }

    public void showOffline() {

    }

}
