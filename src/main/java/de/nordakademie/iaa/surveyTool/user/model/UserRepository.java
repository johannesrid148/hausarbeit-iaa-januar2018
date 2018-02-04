package de.nordakademie.iaa.surveyTool.user.model;

import de.nordakademie.iaa.surveyTool.exception.AnotherUserLoggedInException;
import de.nordakademie.iaa.surveyTool.exception.MoreThanOneUserLoggedInException;
import de.nordakademie.iaa.surveyTool.exception.NoUserFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User create(final User user) {
        entityManager.persist(user);
        return user;
    }

    public User update(final User user) {
        entityManager.merge(user);
        return user;
    }

    public User login(final User user) {
        User toLogin = findOne(user.getId());
        toLogin.setLoggedIn(TRUE);
        return toLogin;
    }

    public User logout(final User user) {
        User toLogout = findOne(user.getId());
        toLogout.setLoggedIn(FALSE);
        return toLogout;
    }

    public  List<User> getLoggedInUser() throws MoreThanOneUserLoggedInException, NoUserFoundException {
        List<User> loggedIn = entityManager
                .createQuery("SELECT u FROM User u WHERE u.loggedIn = ?1",
                        User.class)
                .setParameter(1, TRUE)
                .getResultList();
        if (loggedIn.isEmpty()) {throw new NoUserFoundException("Kein User eingeloggt");}
        return loggedIn;
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findOne(final long userId) {
        return entityManager.find(User.class, userId);
    }

    public User findByKennung(String kennung) throws NoUserFoundException{
        List<User> foundbyKennung = entityManager
                .createQuery("SELECT u FROM User u WHERE u.kennung = ?1",
                        User.class)
                .setParameter(1, kennung)
                .getResultList();
        if (foundbyKennung.isEmpty()){
            throw new NoUserFoundException("Kein User mit dieser Kennung");
        }
        if (foundbyKennung.size()>1){
            throw new NoUserFoundException("Mehrere User mit dieser Kennung");
        }
        return foundbyKennung.get(0);
    }

    public List<User> findAllWithKennung(String kennung) throws NoUserFoundException{
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.kennung = ?1",
                        User.class)
                .setParameter(1, kennung)
                .getResultList();
    }

    public List<User> findByName(String firstName, String lastName) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2",
                        User.class)
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .getResultList();
    }

    public List<User> findLoggedInUsers() {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.loggedIn = TRUE",
                        User.class)
                .getResultList();
    }

    public List<User> checkIfLoggedIn(String kennung) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.kennung = ?1 AND u.loggedIn = TRUE",
                        User.class)
                .setParameter(1, kennung)
                .getResultList();
    }


}
