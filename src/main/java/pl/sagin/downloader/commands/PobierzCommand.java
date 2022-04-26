package pl.sagin.downloader.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.sagin.downloader.Downloader;
import pl.sagin.downloader.requests.TikTokGet;
import pl.sagin.downloader.requests.TikTokRequest;
import pl.sagin.downloader.requests.YoutubeGet;
import pl.sagin.downloader.requests.YoutubeRequest;

public class PobierzCommand implements CommandExecutor {

    public PobierzCommand(Downloader plugin) {
        this.plugin = plugin;
    }

    Downloader plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player player = (Player) sender;


            if (args.length<1 || !args[0].contains("https") &&
                    !args[0].contains("video")) {
                player.sendMessage(ChatColor.RED + "Komenda musi zawierać url Do tiktoka. " + "poprawne użycie: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/pobierz [url]");
                return false;
            }

            if (args[0].contains("tiktok") && args[0].contains("video") && args[0].contains("https"))
            {

                if (args.length>2) {
                    player.sendMessage(ChatColor.RED + "Za dużo argumentów " + "poprawne użycie: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/pobierz [url]");
                    return false;
                }

                if (args.length==1) {
                    String url = args[0];
                    try {

                        String post = TikTokRequest.getApi(args[0]);
                        int in =0;
                        String respurl = TikTokGet.tiktokGet(post, player, in);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (args[0].contains("youtu") && args[0].contains("be") && args[0].contains("https")) {
                ytandp(args, player);
                return false;

            }


            //https://pl.pornhub.com/view_video.php?viewkey=ph5ff70c4f4a3e3
            //info kazali mi jakby coś ja nie chciałem tego dawać. Pozdrawaiam
            if (args[0].contains("pornhub") && args[0].contains("video") && args[0].contains("https")) {
                ytandp(args, player);
                return false;
            }
            return false;
        } else {
            System.out.println("Use player to be player lol");
        }

        return false;
    }


    public static void ytandp(String[] args, Player player) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Podaj Jakość" + "poprawne użycie: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/pobierz [url] [hd/best]]");
            return;
        }
        if (!args[1].equalsIgnoreCase("best")) {
            if (!args[1].equalsIgnoreCase("hd")) {
                player.sendMessage(ChatColor.RED + "Zła jakość " + "poprawne użycie: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/pobierz [url] [hd/best]]");
            }
        }
        if (args.length>3) {
            player.sendMessage(ChatColor.RED + "Za dużo argumentów " + "poprawne użycie: " + ChatColor.GOLD + "" + ChatColor.BOLD + "/pobierz [url] [hd/best]]");
        }

        String url = args[0], quality=args[1];

        try {
            String uurl = YoutubeRequest.getApi(url, quality);
            int pricentage = 0;
            player.sendMessage(YoutubeGet.youtubeGet(uurl, player, pricentage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
