package de.nordakademie.iaa.surveyTool.appointment.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*Klasse geschrieben von Max Schumann*/

@Repository
public class AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Appointment appointment) {
        entityManager.persist(appointment);
    }

    public List<Appointment> findAll() {
        return entityManager.createQuery("SELECT r FROM Appointment r", Appointment.class).getResultList();
    }

    public Appointment findOne(final long studentId) {
        return entityManager.find(Appointment.class, studentId);
    }


    public void delete(Appointment appointment) {
        entityManager.remove(appointment);
    }
}
