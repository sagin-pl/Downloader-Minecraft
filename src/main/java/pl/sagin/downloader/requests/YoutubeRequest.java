package pl.sagin.downloader.requests;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import pl.sagin.downloader.Downloader;

public class YoutubeRequest {

    public YoutubeRequest(Downloader plugin) {
        this.plugin = plugin;
    }

    Downloader plugin;

    public static String getApi(String link, String quality) throws Exception {

        Request request = Request.Post("https://api.sagin.pl/szuragV2");
        String body = "{\n  \"settings\": \"" + quality + "\",\n  \"url\": \""+ link +"\"\n}";
        request.bodyString(body,ContentType.APPLICATION_JSON);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-Type", "application/json");
        HttpResponse httpResponse = request.execute().returnResponse();
        System.out.println(httpResponse.getStatusLine());
        if (httpResponse.getEntity() != null) {
            String html = EntityUtils.toString(httpResponse.getEntity());

            boolean st = false;
            StringBuilder str = new StringBuilder();

            for (int i=0; i<html.length(); i++)
            {
                if (html.charAt(i) == 'h' && !st) {
                    st = true;
                }

                if (html.charAt(i) == '"' && st) {
                    break;
                }

                if (st)
                    str.append(html.charAt(i));

            }

            return String.valueOf(str);
        }


        return "";
    }

}
