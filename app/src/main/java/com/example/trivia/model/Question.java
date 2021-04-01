package com.example.trivia.model;


import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class Question {
    private String text;
    private String correctAnswer;
    private List<String> alternatives;

    public void shuffleAlternatives() {
        Collections.shuffle(alternatives);
    }
}
