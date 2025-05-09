package com.example.APIprovider.reply;

import com.example.APIprovider.reviews.Review;
import com.example.APIprovider.reviews.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.APIprovider.reviews.ReviewService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;
    private final ReviewRepository reviewRepository;

    public ReplyController(ReplyService replyService, ReviewRepository reviewRepository) {
        this.replyService = replyService;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable int id) {
        Optional<Reply> reply = replyService.getReplyById(id);
        return reply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Reply> getReplyByReviewId(@PathVariable int reviewId) {
        Optional<Reply> reply = replyService.getReplyByReviewId(reviewId);
        return reply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public String createOrUpdateReply(
            @RequestParam("reviewId") int reviewId,
            @RequestParam("message") String message
    ) {
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            return "redirect:/reviews/manage?error=ReviewNotFound";
        }

        Review review = reviewOpt.get();
        Optional<Reply> existing = replyService.getReplyByReviewId(reviewId);

        Reply reply = existing.orElse(new Reply());
        reply.setReview(review);
        reply.setMessage(message);
        replyService.saveReply(reply);

        return "redirect:/reviews/manage?providerId=" + review.getProviderId();
    }

    @PutMapping("/update/{replyId}")
    public ResponseEntity<Reply> updateReply(@PathVariable int replyId, @RequestBody ReplyRequest request) {
        Optional<Reply> existingReply = replyService.getReplyById(replyId);

        if (existingReply.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reply reply = existingReply.get();
        reply.setMessage(request.getMessage());

        return ResponseEntity.ok(replyService.saveReply(reply));
    }

    @PostMapping("/delete/{replyId}")
    public String deleteReplyForm(@PathVariable int replyId) {
        System.out.println("Deleting reply with ID: " + replyId);

        Optional<Reply> replyOpt = replyService.getReplyById(replyId);
        if (replyOpt.isPresent()) {
            Reply reply = replyOpt.get();

            Review review = reply.getReview();
            if (review != null) {
                review.setReply(null);
            }

            replyService.deleteReply(replyId);
        }

        return "redirect:/reviews/manage";
    }

//    @DeleteMapping("/delete/{replyId}")
//    public ResponseEntity<Void> deleteReply(@PathVariable int replyId) {
//        if (replyService.getReplyById(replyId).isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        replyService.deleteReply(replyId);
//        return ResponseEntity.noContent().build();
//    }

}