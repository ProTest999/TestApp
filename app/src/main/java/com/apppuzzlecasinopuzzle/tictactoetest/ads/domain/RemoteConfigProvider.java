package com.apppuzzlecasinopuzzle.tictactoetest.ads.domain;


import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.entity.RemoteConfig;

public interface RemoteConfigProvider {

    interface ConfigLoadingCallback {

        void onConfigReady(RemoteConfig remoteConfig);

        void onError(Exception e);

    }

    void load(ConfigLoadingCallback callback);

}