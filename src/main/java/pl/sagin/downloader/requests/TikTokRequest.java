package pl.sagin.downloader.requests;

import org.json.JSONObject;
import pl.sagin.downloader.Downloader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TikTokRequest {

    public TikTokRequest(Downloader plugin) {
        this.plugin = plugin;
    }

    Downloader plugin;

    public static String getApi(String args) {


        try {
            JSONObject data = new JSONObject();
            data.put("url", args);
            URL url = new URL("https://api.sagin.pl/szurag");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(data.toString().getBytes());
            int responseCode = httpConnection.getResponseCode();
            BufferedReader bufferedReader;
            if (responseCode > 199 && responseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            return String.valueOf(content);
        } catch (Exception e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

        return "";
    }

}
