package pl.sagin.downloader.requests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringBuilder url = new StringBuilder("kox");
        String sama = String.valueOf(response.body());
        StringBuilder kox = new StringBuilder();
        boolean start1 = false;



        for (int i = 0; i < sama.length(); i++) {
            if (sama.charAt(i)==':') {
                start1 = true;
                continue;
            }
            if(start1 && sama.charAt(i) == '}')
            {
                break;
            }
            if (start1) {
                kox.append(sama.charAt(i));
            }
        }

        if (kox.toString().contains("https"))
        {
            //     kox.substring(1,kox.length() - 1)
            kox = new StringBuilder(kox.substring(1, kox.length() - 1));
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

        if (kox.toString().contains("https"))
        {
            boolean start = false;

            for (int i = 0; i < sama.length(); i++) {
                if (sama.charAt(i)==':') {
                    start = true;
                    continue;
                }
                if(start && sama.charAt(i) == '"')
                {
                    return url.toString();
                }
                if (start) {
                    url.append(sama.charAt(i));
                }
            }

        } else {
            Random random = new Random();
            in += in+random.nextInt(6)+1;
            tiktokGet(content, player, in);
        }

        return kox.toString();
    }
}
