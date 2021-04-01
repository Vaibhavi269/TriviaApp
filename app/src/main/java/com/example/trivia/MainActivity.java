package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String numChoicesBaseStr = "Number of choices per question: %d";
    public static int numChoices = 4;

    private TextView numChoicesTV;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numChoicesTV = findViewById(R.id.num_choices_tv);
        SeekBar numChoicesSB = findViewById(R.id.num_choices_sb);

        numChoicesTV.setText(String.format(numChoicesBaseStr, numChoices));
        numChoicesSB.setOnSeekBarChangeListener(this);
    }
    public void colorTrivia(View view) {
        Intent intent = new Intent(this, ActivityColor.class);
        startActivity(intent);
    }
    public void animalTrivia(View view) {
        Intent intent = new Intent(this, ActivityAnimal.class);
        startActivity(intent);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // invoked continuously as the user slides the thumb
        numChoices = progress + 2;
        numChoicesTV.setText(String.format(numChoicesBaseStr, numChoices));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // invoked when the user first touches the SeekBar
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // invoked after the user finishes moving the SeekBar
    }
}