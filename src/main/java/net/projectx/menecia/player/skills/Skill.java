package net.projectx.menecia.player.skills;

import org.bukkit.entity.Player;

public interface Skill {

    void activateSkill(Player activator);

    String getSkillName();

    int getSkillID();

    String getSkillDescription();
}
