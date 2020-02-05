package net.projectx.menecia.mobs.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import net.projectx.menecia.Core;
import net.projectx.menecia.mobs.Mob;
import net.projectx.menecia.mobs.Mobs;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobDamageByBraveEvent implements Listener {

    private Core plugin;
    private ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public MobDamageByBraveEvent(Core plugin) {
        this.plugin = plugin;
        removeDamageIndicatorParticle(plugin);
    }

    @EventHandler
    private void onEvent(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        if (Mobs.isMob(victim)) {
            int mobId = Mobs.getId(victim);
            Mob mob = Mobs.get(mobId);

            // TODO: Monster Health Bubble!
        }
    }

    private void removeDamageIndicatorParticle(Core plugin) {
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
                PacketType.Play.Server.WORLD_PARTICLES) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                if (event.getPacketType() == PacketType.Play.Server.WORLD_PARTICLES) {
                    if (packet.getEntityModifier(event).read(0) instanceof Player) {
                        int particleId = packet.getIntegers().read(0);
                        int damageIndicatorParticleId = 7;
                        if (particleId == damageIndicatorParticleId) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        });
    }

}
