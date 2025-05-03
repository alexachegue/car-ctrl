package com.csc340group6.carctrl.reviews;
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

        review.setUser(user);
        review.setAppointment(appointment);

        reviewService.createReview(review);
        return "redirect:/appointments/service-history-page";
    }


}