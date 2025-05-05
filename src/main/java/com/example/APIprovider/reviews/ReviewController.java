package com.example.APIprovider.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getByUser(@PathVariable int userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PostMapping("/create")
    public Review createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

//    Provider
@GetMapping("/provider")
public String showProviderReviews(@RequestParam("providerId") int providerId, Model model) {
    List<Review> reviews = reviewService.getReviewsByProviderId(providerId);

    // Calculate stats
    int totalReviews = reviews.size();
    long repliedCount = reviews.stream().filter(r -> r.getReply() != null).count();
    double averageRating = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);

    // Add model attributes for the view
    model.addAttribute("reviews", reviews);
    model.addAttribute("providerId", providerId);
    model.addAttribute("totalReviews", totalReviews);
    model.addAttribute("repliedCount", repliedCount);
    model.addAttribute("averageRating", String.format("%.1f", averageRating));

    return "provider/review-manage";
}
    @GetMapping("/manage")
    public String showReviewDashboard(@RequestParam("providerId") int providerId, Model model) {
        List<Review> reviews = reviewService.getReviewsByProviderId(providerId);
        long repliedCount = reviews.stream().filter(r -> r.getReply() != null).count();
        double avgRating = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);

        model.addAttribute("providerId", providerId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("totalReviews", reviews.size());
        model.addAttribute("repliedCount", repliedCount);
        model.addAttribute("averageRating", String.format("%.1f", avgRating));

        return "provider/review-manage";
    }

}