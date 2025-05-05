package com.example.APIprovider.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

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

    @PostMapping("/create")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/update/{id}")
    public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment updated) {
        return appointmentService.updateAppointment(id, updated);
    }

//    @GetMapping("/provider/{providerId}")
//    public List<Appointment> getAppointmentsByProvider(@PathVariable int providerId) {
//        return appointmentService.getAppointmentsByProviderId(providerId);
//    }
//
//    @GetMapping("/provider/{providerId}/status/{status}")
//    public List<Appointment> getAppointmentsByProviderAndStatus(@PathVariable int providerId, @PathVariable String status) {
//        return appointmentService.getAppointmentsByProviderAndStatus(providerId, status);
//    }

//    @GetMapping("/bookings/{providerId}")
//    public String viewProviderBookings(@PathVariable int providerId, Model model) {
//        List<Appointment> appointments = appointmentService.getAppointmentsByProviderId(providerId);
//        model.addAttribute("appointments", appointments);
//        model.addAttribute("providerId", providerId);
//        model.addAttribute("title", "Your Bookings");
//        return "provider/booking-list";
//    }
@GetMapping //("/appointments")
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
