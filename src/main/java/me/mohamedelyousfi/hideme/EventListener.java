package me.mohamedelyousfi.hideme;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class EventListener implements Listener {


    /**
     * Deze functie wordt getriggered wanneer een vanished speler joint. Deze speler wordt dan verborgen van de andere spelers.
     */
    @EventHandler
    public void onVanishedPlayerJoin(PlayerJoinEvent event) {
        Hideme hideme = Hideme.getInstance();

        // Als de gejoinde speler in de vanished lijst zit dan vanishen we de speler
        if (hideme.isVanished(event.getPlayer())) {
            hideme.vanishPlayer(event.getPlayer());
        }
    }

    /**
     * Deze functie wordt getriggered wanneer een gewone speler joint. Alle vanished players worden dan verborgen van deze speler
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Hideme hideme = Hideme.getInstance();

        // Loopen door alle vanishedPlayers en deze dan verbergen van de gejoinde speler
        for (UUID vanishedPlayerUUID : hideme.vanishedPlayers) {

            // Player object krijgen van de Player UUID
            Player vanishedPlayer = hideme.getServer().getPlayer(vanishedPlayerUUID);

            if (vanishedPlayer != null) {
                // De speler verbergen van de gejoinde speler
                event.getPlayer().hidePlayer(hideme, vanishedPlayer);
            }

        }

    }

}
