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
        if(!(sender instanceof Player))
        {
            sender.sendMessage("Je kan deze command niet uitvoeren vanuit de console.");
            return false;
        }

        Player player = (Player) sender;

        Hideme hideMe = Hideme.getInstance();

        if(!hideMe.isVanished(player))
        {
            hideMe.vanishPlayer(player);
            player.sendMessage("§aU bent nu gevanished");
            return true;
        }

        hideMe.unVanishPlayer(player);
        player.sendMessage("§cU bent niet meer gevanished.");

        return true;
    }
}
