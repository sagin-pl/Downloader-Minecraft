package pl.sagin.downloader.requests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.sagin.downloader.Downloader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TikTokGet {

    public TikTokGet(Downloader plugin) {
        this.plugin = plugin;
    }

    Downloader plugin;



    public static String tiktokGet(String content, Player player, int in) throws IOException, InterruptedException {
        JSONObject jsonObject = new JSONObject(content);
        String uuidLINK = jsonObject.getString("url");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.valueOf(uuidLINK))).build();

        String[] parts = uuidLINK.split("/");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String sama = String.valueOf(response.body());
        StringBuilder kox;

        sama = "[" + sama;
        sama = sama + "]";

        JSONArray array = new JSONArray(sama);
        JSONObject object = array.getJSONObject(0);

        kox = new StringBuilder(String.valueOf(object.get(parts[4])));

        if (kox.toString().contains("https"))
        {
            player.sendMessage(ChatColor.AQUA + "(==========) " + ChatColor.BOLD + "100%");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + kox);

        } else {
            int percentage = Integer.parseInt(kox.toString()) + in;

            if (percentage < 10) {
                player.sendMessage(ChatColor.AQUA + "(=---------) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 20) {
                player.sendMessage(ChatColor.AQUA + "(==--------) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 30) {
                player.sendMessage(ChatColor.AQUA + "(===-------) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 40) {
                player.sendMessage(ChatColor.AQUA + "(====------) " + ChatColor.BOLD + "" + percentage + "%");
            } else if (percentage < 50) {
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
        }


        TimeUnit.SECONDS.sleep(3);

        if (!kox.toString().contains("https"))
        {
            Random random = new Random();
            in += in+random.nextInt(6)+1;
            tiktokGet(content, player, in);

        }
        return kox.toString();
    }
}
