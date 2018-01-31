package de.nordakademie.iaa.surveyTool.user.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final User user) {
        entityManager.persist(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT r FROM User r", User.class).getResultList();
    }

    public User findOne(final long userId) {
        return entityManager.find(User.class, userId);
    }

}
