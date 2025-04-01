package carctrladminproject.example.carctrlsysadminproject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyService replyService;
    private final ReviewRepository reviewRepository;

    public ReplyController(ReplyService replyService, ReviewRepository reviewRepository) {
        this.replyService = replyService;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable Long id) {
        Optional<Reply> reply = replyService.getReplyById(id);
        return reply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Reply> getReplyByReviewId(@PathVariable Long reviewId) {
        Optional<Reply> reply = replyService.getReplyByReviewId(reviewId);
        return reply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reply> createReply(@RequestBody ReplyRequest request) {
        Optional<Review> review = reviewRepository.findById(request.getReviewId());

        if (review.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Reply reply = new Reply(request.getMessage(), review.get());
        return ResponseEntity.ok(replyService.saveReply(reply));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.noContent().build();
    }
}
