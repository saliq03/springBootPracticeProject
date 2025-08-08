package com.quizes.quiz.model;

import lombok.Data;

@Data
public class QuestionWrapper {

    private int id;
     private String category;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    public QuestionWrapper(int id, String category, String question, String optionA, String optionB, String optionC,String optionD) {
        this.id = id;
        this.category = category;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }
}
