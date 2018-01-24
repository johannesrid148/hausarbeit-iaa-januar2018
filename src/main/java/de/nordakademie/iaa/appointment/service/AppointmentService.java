package de.nordakademie.iaa.appointment.service;

import de.nordakademie.iaa.appointment.model.Appointment;
import de.nordakademie.iaa.appointment.model.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(final AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Transactional
    public void create(final Appointment appointment) {
        appointmentRepository.create(appointment);
    }
}

