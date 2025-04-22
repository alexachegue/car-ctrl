package com.csc340group6.carctrl.reply;
import com.csc340group6.carctrl.reviews.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReviewRepository reviewRepository;

    public ReplyService(ReplyRepository replyRepository, ReviewRepository reviewRepository) {
        this.replyRepository = replyRepository;
        this.reviewRepository = reviewRepository;
    }

    public Optional<Reply> getReplyById(int id) {
        return replyRepository.findByReplyId(id);
    }

    public Optional<Reply> getReplyByReviewId(int reviewId) {
        return replyRepository.findByReview_ReviewId(reviewId);
    }

    public Reply saveReply(Reply reply) {
        return replyRepository.save(reply);
    }

    public void deleteReply(int id) {
        replyRepository.deleteById(id);
    }
}