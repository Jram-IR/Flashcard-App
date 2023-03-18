package com.project.flashcardapp.home.dto;

import com.google.firebase.firestore.Exclude;

public class FlashCardModel {
    private String question="";
    private String nextReviewDate=" ";
    private String reviewStatus=" ";
    private String answer,deckId=" ";
    @Exclude
    private String id;

    public FlashCardModel() {
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }





    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(String nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
