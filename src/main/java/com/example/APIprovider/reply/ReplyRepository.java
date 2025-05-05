package com.example.APIprovider.reply;

import com.example.APIprovider.reviews.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    Optional<Reply> findByReview_ReviewId(int reviewId);
    Optional<Reply> findByReplyId(int id);


}