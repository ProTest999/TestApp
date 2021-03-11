package com.apppuzzlecasinopuzzle.tictactoetest.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.apppuzzlecasinopuzzle.tictactoetest.R;
import com.apppuzzlecasinopuzzle.tictactoetest.ads.Utils;
import com.apppuzzlecasinopuzzle.tictactoetest.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        binding.bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        binding.bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
    }
}
