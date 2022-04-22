package pl.sagin.downloader.requests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class YoutubeGet {

    public static String youtubeGet(String uurl, Player player, int percentage) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uurl))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        String nig = response.body();

        boolean st = false;
        StringBuilder str = new StringBuilder();

        for (int i=0; i<nig.length(); i++)
        {
            if (nig.charAt(i) == ':' && !st) {
                st = true;
                i++;
            }

            if (nig.charAt(i) == '}' && st) {
                break;
            }

            if (st)
                str.append(nig.charAt(i));

        }

        String ur = String.valueOf(str);

        if (ur.contains("https")){
            player.sendMessage(ChatColor.AQUA + "(==========) " + ChatColor.BOLD + "100%");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + ur.substring(1,ur.length() - 1));
        } else {

            Random random = new Random();
            percentage = random.nextInt(percentage+10) + percentage;

            if (percentage < 10) {
                player.sendMessage(ChatColor.AQUA + "(=---------) " + ChatColor.BOLD + "" + percentage + "%");
            }else if (percentage < 20) {
                player.sendMessage(ChatColor.AQUA + "(==--------) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 30) {
                player.sendMessage(ChatColor.AQUA + "(===-------) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 40) {
                player.sendMessage(ChatColor.AQUA + "(====------) " + ChatColor.BOLD + "" + percentage + "%");
            }else if (percentage < 50) {
                player.sendMessage(ChatColor.AQUA + "(=====-----) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 60) {
                player.sendMessage(ChatColor.AQUA + "(======----) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 70) {
                player.sendMessage(ChatColor.AQUA + "(=======---) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 80) {
                player.sendMessage(ChatColor.AQUA + "(========--) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 90) {
                player.sendMessage(ChatColor.AQUA + "(=========-) " + ChatColor.BOLD + "" + percentage + "%");
            }
            TimeUnit.SECONDS.sleep(3);
            YoutubeGet.youtubeGet(uurl, player, percentage);
        }

        return "";

    }

}
