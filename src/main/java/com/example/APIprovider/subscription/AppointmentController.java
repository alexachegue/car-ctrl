package com.example.APIprovider.subscription;

import com.example.APIprovider.alert.Alert;
import com.example.APIprovider.car.CarRepository;
import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.provider.ProviderRepository;
import com.example.APIprovider.reviews.Review;
import com.example.APIprovider.reviews.ReviewService;
import com.example.APIprovider.services.CarService;
import com.example.APIprovider.alert.AlertService;
import com.example.APIprovider.services.CarServiceRepository;
import com.example.APIprovider.user.User;
import com.example.APIprovider.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
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

    @GetMapping
    public String showAppointments(HttpSession session, Model model) {
        Provider provider = (Provider) session.getAttribute("loggedInProvider");
        if (provider == null) {
            return "redirect:/providers/login";
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByProviderId(provider.getProviderId());
        model.addAttribute("appointments", appointments);
        model.addAttribute("providerId", provider.getProviderId());
        model.addAttribute("title", "Appointments");
        return "provider/appointment-list";
    }

    @PostMapping("/accept")
    public String acceptAppointment(@RequestParam("appointmentId") int appointmentId, HttpSession session) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if (appointment == null) return "redirect:/appointments";


        if (appointment.getStatus() == Appointment.Status.Pending) {
            appointment.setStatus(Appointment.Status.Scheduled);
            appointmentService.saveAppointment(appointment);

            // send alert to customer
            int userId = appointment.getUser().getUserId();
            int providerId = appointment.getProvider().getProviderId();
            alertService.createAlertForUser(providerId, userId, appointmentId, Alert.AlertType.success, "Your appointment has been scheduled.");
        }

        return "redirect:/appointments";
    }



    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/user/{userId}")
    public List<Appointment> getAppointmentsByUserId(@PathVariable int userId) {
        return appointmentService.getAppointmentsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable int id) {
        return appointmentService.getAppointmentById(id);
    }


    @PutMapping("/update/{id}")
    public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment updated) {
        return appointmentService.updateAppointment(id, updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
