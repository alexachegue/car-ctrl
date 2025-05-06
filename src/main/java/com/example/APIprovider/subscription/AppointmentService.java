package com.example.APIprovider.subscription;

import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.provider.ProviderRepository;
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

    public List<Appointment> getAppointmentsByProviderId(int providerId) {
        return appointmentRepository.getAppointmentsByProvider(providerId);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
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
