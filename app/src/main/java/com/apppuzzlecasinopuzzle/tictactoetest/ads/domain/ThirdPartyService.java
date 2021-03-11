package com.apppuzzlecasinopuzzle.tictactoetest.ads.domain;

import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.entity.InputData;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.domain.entity.OutputData;

import org.jetbrains.annotations.Nullable;

public interface ThirdPartyService<IN extends InputData, OUT extends OutputData> {

    interface InitializationCallback<OUT extends OutputData> {

        void onInitialized(OUT data);

    }

    void initialize(IN input, @Nullable InitializationCallback<OUT> callback);

}

