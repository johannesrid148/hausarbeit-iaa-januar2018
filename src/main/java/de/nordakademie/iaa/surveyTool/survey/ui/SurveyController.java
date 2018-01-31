package de.nordakademie.iaa.surveyTool.survey.ui;


import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backSurvey")
public class SurveyController {

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

    //Anpassen
    @RequestMapping(method = RequestMethod.PUT)
    public Survey updateSurvey(@RequestBody final Survey survey){
      return  surveyService.update(survey);
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
