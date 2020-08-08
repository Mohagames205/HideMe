package me.mohamedelyousfi.hideme;

import me.mohamedelyousfi.hideme.commands.VanishCommand;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Hideme extends JavaPlugin {


    protected static Hideme hideMe;
    public List<UUID> vanishedPlayers = new ArrayList<>();
    public BossBar bossBar;
    public final static String prefix = "§f[§cHide§4Me§f] ";


    @Override
    public void onEnable() {
        hideMe = this;

        //Dit maakt een instance aan van de BossBar class. Zo kunnen wij aan elke vanished player een bossbar toevoegen.
        bossBar = Bukkit.createBossBar("Vanished", BarColor.PINK, BarStyle.SOLID);

        //Vanish command registreren bij Bukkit
        getCommand("vanish").setExecutor(new VanishCommand());

        //Event class registreren bij de eventhandler
        getServer().getPluginManager().registerEvents(new EventListener(), this);

    }

    /**
     * Deze functie zorgt ervoor dat de gegeven speler niet meer gezien wordt.
     *
     * @param player Player om te verbergen
     */
    public void vanishPlayer(Player player)
    {
        // Speler toevoegen aan vanishedPlayers lijst
        vanishedPlayers.add(player.getUniqueId());

        // Bossbar toevoegen bij de speler
        bossBar.addPlayer(player);

        // Loopen door elke speler om de gegeven speler te verbergen van hen.
        for (Player playerToHideFrom : getServer().getOnlinePlayers()) {

            // Als de speler in de loop de hideme.see permission heeft
            if(!playerToHideFrom.hasPermission("hideme.see"))
            {
                playerToHideFrom.hidePlayer(this, player);
            }
        }
    }

    /**
     * Deze functie zorgt ervoor dat de gegeven speler weer zichtbaar is voor andere spelers.
     *
     * @param player Player om te verhullen
     */
    public void unVanishPlayer(Player player)
    {
        // Speler verwijderen aan vanishedPlayers lijst
        vanishedPlayers.remove(player.getUniqueId());

        // Bossbar weghalen bij de speler
        bossBar.removePlayer(player);

        // Loopen door elke speler om de gevanishde speler te verhullen
        for (Player onlinePlayer: getServer().getOnlinePlayers()) {
            onlinePlayer.showPlayer(this, player);
        }
    }

    /**
     * Deze functie checkt als de speler in de vanishedPlayers lijst zit. Dit wordt gebruikt om te checken of een speler in vanish gezet moet worden als die terug joint.
     *
     * @param player De speler bij wie gecheckt moet worden als die in de vanish lijst zit
     * @return true als de speler in de vanished lijst zit is en false als de speler niet in de lijst zit
     */
    public boolean isVanished(Player player)
    {
        return vanishedPlayers.contains(player.getUniqueId());
    }

    /**
     * Deze method returned een instance van de Hideme class.
     *
     * @return een instance van de Main class
     */
    public static Hideme getInstance()
    {
        return hideMe;
    }

}
