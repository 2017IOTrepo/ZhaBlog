package nuc.iot.blog.provider;

import com.alibaba.fastjson.JSON;
import nuc.iot.blog.dto.AccessTokenDTO;
import nuc.iot.blog.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String respStr = response.body().string();
            GithubUser githubUser = JSON.parseObject(respStr, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
