package com.csc340group6.carctrl.subscription;

import com.csc340group6.carctrl.alert.Alert;
import com.csc340group6.carctrl.car.CarRepository;
import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.provider.ProviderRepository;
import com.csc340group6.carctrl.reviews.Review;
import com.csc340group6.carctrl.reviews.ReviewService;
import com.csc340group6.carctrl.services.CarService;
import com.csc340group6.carctrl.alert.AlertService;
import com.csc340group6.carctrl.services.CarServiceRepository;
import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private AlertService alertService;

    /**
     *  CUSTOMER MVC
     */
    @GetMapping("/form")
    public String showAppointmentForm(@RequestParam("providerId") int providerId,
                                      @RequestParam("serviceId") int serviceId,
                                      HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/users/login-page";
        }

        int userId = (int) session.getAttribute("userId");
        User user = userRepository.findById(userId).orElseThrow();

        Provider provider = providerRepository.findById(providerId).orElseThrow();
        CarService service = serviceRepository.findById(serviceId).orElseThrow();

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setProvider(provider);
        appointment.setService(service);

        model.addAttribute("appointment", appointment);
        model.addAttribute("cars", carRepository.findCarsByUser(userId));
        model.addAttribute("service", service);
        model.addAttribute("providers", providerRepository.findAll());

        return "user/appointment-form";
    }

    @PostMapping("/create")
    public String createAppointmentFromForm(HttpSession session,
                                            @RequestParam int carId,
                                            @RequestParam int serviceId,
                                            @RequestParam int providerId,
                                            @RequestParam String appointmentDate,
                                            @RequestParam(required = false) String description) {
        int userId = (int) session.getAttribute("userId");
        Provider provider = providerRepository.findById(providerId).orElseThrow();

        Appointment appointment = new Appointment();
        appointment.setUser(userRepository.findById(userId).orElseThrow());
        appointment.setCar(carRepository.findById(carId).orElseThrow());
        appointment.setService(serviceRepository.findById(serviceId).orElseThrow());
        appointment.setProvider(providerRepository.findById(providerId).orElseThrow());
        appointment.setAppointmentDate(Timestamp.valueOf(appointmentDate.replace("T", " ") + ":00"));
        appointment.setDescription(description);
        appointment.setStatus(Appointment.Status.Pending);

        appointmentService.createAppointment(appointment);
        alertService.createAlertForUser(provider.getProviderId(), userId, appointment.getAppointmentId(), Alert.AlertType.primary, "Appointment submitted and is pending confirmation.");
        return "redirect:/appointments/confirmation/" + appointment.getAppointmentId();
    }

    @GetMapping("/confirmation/{appointmentId}")
    public String showConfirmation(@PathVariable int appointmentId, HttpSession session, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        User user = (User) session.getAttribute("loggedInUser");

        model.addAttribute("appointment", appointment);
        model.addAttribute("user", user);
        return "user/appointment-confirmation";
    }

    @GetMapping("/service-history-page")
    public String viewAppointmentHistory(HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) return "redirect:/users/login-page";

        int userId = (int) userIdObj;
        List<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId);

        List<Review> reviews = reviewService.getReviewsByUserId(userId);

        Map<String, Review> reviewMap = new HashMap<>();
        for (Review r : reviews) {
            reviewMap.put(String.valueOf(r.getAppointment().getAppointmentId()), r);
        }
        model.addAttribute("reviewMap", reviewMap);
        model.addAttribute("appointments", appointments);
        model.addAttribute("now", new Date());

        return "user/service-history";
    }


    /**
     * PROVIDER MVC
     */
    @GetMapping ("/appointments")
    public String showAppointments(@RequestParam("providerId") int providerId, Model model) {
        List<Appointment> appointments = appointmentService.getAppointmentsByProviderId(providerId);
        model.addAttribute("appointments", appointments);
        model.addAttribute("providerId", providerId);
        model.addAttribute("title", "Appointments");
        return "provider/appointment-list";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

}