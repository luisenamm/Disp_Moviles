package com.itesm.ex1_a01633894;

import android.content.Intent;

public class Question {

    Integer id_question;

    String question,
            ans_1,
            ans_2,
            ans_3,
            ans_4,
            correct;

    public Question(Integer id_question, String question, String ans_1, String ans_2, String ans_3, String ans_4, String correct) {
        this.id_question = id_question;
        this.question = question;
        this.ans_1 = ans_1;
        this.ans_2 = ans_2;
        this.ans_3 = ans_3;
        this.ans_4 = ans_4;
        this.correct = correct;
    }

    public Integer getId_question() {
        return id_question;
    }

    public void setId_question(Integer id_question) {
        this.id_question = id_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns_1() {
        return ans_1;
    }

    public void setAns_1(String ans_1) {
        this.ans_1 = ans_1;
    }

    public String getAns_2() {
        return ans_2;
    }

    public void setAns_2(String ans_2) {
        this.ans_2 = ans_2;
    }

    public String getAns_3() {
        return ans_3;
    }

    public void setAns_3(String ans_3) {
        this.ans_3 = ans_3;
    }

    public String getAns_4() {
        return ans_4;
    }

    public void setAns_4(String ans_4) {
        this.ans_4 = ans_4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
