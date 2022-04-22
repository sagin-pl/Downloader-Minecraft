package pl.sagin.downloader.commands;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class DayCommand implements CommandExecutor {

    public boolean isDay() {
        Server server = getServer();
        long time = server.getWorld("world").getTime();

        if(time > 0 && time < 12300) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (isDay())
        {
            player.sendMessage("day");
        }
        else {
            player.sendMessage("night");
        }


        return false;
    }
}
