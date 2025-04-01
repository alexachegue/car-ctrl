package carctrladminproject.example.carctrlsysadminproject;

import jakarta.persistence.*;

@Entity
@Table(name = "replies")


public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @OneToOne
    @JoinColumn(name = "review_id", nullable = false, unique = true)
    private Review review;

    public Reply() {}

    public Reply(String message, Review review) {
        this.message = message;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

