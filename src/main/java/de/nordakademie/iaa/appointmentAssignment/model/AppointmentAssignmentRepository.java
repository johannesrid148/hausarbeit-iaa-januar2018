package de.nordakademie.iaa.appointmentAssignment.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AppointmentAssignmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final AppointmentAssignment appointmentAssignment) {
        entityManager.persist(appointmentAssignment);
    }

    public List<AppointmentAssignment> findAll() {
        return entityManager.createQuery("SELECT r FROM AppointmentAssigment r", AppointmentAssignment.class).getResultList();
    }

    public AppointmentAssignment findOne(final int assignmentID) {
        return entityManager.find(AppointmentAssignment.class, assignmentID);
    }

}
