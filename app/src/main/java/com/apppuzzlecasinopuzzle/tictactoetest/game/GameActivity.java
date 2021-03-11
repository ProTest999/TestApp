package com.apppuzzlecasinopuzzle.tictactoetest.game;

import android.app.AlertDialog;
import com.apppuzzlecasinopuzzle.tictactoetest.game.entity.Person;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.apppuzzlecasinopuzzle.tictactoetest.R;
import com.apppuzzlecasinopuzzle.tictactoetest.databinding.ActivityGameBinding;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean priority = false;
    int[][] field = new int[3][3];
    private ActivityGameBinding binding;
    private Person person;
    private int countMove = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        person = new Person();
        init();
    }


    private void init() {
        binding.iv11.setOnClickListener(this);
        binding.iv12.setOnClickListener(this);
        binding.iv13.setOnClickListener(this);
        binding.iv21.setOnClickListener(this);
        binding.iv22.setOnClickListener(this);
        binding.iv23.setOnClickListener(this);
        binding.iv31.setOnClickListener(this);
        binding.iv32.setOnClickListener(this);
        binding.iv33.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv11:
                move(binding.iv11, 0, 0);
                break;
            case R.id.iv12:
                move(binding.iv12, 0, 1);
                break;
            case R.id.iv13:
                move(binding.iv13, 0, 2);
                break;

            case R.id.iv21:
                move(binding.iv21, 1, 0);
                break;
            case R.id.iv22:
                move(binding.iv22, 1, 1);
                break;
            case R.id.iv23:
                move(binding.iv23, 1, 2);
                break;

            case R.id.iv31:
                move(binding.iv31, 2, 0);
                break;
            case R.id.iv32:
                move(binding.iv32, 2, 1);
                break;
            case R.id.iv33:
                move(binding.iv33, 2, 2);
                break;
        }
    }

    private void move(ImageView imageView, int x, int y) {
        //false = x
        countMove++;
        if (!priority) {
            imageView.setBackgroundResource(person.getX());
            imageView.setClickable(false);
            field[x][y] = person.getX();
            priority = true;
            if (isWin()) {
                showMyDialog(person.getX());
            } else {
                if(countMove == 9){
                    showDraw();
                }
            }
        } else {
            imageView.setBackgroundResource(person.getY());
            imageView.setClickable(false);
            field[x][y] = person.getY();
            priority = false;
            if (isWin()) {
                showMyDialog(person.getY());
            } else {
                if(countMove == 9){
                    showDraw();
                }
            }
        }
    }

    private boolean isWin() {
        if (checkHorizontal() == person.getX() || checkVertical() == person.getX() || checkDiagonal() == person.getX()) {
            return true;
        } else
            return checkHorizontal() == person.getY() || checkVertical() == person.getY() || checkDiagonal() == person.getY();
    }


    private void showMyDialog(int imageRes) {
        new AlertDialog.Builder(GameActivity.this).setIcon(imageRes)
                .setTitle("We have a winner!")
                .setCancelable(false)
                .setMessage("Congratulations!")
                .setPositiveButton("Start over", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recreate();
                    }
                })
                .setNegativeButton("Go to the menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void showDraw(){
        new AlertDialog.Builder(GameActivity.this)
                .setTitle("Its Draw!")
                .setCancelable(false)
                .setMessage("Do you want play more?")
                .setPositiveButton("Start over", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recreate();
                    }
                })
                .setNegativeButton("Go to the menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    private int checkVertical() {
        if (field[0][0] == person.getX() & field[1][0] == person.getX() & field[2][0] == person.getX()) {
            return person.getX();
        }
        if (field[0][0] == person.getY() & field[1][0] == person.getY() & field[2][0] == person.getY()) {
            return person.getY();
        }
        if (field[0][1] == person.getX() & field[1][1] == person.getX() & field[2][1] == person.getX()) {
            return person.getX();
        }
        if (field[0][1] == person.getY() & field[1][1] == person.getY() & field[2][1] == person.getY()) {
            return person.getY();
        }
        if (field[0][2] == person.getX() & field[1][2] == person.getX() & field[2][2] == person.getX()) {
            return person.getX();
        }
        if (field[0][2] == person.getY() & field[1][2] == person.getY() & field[2][2] == person.getY()) {
            return person.getY();
        }
        return 0;
    }

    private int checkHorizontal() {
        if (field[0][0] == person.getX() & field[0][1] == person.getX() & field[0][2] == person.getX()) {
            return person.getX();
        }
        if (field[0][0] == person.getY() & field[0][1] == person.getY() & field[0][2] == person.getY()) {
            return person.getY();
        }
        if (field[1][0] == person.getX() & field[1][1] == person.getX() & field[1][2] == person.getX()) {
            return person.getX();
        }
        if (field[1][0] == person.getY() & field[1][1] == person.getY() & field[1][2] == person.getY()) {
            return person.getY();
        }
        if (field[2][0] == person.getX() & field[2][1] == person.getX() & field[2][2] == person.getX()) {
            return person.getX();
        }
        if (field[2][0] == person.getY() & field[2][1] == person.getY() & field[2][2] == person.getY()) {
            return person.getY();
        }
        return 0;
    }

    private int checkDiagonal() {
        if (field[0][0] == person.getX() & field[1][1] == person.getX() & field[2][2] == person.getX()) {
            return person.getX();
        }
        if (field[0][2] == person.getX() & field[1][1] == person.getX() & field[2][0] == person.getX()) {
            return person.getX();
        }
        if (field[0][0] == person.getY() & field[1][1] == person.getY() & field[2][2] == person.getY()) {
            return person.getY();
        }
        if (field[0][2] == person.getY() & field[1][1] == person.getY() & field[2][0] == person.getY()) {
            return person.getY();
        }
        return 0;
    }
}
