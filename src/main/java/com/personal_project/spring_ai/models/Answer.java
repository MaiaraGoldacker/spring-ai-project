package com.personal_project.spring_ai.models;

public class Answer {

    private String answer;

    public Answer(String content) {
        this.answer = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
