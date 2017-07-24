package jp.cm.springBootSlack.slack;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author oraisaori on 2017/07/08.
 */
@Controller
public final class SlackAPI {
    private final HttpUrl baseUrl = HttpUrl.parse("https://slack.com/api/");
    public final String clientId = "208286156567.208883461584";

    @RequestMapping("/slack")
    @ResponseBody
    public HttpUrl authorizeUrl(String scopes, HttpUrl redirectUrl, ByteString state, String team) {
        HttpUrl.Builder builder = baseUrl.newBuilder("/oauth/authorize")
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("scope", scopes)
                .addQueryParameter("redirect_uri", redirectUrl.toString())
                .addQueryParameter("state", state.base64());

        if (team != null) {
            builder.addQueryParameter("team", team);
        }

        return builder.build();
    }

    public String postMessage() {
        String result = null;

        Request request = new Request.Builder()
                .url("https://slack.com/api/")
                .build();

        // クライアントオブジェクト
        OkHttpClient httpClient = new OkHttpClient();

        // リクエストを投げて、結果受け取る
        try {
            Response response = httpClient.newCall(request).execute();
            result = response.body().string();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}
