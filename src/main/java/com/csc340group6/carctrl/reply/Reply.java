package com.csc340group6.carctrl.reply;

import com.csc340group6.carctrl.reviews.Review ;
import jakarta.persistence.*;

@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private int replyId;

    private String message;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public Reply() {}

    public Reply(String message, Review review) {
        this.message = message;
        this.review = review;
    }

    public int getReplyId() { return replyId; }
    public void setReplyId(int replyId) { this.replyId = replyId; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}