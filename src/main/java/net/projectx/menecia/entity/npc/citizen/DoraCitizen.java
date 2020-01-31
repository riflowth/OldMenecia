package net.projectx.menecia.entity.npc.citizen;

import net.projectx.menecia.entity.Mob;
import net.projectx.menecia.entity.PassiveMob;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class DoraCitizen extends Mob implements PassiveMob {

    private static final EntityType entityType = EntityType.VILLAGER;
    private static final String[] possibleNames = new String[] {"John", "Roy"};
    private static final double defaultHealth = 20;
    private boolean hasSpawned = false;

    public DoraCitizen() {
        super(
                entityType,
                possibleNames[ThreadLocalRandom.current().nextInt(0, possibleNames.length)],
                defaultHealth
        );
    }

    @Override
    public void follow(Player player) {

    }

    @Override
    public void unfollow(Player player) {

    }
}
