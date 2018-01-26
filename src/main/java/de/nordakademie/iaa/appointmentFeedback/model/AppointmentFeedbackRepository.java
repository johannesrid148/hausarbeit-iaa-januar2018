package de.nordakademie.iaa.appointmentFeedback.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AppointmentFeedbackRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final AppointmentFeedback appointmentFeedback) {
        entityManager.persist(appointmentFeedback);
    }

    public List<AppointmentFeedback> findAll() {
        return entityManager.createQuery("SELECT r FROM AppointmentFeedback r", AppointmentFeedback.class).getResultList();
    }

    public AppointmentFeedback findOne(final long studentId) {
        return entityManager.find(AppointmentFeedback.class, studentId);
    }

}
