package me.mohamedelyousfi.hideme.commands;

import me.mohamedelyousfi.hideme.Hideme;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Hideme hideMe = Hideme.getInstance();
        FileConfiguration config = hideMe.getConfig();

        String messagePrefix = config.getString("messages.prefix");
        String vanishMessage = config.getString("messages.vanish");
        String unvanishMessage = config.getString("messages.unvanish");
        String isConsoleMessage = config.getString("messages.isconsole");

        // Als de commandsender geen Player is dan annuleren we alles.
        if (!(sender instanceof Player)) {
            sender.sendMessage(messagePrefix + isConsoleMessage);
            return false;
        }

        // De CommandSender casten naar een Player object
        Player player = (Player) sender;

        // Als de speler niet is gevanished dan moeten we de speler vanishen
        if (!hideMe.isVanished(player)) {
            hideMe.vanishPlayer(player);
            player.sendMessage(messagePrefix + vanishMessage);
            return true;
        }

        // Als de speler wel gevanished is, dan moet die uit vanish gehaald worden.
        hideMe.unVanishPlayer(player);
        player.sendMessage(messagePrefix + unvanishMessage);

        return true;
    }
}
