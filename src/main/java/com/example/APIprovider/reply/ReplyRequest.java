package com.example.APIprovider.reply;

public class ReplyRequest {

    private String message;
    private int reviewId;

    public ReplyRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}