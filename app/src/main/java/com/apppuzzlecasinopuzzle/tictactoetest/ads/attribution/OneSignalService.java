package com.apppuzzlecasinopuzzle.tictactoetest.ads.attribution;

import android.content.Context;


import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.ThirdPartyService;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.entity.InputData;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.entity.OutputData;
import com.onesignal.OneSignal;

import org.jetbrains.annotations.Nullable;

public class OneSignalService implements ThirdPartyService<OneSignalService.Input, OutputData> {

    public static class Input extends InputData {

        final Context applicationContext;
        final String oneSignalAppId;

        public Input(Context applicationContext, String oneSignalAppId) {
            this.applicationContext = applicationContext;
            this.oneSignalAppId = oneSignalAppId;
        }
    }

    private static OneSignalService mInstance = null;

    private OneSignalService() {
    }

    public static OneSignalService getInstance() {
        if (mInstance == null)
            mInstance = new OneSignalService();
        return mInstance;
    }

    @Override
    public void initialize(Input input, @Nullable InitializationCallback callback) {
        OneSignal.init(input.applicationContext, "REMOTE", input.oneSignalAppId, null, null);
    }

}
