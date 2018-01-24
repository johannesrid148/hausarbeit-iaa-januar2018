package de.nordakademie.iaa.appointmentSurvey.service;

import de.nordakademie.iaa.appointmentSurvey.model.AppointmentSurvey;
import de.nordakademie.iaa.appointmentSurvey.model.AppointmentSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentSurveyService {

    private final AppointmentSurveyRepository appointmentSurveyRepository;

    @Autowired
    public AppointmentSurveyService(final AppointmentSurveyRepository appointmentSurveyRepository) {
        this.appointmentSurveyRepository = appointmentSurveyRepository;
    }

    @Transactional(readOnly = true)
    public List<AppointmentSurvey> findAll() {
        return appointmentSurveyRepository.findAll();
    }

    @Transactional
    public void create(final AppointmentSurvey appointmentsurvey) {
        appointmentSurveyRepository.create(appointmentsurvey);
    }
}

