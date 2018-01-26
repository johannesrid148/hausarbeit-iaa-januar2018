package de.nordakademie.iaa.appointmentAssignment.service;

import de.nordakademie.iaa.appointmentAssignment.model.AppointmentAssignment;
import de.nordakademie.iaa.appointmentAssignment.model.AppointmentAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentAssignmentService {

    private final AppointmentAssignmentRepository appointmentAssignmentRepository;

    @Autowired
    public AppointmentAssignmentService(final AppointmentAssignmentRepository appointmentAssignmentRepository) {
        this.appointmentAssignmentRepository = appointmentAssignmentRepository;
    }

    @Transactional(readOnly = true)
    public List<AppointmentAssignment> findAll() {
        return appointmentAssignmentRepository.findAll();
    }

    @Transactional
    public void create(final AppointmentAssignment appointmentAssignment) {
        appointmentAssignmentRepository.create(appointmentAssignment);
    }
}

