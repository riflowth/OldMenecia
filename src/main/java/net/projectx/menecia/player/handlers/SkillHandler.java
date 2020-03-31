package net.projectx.menecia.player.handlers;

import net.projectx.menecia.player.skills.Skill;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class SkillHandler {
    Map<Integer, Skill> skillList = new HashMap<>();

    public void registerSkill(Skill targetSkill) {
        skillList.put(targetSkill.getSkillID(), targetSkill);
    }

    public void unregisterSkill(Skill targetSkill) {
        skillList.remove(targetSkill.getSkillID());
    }

    public Skill getSkill(int skillID) {
        return skillList.get(skillID);
    }

    public void activateSkill(Skill skill, Player activator) {
        skill.activateSkill(activator);
    }

    public void activateSkill(int skillID, Player activator) {
        getSkill(skillID).activateSkill(activator);
    }
}
