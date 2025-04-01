package carctrladminproject.example.carctrlsysadminproject;

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

    public Optional<Reply> getReplyById(Long id) {
        return replyRepository.findById(id);
    }

    public Optional<Reply> getReplyByReviewId(Long reviewId) {
        return replyRepository.findByReviewId(reviewId);
    }

    public Reply saveReply(Reply reply) {
        return replyRepository.save(reply);
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}

