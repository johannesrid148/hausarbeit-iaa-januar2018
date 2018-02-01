package de.nordakademie.iaa.surveyTool.survey.service;


import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.appointment.model.AppointmentRepository;
import de.nordakademie.iaa.surveyTool.exception.ForbiddenUserException;
import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.survey.model.SurveyRepository;
import de.nordakademie.iaa.surveyTool.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
    public Survey create(final Survey appointmentsurvey) {
        for (Appointment appointment : appointmentsurvey.getAppointmentOptions()) {
            appointmentRepository.create(appointment);
        }
        surveyRepository.create(appointmentsurvey);
        return appointmentsurvey;
    }

    @Transactional(readOnly = true)
    public Survey getSurvey(Long idSurvey) {
        return surveyRepository.findOne(idSurvey);
    }

    @Transactional
    public Survey update(Survey survey, User user) throws ForbiddenUserException {
        if (user.getLastName() == survey.getCreator()) {
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

        Set<Appointment> appointments = survey.getAppointmentOptions();
        for (Appointment appointment : appointments) {
            appointmentRepository.delete(appointment);
        }
    }

    @Transactional
    public Survey endSurvey(Long idSurvey) {
        Survey survey = surveyRepository.findOne(idSurvey);
        return surveyRepository.endSurvey(survey);
    }

   /* public Survey participate(Survey appointmentSurvey) {
    }*/
}

