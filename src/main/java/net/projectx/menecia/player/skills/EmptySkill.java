package net.projectx.menecia.player.skills;

import org.bukkit.entity.Player;

public class EmptySkill implements Skill {
    @Override
    public void activateSkill(Player activator) {}

    @Override
    public String getSkillName() {
        return null;
    }

    @Override
    public int getSkillID() {
        return -1;
    }

    @Override
    public String getSkillDescription() {
        return null;
    }
}
