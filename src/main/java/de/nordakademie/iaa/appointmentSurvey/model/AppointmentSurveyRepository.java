package de.nordakademie.iaa.appointmentSurvey.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AppointmentSurveyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final AppointmentSurvey appointmentSurvey) {
        entityManager.persist(appointmentSurvey);
    }

    public List<AppointmentSurvey> findAll() {
        return entityManager.createQuery("SELECT r FROM AppointmentSurvey r", AppointmentSurvey.class).getResultList();
    }

    public AppointmentSurvey findOne(final long surveyId) {
        return entityManager.find(AppointmentSurvey.class, surveyId);
    }

}
