package de.nordakademie.iaa.system.model;

import de.nordakademie.iaa.appointment.model.Appointment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SystemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final System system) {
        entityManager.persist(system);
    }

    public List<System> findAll() {
        return entityManager.createQuery("SELECT r FROM System r", System.class).getResultList();
    }

    public System findOne(final long studentId) {
        return entityManager.find(System.class, studentId);
    }

}
