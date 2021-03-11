package com.apppuzzlecasinopuzzle.tictactoetest.ads.data;


import com.apppuzzlecasinopuzzle.tictactoetest.ads.Utils;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.RemoteConfigProvider;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.entity.RemoteConfig;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConfigProvider implements RemoteConfigProvider {

    private final String mApiEndPoint = "https://gist.githubusercontent.com/ProTest999/%POSTID%/raw/config.txt"; //Can be ciphered with BAse64 or any other algorithm  gist.githubusercontent.com/ProTest999/%POSTID%/raw/config.txt
    private final String mRemoteConfigId = "03e0d521116bc54b2373dacfeadff51c"; //remote configuration record ID


    private OkHttpClient mHttpClient;

    public ConfigProvider() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Override
    public void load(final ConfigLoadingCallback callback) {
        //load application's remote config
        new Thread(() -> {
            try {
                //fetch config
                String urlToRemoteConfig = mApiEndPoint.replace("%POSTID%", mRemoteConfigId);
                String configJson = fetchRemoteConfigString(urlToRemoteConfig);
                Utils.debugOutput("Config: " + configJson);
                //parse config
                DocumentContext dc = JsonPath.parse(configJson);
                //fill RemoteConfig.Builder
                RemoteConfig remoteConfigBuilder = new RemoteConfig.Builder()
                        .setIsDisplayADS(getConfigBool(dc, "is_display_ads"))
                        .setOfferUrl(getConfigStr(dc, "$.url", ""))
                        .setOneSignalId(getConfigStr(dc, "$.os_id", null))
                        .setFacebookId(getConfigStr(dc, "$.fb_id", null))
                        .build();
                //provide result to the listener
                callback.onConfigReady(remoteConfigBuilder);
            } catch (Exception e) {
                callback.onError(e);
            }
        }).start();
    }

    /**
     * Makes GET request and returns response body
     *
     * @param url HTTP address
     * @return Response body
     */
    @SuppressWarnings("ConstantConditions")
    private String fetchRemoteConfigString(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = mHttpClient.newCall(request).execute();
        return response.body().string();
    }


    /**
     * Gets configuration object by selector as Boolean. Values are set to false by default.
     *
     * @param key Selector
     * @return Requested value or false
     */
    private boolean getConfigBool(DocumentContext jsonDocument, String key) {
        try {
            return jsonDocument.read(key, boolean.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets configuration object by selector as String
     *
     * @param key          Selector
     * @param defaultValue Default value
     * @return Requested value or defaultValue
     */
    private String getConfigStr(DocumentContext jsonDocument, String key, String defaultValue) {
        try {
            String val = jsonDocument.read(key, String.class);
            if (val == null) return defaultValue;
            return val;
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
