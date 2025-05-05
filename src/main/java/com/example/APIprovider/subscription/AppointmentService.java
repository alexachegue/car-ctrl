package com.example.APIprovider.subscription;

import com.example.APIprovider.services.CarService;
import com.example.APIprovider.car.Car;
import com.example.APIprovider.car.CarRepository;
//import com.example.APIprovider.services.Service;
import com.example.APIprovider.services.CarServiceRepository;
import com.example.APIprovider.user.User;
import com.example.APIprovider.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarServiceRepository serviceRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByUserId(int userId) {
        return appointmentRepository.findAppointmentByUser_UserId(userId);
//        return appointmentRepository.findByUser_UserId(userId);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

//    public List<Appointment> getAppointmentsByProviderId(int providerId) {
//        return appointmentRepository.getAppointmentsByProvider(providerId);
//    }
    public List<Appointment> getAppointmentsByProviderId(int providerId) {
        return appointmentRepository.getAppointmentsByProvider(providerId);
}

//
//    public List<Appointment> getAppointmentsByProviderAndStatus(int providerId, String status) {
//        return appointmentRepository.getAppointmentsByProviderAndStatus(providerId, status);
//    }

    public Appointment createAppointment(Appointment appointment) {
        User user = userRepository.findById(appointment.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(appointment.getCar().getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        CarService service = serviceRepository.findById(appointment.getService().getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        appointment.setUser(user);
        appointment.setCar(car);
        appointment.setService(service);

        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(int id, Appointment updated) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            appointment.setDescription(updated.getDescription());
            appointment.setAppointmentDate(updated.getAppointmentDate());
            appointment.setStatus(updated.getStatus());
            return appointmentRepository.save(appointment);
        }
        return null;
    }

//    public Appointment updateAppointmentStatus(int id, String status) {
//        Appointment appointment = getAppointmentById(id);
//        if (appointment != null) {
//            appointment.setStatus(status);
//            return appointmentRepository.save(appointment);
//        }
//        return null;
//    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }
}
