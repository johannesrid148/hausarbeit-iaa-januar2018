package de.nordakademie.iaa.appointment.ui;

import de.nordakademie.iaa.appointment.model.Appointment;
import de.nordakademie.iaa.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Appointment saveAppointment(@RequestBody final Appointment appointment) {
        return appointmentService.create(appointment);
    }
}