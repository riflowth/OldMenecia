package net.projectx.menecia.player.events;

import net.projectx.menecia.Menecia;
import net.projectx.menecia.resources.utilities.Utils;
import net.projectx.menecia.skills.SkillElement;
import net.projectx.menecia.skills.SkillFormula;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerSkillEvent implements Listener {

    private Menecia plugin;
    private Map<UUID, SkillElement[]> playerUuidToSkillElements = new HashMap<>();

    public PlayerSkillEvent(Menecia plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerInputSkill(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            addSkillElement(player, SkillElement.LEFT_CLICK);
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (player.isSneaking()) {
                addSkillElement(player, SkillElement.SHIFT_RIGHT_CLICK);
            } else {
                addSkillElement(player, SkillElement.RIGHT_CLICK);
            }
        }

        if (isSkillCompleted(player)) completeSkill(player);
    }

    private void completeSkill(Player player) {
        SkillElement[] skillElements = playerUuidToSkillElements.get(player.getUniqueId());
        if (Arrays.equals(skillElements, SkillFormula.WEAPON_ABILITY)) {
            player.sendMessage("Used Weapon Ability!");
        } else if (Arrays.equals(skillElements, SkillFormula.MAIN_OFFENSIVE_SKILL)) {
            player.sendMessage("Used Main Offensive Skill!");
        } else if (Arrays.equals(skillElements, SkillFormula.SECONDARY_OFFENSIVE_SKILL)) {
            player.sendMessage("Used Secondary Offensive Skill!");
        } else if (Arrays.equals(skillElements, SkillFormula.MOVEMENT_SKILL)) {
            player.sendMessage("Used Movement Skill!");
        } else if (Arrays.equals(skillElements, SkillFormula.BUFF_SKILL)) {
            player.sendMessage("Used Buff Skill!");
        }
        clearSkillElement(player);
    }

    private boolean isSkillCompleted(Player player) {
        SkillElement[] skillElements = playerUuidToSkillElements.get(player.getUniqueId());
        return (skillElements[2] != null);
    }

    private void addSkillElement(Player player, SkillElement skillElement) {
        playerUuidToSkillElements.putIfAbsent(player.getUniqueId(), new SkillElement[3]);
        SkillElement[] skillElements = playerUuidToSkillElements.get(player.getUniqueId());
        int skillElementIndex = ((Utils.getLength(skillElements) - 1) < 0)
                ? 0 : Utils.getLength(skillElements);
        skillElements[skillElementIndex] = skillElement;
        playerUuidToSkillElements.put(player.getUniqueId(), skillElements);
    }

    public void clearSkillElement(Player player) {
        playerUuidToSkillElements.remove(player.getUniqueId());
    }

}
