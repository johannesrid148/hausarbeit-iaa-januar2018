package de.nordakademie.iaa.surveyTool.survey.ui;


import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.exception.ForbiddenUserException;
import de.nordakademie.iaa.surveyTool.exception.SurveyNotFoundException;
import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.survey.service.SurveyService;
import de.nordakademie.iaa.surveyTool.user.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/backSurvey")
public class SurveyController {
    /*Klasse geschrieben von Max Schumann*/
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(final SurveyService surveyService) {
        this.surveyService = surveyService;
    }

   /* //Teilnehmen
    @RequestMapping(method = RequestMethod.POST)
    public Survey participateSurvey(@RequestBody final Survey appointmentSurvey) {
        return surveyService.update(appointmentSurvey);
    }*/

    //Erhalte alle
    @RequestMapping(method = RequestMethod.GET)
    public List<Survey> findAll() {
        return surveyService.findAll();
    }

    //Erhalte einen einzelnen
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Survey getSurvey(@PathVariable("id") Long idSurvey){
        return  surveyService.getSurvey(idSurvey);
    }

    //Anlegen mit Infodaten
    @RequestMapping(method = RequestMethod.POST)
    public Survey saveBackSurvey(@RequestBody final Survey survey) {
        return surveyService.create(survey);
    }

    //Appointments bei Speichern hinzufügen
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public Survey attachAppointmentsSurvey(@PathVariable("id") Long idSurvey, @RequestBody Appointment appointment) throws SurveyNotFoundException{
  //  final String JSON_DATA = appointmentStr;
   // final JSONObject obj = new JSONObject(JSON_DATA);
    //Appointment appointment = new Appointment(Collections.singletonList("jfdlkf"));
   // appointment.setStart(obj.getDate("start"));
        return surveyService.attachAppointments(idSurvey, appointment);
}

    //Anpassen
    @RequestMapping(method = RequestMethod.PUT)
    public Survey updateSurvey(@RequestBody final Survey survey, @RequestParam String username) throws ForbiddenUserException {
            return  surveyService.update(survey, username);
        }

    //Löschen
    @RequestMapping(method = RequestMethod.DELETE, path = "/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") Long surveyId){
        surveyService.delete(surveyId);
    }

    //Eine Umfrage beenden
    @GetMapping
    @RequestMapping("endSurvey/{id}")
    public Survey endSurvey(@PathVariable("id") Long idSurvey){
        return  surveyService.endSurvey(idSurvey);
    }
}
