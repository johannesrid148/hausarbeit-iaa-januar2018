package de.nordakademie.iaa.appointmentSurvey.ui;

import de.nordakademie.iaa.appointment.model.Appointment;
import de.nordakademie.iaa.appointmentSurvey.model.AppointmentSurvey;
import de.nordakademie.iaa.appointmentSurvey.service.AppointmentSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class AppointmentSurveyController {

    private final AppointmentSurveyService appointmentSurveyService;

    @Autowired
    public AppointmentSurveyController(final AppointmentSurveyService appointmentSurveyService) {
        this.appointmentSurveyService = appointmentSurveyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AppointmentSurvey> findAll() {
        return appointmentSurveyService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public AppointmentSurvey getSurvey(@PathVariable("id") Long idSurvey){
        return  appointmentSurveyService.getSurvey(idSurvey);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AppointmentSurvey saveSurvey(@RequestBody final AppointmentSurvey appointmentSurvey) {
        return appointmentSurveyService.create(appointmentSurvey);
    }

    @RequestMapping (method = RequestMethod.PUT)
    public AppointmentSurvey updateSurvey(@RequestBody final AppointmentSurvey appointmentSurvey){
      return  appointmentSurveyService.update(appointmentSurvey);
    }


}
