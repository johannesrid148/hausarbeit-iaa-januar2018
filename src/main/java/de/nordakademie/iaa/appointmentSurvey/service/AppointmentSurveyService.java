package de.nordakademie.iaa.appointmentSurvey.service;

import de.nordakademie.iaa.appointment.model.Appointment;
import de.nordakademie.iaa.appointment.model.AppointmentRepository;
import de.nordakademie.iaa.appointmentSurvey.model.AppointmentSurvey;
import de.nordakademie.iaa.appointmentSurvey.model.AppointmentSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentSurveyService {

    private final AppointmentSurveyRepository appointmentSurveyRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentSurveyService(final AppointmentSurveyRepository appointmentSurveyRepository,
                                    AppointmentRepository appointmentRepository) {
        this.appointmentSurveyRepository = appointmentSurveyRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional(readOnly = true)
    public List<AppointmentSurvey> findAll() {
        return appointmentSurveyRepository.findAll();
    }

    @Transactional
    public AppointmentSurvey create(final AppointmentSurvey appointmentsurvey) {
        for (Appointment appointment: appointmentsurvey.getAppointmentOptions()) {
            appointmentRepository.create(appointment);
        }
        appointmentSurveyRepository.create(appointmentsurvey);
        return appointmentsurvey;
    }

    @Transactional(readOnly = true)
    public AppointmentSurvey getSurvey(Long idSurvey) {
        return appointmentSurveyRepository.findOne(idSurvey);
    }

    @Transactional
    public AppointmentSurvey update(AppointmentSurvey appointmentSurvey) {
        AppointmentSurvey appointmentSurveyUpdate = appointmentSurveyRepository.findOne(appointmentSurvey.getId());
        AppointmentSurvey updatedAppointmentSurvey = null;

        if (appointmentSurveyUpdate != null) {
            appointmentSurveyUpdate.setDescription(appointmentSurvey.getDescription());
            appointmentSurveyUpdate.setTitle(appointmentSurvey.getTitle());
            appointmentSurveyUpdate.setAppointmentOptions(appointmentSurvey.getAppointmentOptions());
        }
        updatedAppointmentSurvey = appointmentSurveyRepository.update(appointmentSurveyUpdate);
        return updatedAppointmentSurvey;
    }

    @Transactional
    public void delete(Long appointmentSurveyId) {
        AppointmentSurvey appointmentSurvey = appointmentSurveyRepository.findOne(appointmentSurveyId);
        deleteAppointmentForSurvey(appointmentSurvey);
        appointmentSurveyRepository.delete(appointmentSurvey);
    };

    private void deleteAppointmentForSurvey(AppointmentSurvey appointmentSurvey) {

        List<Appointment> appointments = appointmentSurvey.getAppointmentOptions();
        for (Appointment appointment: appointments){
            appointmentRepository.delete(appointment);
        }
    };

    @Transactional
    public AppointmentSurvey endSurvey(Long idSurvey) {
        AppointmentSurvey appointmentSurvey = appointmentSurveyRepository.findOne(idSurvey);
        return appointmentSurveyRepository.endSurvey(appointmentSurvey);
    }

   /* public AppointmentSurvey participate(AppointmentSurvey appointmentSurvey) {
    }*/
}

