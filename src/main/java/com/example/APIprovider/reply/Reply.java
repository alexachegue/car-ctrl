package com.example.APIprovider.reply;

import com.example.APIprovider.reviews.Review;
import jakarta.persistence.*;

@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private int replyId;

    private String message;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", referencedColumnName = "reviewId", nullable = false, unique = true)
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