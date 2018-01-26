package de.nordakademie.iaa.appointmentSurvey.ui;

import de.nordakademie.iaa.appointmentSurvey.model.AppointmentSurvey;
import de.nordakademie.iaa.appointmentSurvey.service.AppointmentSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointmentSurvey")
public class AppointmentSurveyController {

    private final AppointmentSurveyService appointmentSurveyService;

    @Autowired
    public AppointmentSurveyController(final AppointmentSurveyService appointmentSurveyService) {
        this.appointmentSurveyService = appointmentSurveyService;
    }

    @GetMapping
    public List<AppointmentSurvey> findAll() {
        return appointmentSurveyService.findAll();
    }

    @PostMapping
    public AppointmentSurvey saveAppointment(@RequestBody final AppointmentSurvey appointment) {
        return appointmentSurveyService.create(appointment);
    }
}
