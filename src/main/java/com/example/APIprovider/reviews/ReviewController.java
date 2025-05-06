package com.example.APIprovider.reviews;

import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.provider.ProviderRepository;
import com.example.APIprovider.subscription.Appointment;
import com.example.APIprovider.subscription.AppointmentRepository;
import com.example.APIprovider.user.User;
import com.example.APIprovider.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    /**
     * CUSTOMER MVC
     */
    @GetMapping("/new")
    public String showReviewForm(@RequestParam int appointmentId, HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) return "redirect:/users/login-page";

        int userId = (int) userIdObj;

        Review review = new Review();
        review.setAppointment(new Appointment());
        review.getAppointment().setAppointmentId(appointmentId);
        review.setUser(new User());
        review.getUser().setUserId(userId);

        Appointment fullAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        review.setAppointment(fullAppointment);
        review.setProvider(fullAppointment.getProvider());

        model.addAttribute("review", review);
        return "user/review-form";
    }

    @PostMapping("/submit")
    public String submitReview(@ModelAttribute Review review, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) return "redirect:/users/login-page";

        int userId = (int) userIdObj;
        int appointmentId = review.getAppointment().getAppointmentId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        int providerId = review.getProvider().getProviderId();

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        review.setUser(user);
        review.setAppointment(appointment);
        review.setProvider(provider);

        reviewService.createReview(review);
        return "redirect:/appointments/service-history-page";
    }

    @GetMapping("/view/appointment/{appointmentId}")
    public String viewReviewByAppointment(@PathVariable int appointmentId, Model model) {
        Review review = reviewService.getReviewByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("No review found for appointment: " + appointmentId));
        model.addAttribute("review", review);
        return "user/review-details";
    }

    @GetMapping("/respond/{reviewId}")
    public String showProviderResponseForm(@PathVariable int reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "user/provider-review-manage";
    }

    /**
     * PROVIDER MVC
     */
    @PostMapping("/respond/{reviewId}")
    public String respondToReview(@PathVariable int reviewId,
                                  @RequestParam String providerResponse) {
        Review review = reviewService.getReviewById(reviewId);
        review.setProviderResponse(providerResponse);
        reviewService.saveReview(review);
        return "provider/review-manage";
    }

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
    public String showReviewDashboard(HttpSession session, Model model) {
        Object providerIdObj = session.getAttribute("providerId");
        if (providerIdObj == null) return "redirect:/providers/login";

        int providerId = (int) providerIdObj;

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