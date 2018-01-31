package de.nordakademie.iaa.surveyTool.survey.service;


import de.nordakademie.iaa.surveyTool.appointment.model.Appointment;
import de.nordakademie.iaa.surveyTool.appointment.model.AppointmentRepository;
import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.survey.model.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Survey update(Survey appointmentSurvey) {
        Survey appointmentSurveyUpdate = surveyRepository.findOne(appointmentSurvey.getId());
        Survey updatedSurvey = null;

        if (appointmentSurveyUpdate != null) {
            appointmentSurveyUpdate.setDescription(appointmentSurvey.getDescription());
            appointmentSurveyUpdate.setTitle(appointmentSurvey.getTitle());
            appointmentSurveyUpdate.setAppointmentOptions(appointmentSurvey.getAppointmentOptions());
        }
        updatedSurvey = surveyRepository.update(appointmentSurveyUpdate);
        return updatedSurvey;
    }

    @Transactional
    public void delete(Long appointmentSurveyId) {
        Survey appointmentSurvey = surveyRepository.findOne(appointmentSurveyId);
        deleteAppointmentForSurvey(appointmentSurvey);
        surveyRepository.delete(appointmentSurvey);
    };

    private void deleteAppointmentForSurvey(Survey appointmentSurvey) {

        List<Appointment> appointments = appointmentSurvey.getAppointmentOptions();
        for (Appointment appointment: appointments){
            appointmentRepository.delete(appointment);
        }
    };

    @Transactional
    public Survey endSurvey(Long idSurvey) {
        Survey appointmentSurvey = surveyRepository.findOne(idSurvey);
        return surveyRepository.endSurvey(appointmentSurvey);
    }

   /* public Survey participate(Survey appointmentSurvey) {
    }*/
}

