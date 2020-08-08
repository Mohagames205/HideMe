package me.mohamedelyousfi.hideme.commands;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.mohamedelyousfi.hideme.Hideme;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Als de commandsender geen Player is dan annuleren we alles.
        if(!(sender instanceof Player))
        {
            sender.sendMessage(Hideme.prefix + "Je kan deze command niet uitvoeren vanuit de console.");
            return false;
        }

        // De CommandSender casten naar een Player object
        Player player = (Player) sender;

        Hideme hideMe = Hideme.getInstance();

        // Als de speler niet is gevanished dan moeten we de speler vanishen
        if(!hideMe.isVanished(player))
        {
            hideMe.vanishPlayer(player);
            player.sendMessage(Hideme.prefix + "§aU bent nu gevanished");
            return true;
        }

        // Als de speler wel gevanished is, dan moet die uit vanish gehaald worden.
        hideMe.unVanishPlayer(player);
        player.sendMessage(Hideme.prefix + "§cU bent niet meer gevanished.");

        return true;
    }
}
