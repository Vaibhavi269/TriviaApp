package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trivia.model.Question;
import com.example.trivia.offline.Animal;


import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ActivityAnimal extends AppCompatActivity implements View.OnClickListener {
    private List<Button> buttons = new LinkedList<>();

    private Deque<Question> questionsBuffer = new ArrayDeque<>();
    private String correctAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        buttons.add((Button)findViewById(R.id.skip_btn));
        buttons.add((Button)findViewById(R.id.first_btn));
        buttons.add((Button)findViewById(R.id.second_btn));
        buttons.add((Button)findViewById(R.id.third_btn));
        buttons.add((Button)findViewById(R.id.fourth_btn));

        for (Button button: buttons) {
            button.setOnClickListener(this);
        }

        reloadQuestionsBuffer();
        updateView(nextQuestion());
    }
    private void reloadQuestionsBuffer() {
        List<String> animals = new LinkedList<>(Animal.animal);
        Collections.shuffle(animals);
        for (String animal: animals) {
            Question question = new Question();
            question.setText(animal);
            question.setCorrectAnswer(animal);
            List<String> alternatives = new LinkedList<>(animals);
            alternatives.remove(animal);
            Collections.shuffle(alternatives);
            question.setAlternatives(alternatives.subList(0, 3));
            question.getAlternatives().add(animal);
            questionsBuffer.push(question);
        }
    }
    private Question nextQuestion() {
        if (questionsBuffer.size() == 0) {
            reloadQuestionsBuffer();
        }
        return questionsBuffer.poll();
    }

    private void updateView(Question question) {
        ImageView questionIV = findViewById(R.id.animaliv);
        questionIV.setImageResource(
                getResources().getIdentifier(
                        question.getText(),
                        "drawable",
                        getPackageName()
                )
        );
        question.shuffleAlternatives();
        for (int i = 0; i < 4; i++) {
            buttons.get(i + 1).setText(question.getAlternatives().get(i));
        }
        correctAnswer = question.getCorrectAnswer();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.skip_btn) {
            updateView(nextQuestion());
        } else {
            Button button = (Button)view;
            if (button.getText().equals(correctAnswer)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                updateView(nextQuestion());
            } else {
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}