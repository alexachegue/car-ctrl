package com.csc340group6.carctrl.subscription;

import com.csc340group6.carctrl.car.Car;
import com.csc340group6.carctrl.car.CarRepository;
import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.provider.ProviderRepository;
import com.csc340group6.carctrl.services.CarServiceRepository;
import com.csc340group6.carctrl.services.CarService;
import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.user.UserRepository;
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
    private ProviderRepository providerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarServiceRepository serviceRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByUserId(int userId) {
        return appointmentRepository.findAppointmentByUser_UserId(userId);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment createAppointment(Appointment appointment) {
        User user = userRepository.findById(appointment.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(appointment.getCar().getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        CarService service = serviceRepository.findById(appointment.getService().getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));
        Provider provider = providerRepository.findById(appointment.getProvider().getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        appointment.setUser(user);
        appointment.setCar(car);
        appointment.setService(service);
        appointment.setProvider(provider);

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

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }
}