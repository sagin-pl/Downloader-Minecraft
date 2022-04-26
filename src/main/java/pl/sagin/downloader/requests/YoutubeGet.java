package pl.sagin.downloader.requests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class YoutubeGet {

    public YoutubeGet() {
    }

    public static String youtubeGet(String uurl, Player player, int percentage) throws IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uurl)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String[] parts = uurl.split("/");

        String sama = response.body();

        StringBuilder kox;

        sama = "[" + sama;
        sama = sama + "]";

        JSONArray array = new JSONArray(sama);
        JSONObject object = array.getJSONObject(0);

        kox = new StringBuilder(String.valueOf(object.get(parts[4])));

        String ur = String.valueOf(kox);

        if (ur.contains("https")){
            player.sendMessage(ChatColor.AQUA + "(==========) " + ChatColor.BOLD + "100%");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + ur);
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
