package net.projectx.menecia.player.tablist;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import net.projectx.menecia.packets.ServerPlayerInfoPacket;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class TabListUtil {

    //TODO: Complete this system!

    public static void show(List<PlayerInfoData> playerInfoDataList, Player player) {
        ServerPlayerInfoPacket packet = new ServerPlayerInfoPacket();
        packet.setAction(EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        packet.setData(playerInfoDataList);
        packet.sendPacket(player);
    }

    public static void updateName(WrappedGameProfile gameProfile, String name) {
        ServerPlayerInfoPacket packet = new ServerPlayerInfoPacket();
        packet.setAction(EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
        PlayerInfoData clockInfoData = new PlayerInfoData(gameProfile, 0,
                EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText(name));
        packet.setData(Collections.singletonList(clockInfoData));
        packet.broadcastPacket();
    }

}
