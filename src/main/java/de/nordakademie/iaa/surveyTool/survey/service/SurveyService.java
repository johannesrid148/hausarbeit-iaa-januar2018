package de.nordakademie.iaa.surveyTool.survey.service;


import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.appointment.model.AppointmentRepository;
import de.nordakademie.iaa.surveyTool.exception.ForbiddenUserException;
import de.nordakademie.iaa.surveyTool.exception.SurveyNotFoundException;
import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.survey.model.SurveyRepository;
import de.nordakademie.iaa.surveyTool.user.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
/*Klasse geschrieben von Max Schumann*/
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public SurveyService(final SurveyRepository surveyRepository,
                         AppointmentRepository appointmentRepository) {
        this.surveyRepository = surveyRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Transactional
    public Survey create(final Survey survey) {
        for (Appointment appointment : survey.getAppointmentOptions()) {
            appointmentRepository.create(appointment);
        }
        surveyRepository.create(survey);
        return survey;
    }

    @Transactional(readOnly = true)
    public Survey getSurvey(Long idSurvey) {
        return surveyRepository.findOne(idSurvey);
    }

    /*Cannot deserialize instance of de.nordakademie.iaa.surveyTool.survey.model.Survey out of START_ARRAY token*/
    @Transactional
    public Survey update(Survey survey, String username) throws ForbiddenUserException {
        if (username.equals(survey.getCreator())) {
            Survey surveyUpdate = surveyRepository.findOne(survey.getId());
            if (surveyUpdate != null) {
                surveyUpdate.setDescription(survey.getDescription());
                surveyUpdate.setTitle(survey.getTitle());
                surveyUpdate.setAppointmentOptions(survey.getAppointmentOptions());
            }
            return surveyRepository.update(surveyUpdate);
        }
        else throw new ForbiddenUserException(
                    "Sie sind nicht Ersteller der Umfrage und können diese nicht ändern");
    }

    @Transactional
    public void delete(Long surveyId) {
        Survey survey = surveyRepository.findOne(surveyId);
        surveyRepository.delete(survey);
    }

    private void deleteAppointmentForSurvey(Survey survey) {

        List<Appointment> appointments = survey.getAppointmentOptions();
        for (Appointment appointment : appointments) {
            appointmentRepository.delete(appointment);
        }
    }

    @Transactional
    public Survey endSurvey(Long idSurvey) {
        Survey survey = surveyRepository.findOne(idSurvey);
        return surveyRepository.endSurvey(survey);
    }


    @Transactional
    public Survey attachAppointments(Long idSurvey, List<Appointment> appointment) throws SurveyNotFoundException {
        Survey surveyUpdate = surveyRepository.findOne(idSurvey);
        if (surveyUpdate != null) {
            for (Appointment i: appointment)
             {  appointmentRepository.create(i);
                surveyUpdate.addAppointmentOptions(i);
             }

            return surveyUpdate;
        }
        else throw new SurveyNotFoundException("Diese Umfrage wurde in der Datenbank nicht gefunden");
    }

   /* public Survey participate(Survey appointmentSurvey) {
    }*/
}


