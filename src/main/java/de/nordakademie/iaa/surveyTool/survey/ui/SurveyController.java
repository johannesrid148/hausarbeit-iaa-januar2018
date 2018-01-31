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
    public Survey saveBackSurvey(@RequestBody final Survey appointmentSurvey) {
        return surveyService.create(appointmentSurvey);
    }

    //Anpassen
    @RequestMapping(method = RequestMethod.PUT)
    public Survey updateSurvey(@RequestBody final Survey appointmentSurvey){
      return  surveyService.update(appointmentSurvey);
    }

    //Löschen
    @PostMapping
    @RequestMapping("/delete")
    public void deleteSurvey(@RequestParam("appointmentSurveyId") Long appointmentSurveyId){
        surveyService.delete(appointmentSurveyId);
    }

    //Eine Umfrage beenden
    @GetMapping
    @RequestMapping("endSurvey")
    public Survey endSurvey(@PathVariable("id") Long idSurvey){
        return  surveyService.endSurvey(idSurvey);
    }

}
