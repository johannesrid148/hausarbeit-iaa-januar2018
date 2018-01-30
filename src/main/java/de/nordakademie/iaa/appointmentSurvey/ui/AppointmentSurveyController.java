package de.nordakademie.iaa.appointmentSurvey.ui;

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

    //Teilnehmen
    @RequestMapping(method = RequestMethod.POST)
    public AppointmentSurvey participateSurvey(@RequestBody final AppointmentSurvey appointmentSurvey) {
        return appointmentSurveyService.update(appointmentSurvey);
    }

    //Erhalte alle
    @RequestMapping(method = RequestMethod.GET)
    public List<AppointmentSurvey> findAll() {
        return appointmentSurveyService.findAll();
    }

    //Erhalte einen einzelnen
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public AppointmentSurvey getSurvey(@PathVariable("id") Long idSurvey){
        return  appointmentSurveyService.getSurvey(idSurvey);
    }

    //Anlegen mit Infodaten
    @RequestMapping(method = RequestMethod.POST)
    public AppointmentSurvey saveSurvey(@RequestBody final AppointmentSurvey appointmentSurvey) {
        return appointmentSurveyService.create(appointmentSurvey);
    }

    //Anpassen
    @RequestMapping (method = RequestMethod.PUT)
    public AppointmentSurvey updateSurvey(@RequestBody final AppointmentSurvey appointmentSurvey){
      return  appointmentSurveyService.update(appointmentSurvey);
    }

    //Löschen
    @PostMapping
    @RequestMapping ("/delete")
    public void deleteSurvey(@RequestParam("appointmentSurveyId") Long appointmentSurveyId){
        appointmentSurveyService.delete(appointmentSurveyId);
    }

    //Eine Umfrage beenden
    @GetMapping
    @RequestMapping("endSurvey")
    public AppointmentSurvey endSurvey(@PathVariable("id") Long idSurvey){
        return  appointmentSurveyService.endSurvey(idSurvey);
    }

}
