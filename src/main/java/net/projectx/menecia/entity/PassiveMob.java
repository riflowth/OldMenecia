package net.projectx.menecia.entity;

import org.bukkit.entity.Player;

public interface PassiveMob {

    void follow(Player player);

    void unfollow(Player player);

}
