package net.projectx.menecia.mobs.events;

import net.projectx.menecia.items.currency.Coin;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.MobUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.concurrent.ThreadLocalRandom;

public class MobDeathEvent implements Listener {

    @EventHandler
    private void onEvent(EntityDeathEvent event) {
        if (MobUtil.isMob(event.getEntity())) {
            Mob mob = MobUtil.getMobInstance(event.getEntity());
            int[] coinDrops = mob.getCoinDrops();
            int amount = ThreadLocalRandom.current().nextInt(coinDrops[0], coinDrops[1] + 1);
            Coin coin = new Coin(amount);
            coin.drop(event.getEntity().getLocation());
        }
    }

}
