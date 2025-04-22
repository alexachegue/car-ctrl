package com.csc340group6.carctrl.reply;

import com.csc340group6.carctrl.reviews.Review;
import com.csc340group6.carctrl.reviews.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
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



    /**
    @PostMapping("/create")
    public ResponseEntity<Reply> createReply(@RequestBody ReplyRequest request) {
        // Use request.getId() instead of request.getReviewId()


         Optional<Review> review = reviewRepository.findById(request.getId());

        if (review.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Reply reply = new Reply(request.getMessage(), review.get());
        return ResponseEntity.ok(replyService.saveReply(reply));
    }

     **/

    @PostMapping("/create")
    public ResponseEntity<Reply> createReply(@RequestBody ReplyRequest request) {
        System.out.println("DEBUG message: " + request.getMessage());
        System.out.println("DEBUG reviewId: " + request.getReviewId());

        System.out.println("Incoming reviewId: " + request.getReviewId());
        Optional<Review> review = reviewRepository.findById(request.getReviewId());

        if (review.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Reply reply = new Reply(request.getMessage(), review.get());
        return ResponseEntity.ok(replyService.saveReply(reply));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable int id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}
