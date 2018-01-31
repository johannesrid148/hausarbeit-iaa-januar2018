package de.nordakademie.iaa.surveyTool.survey.service;


import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.appointment.model.AppointmentRepository;
import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.survey.model.SurveyRepository;
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
        for (Appointment appointment: appointmentsurvey.getAppointmentOptions()) {
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
    public Survey update(Survey survey) {
        Survey surveyUpdate = surveyRepository.findOne(survey.getId());
        if (surveyUpdate != null) {
            surveyUpdate.setDescription(survey.getDescription());
            surveyUpdate.setTitle(survey.getTitle());
            surveyUpdate.setAppointmentOptions(survey.getAppointmentOptions());
        }
        return surveyRepository.update(surveyUpdate);
    }

    @Transactional
    public void delete(Long surveyId) {
        Survey survey = surveyRepository.findOne(surveyId);
        surveyRepository.delete(survey);
    };

    private void deleteAppointmentForSurvey(Survey appointmentSurvey) {

        Set<Appointment> appointments = appointmentSurvey.getAppointmentOptions();
        for (Appointment appointment: appointments){
            appointmentRepository.delete(appointment);
        }
    };

    @Transactional
    public Survey endSurvey(Long idSurvey) {
        Survey survey = surveyRepository.findOne(idSurvey);
        //ativefalse
        return surveyRepository.endSurvey(survey);
    }

   /* public Survey participate(Survey appointmentSurvey) {
    }*/
}

