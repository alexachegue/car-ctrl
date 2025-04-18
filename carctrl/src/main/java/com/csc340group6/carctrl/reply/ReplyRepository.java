package com.csc340group6.carctrl.reply;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    Optional<Reply> findByReview_ReviewId(int reviewId);
    Optional<Reply> findByReplyId(int id);

}

