package de.nordakademie.iaa.appointment.ui;

import de.nordakademie.iaa.appointment.model.Appointment;
import de.nordakademie.iaa.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @PostMapping
    public Appointment saveAppointment(@RequestBody final Appointment appointment) {
        return appointmentService.create(appointment);
    }
}