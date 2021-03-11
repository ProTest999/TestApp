package com.apppuzzlecasinopuzzle.tictactoetest;

import com.apppuzzlecasinopuzzle.tictactoetest.ads.SDKConfig;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.data.ConfigProvider;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.data.SettingsStorageImpl;
import com.apppuzzlecasinopuzzle.tictactoetest.game.MainActivity;
import com.facebook.FacebookSdk;

public class Application extends android.app.Application {


    /**
     * Local SDK config.
     * Can be touched ONLY before call of super.onCreate(); method in Parent Application.class
     */
    public static SDKConfig sdkConfig = new SDKConfig();


    @Override
    public void onCreate() {
        super.onCreate();
        sdkConfig.sweetieActivityClass = MainActivity.class; // TODO: set to your main activity
        sdkConfig.setRemoteConfigProvider(new ConfigProvider()); // TODO: set to your ConfigProvider
//        sdkConfig.splashScreenDrawableResId = R.drawable.ramka; //TODO: set your own R.drawable.XXXXXX

        new SettingsStorageImpl(this, "AppSettings");
        FacebookSdk.setAutoInitEnabled(false);

    }
}
