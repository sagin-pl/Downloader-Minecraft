package pl.sagin.downloader.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.sagin.downloader.Downloader;
import pl.sagin.downloader.requests.TikTokGet;
import pl.sagin.downloader.requests.TikTokRequest;

public class PobierzCommand implements CommandExecutor {

    public PobierzCommand(Downloader plugin) {
        this.plugin = plugin;
    }

    Downloader plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;


            if (args[0].contains("tiktok") && args[0].contains("video") && args[0].contains("https")) {

                if (args.length > 2) {
                    player.sendMessage(ChatColor.RED + "Za dużo argumentów " + "poprawne użycie: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/pobierz [url]");
                    return false;
                }

                if (args.length == 1) {
                    String url = args[0];
                    try {

                        String post = TikTokRequest.getApi(args[0]);
                        int in = 0;
                        String respurl = TikTokGet.tiktokGet(post, player, in);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
