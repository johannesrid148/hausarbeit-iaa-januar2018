package de.nordakademie.iaa.appointmentFeedback.service;

import de.nordakademie.iaa.appointmentFeedback.model.AppointmentFeedback;
import de.nordakademie.iaa.appointmentFeedback.model.AppointmentFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentFeedbackService {

    private final AppointmentFeedbackRepository appointmentRepository;

    @Autowired
    public AppointmentFeedbackService(final AppointmentFeedbackRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional(readOnly = true)
    public List<AppointmentFeedback> findAll() {
        return appointmentRepository.findAll();
    }

    @Transactional
    public void create(final AppointmentFeedback appointmentFeedback) {
        appointmentRepository.create(appointmentFeedback);
    }
}

