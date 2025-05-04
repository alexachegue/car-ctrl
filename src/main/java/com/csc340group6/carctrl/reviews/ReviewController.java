package com.csc340group6.carctrl.reviews;
import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.provider.ProviderRepository;
import com.csc340group6.carctrl.subscription.Appointment;
import com.csc340group6.carctrl.subscription.AppointmentRepository;
import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.user.UserRepository;
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
        return "review-form";
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
        return "review-details";
    }

    @GetMapping("/respond/{reviewId}")
    public String showProviderResponseForm(@PathVariable int reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "provider-review-manage";
    }

    @PostMapping("/respond/{reviewId}")
    public String respondToReview(@PathVariable int reviewId,
                                  @RequestParam String providerResponse) {
        Review review = reviewService.getReviewById(reviewId);
        review.setProviderResponse(providerResponse);
        reviewService.saveReview(review);
        return "home-index";
    }

}