package de.nordakademie.iaa.surveyTool.appointment.ui;

import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*Klasse geschrieben von Max Schumann*/
@RestController
@RequestMapping("/backAppointment")

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

    /*@RequestMapping(method = RequestMethod.POST)
    public Appointment saveAppointment(@RequestBody final Appointment appointment) {
        return appointmentService.create(appointment);
    }*/
}