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

/**
 * Class to handle the users in the database
 *
 * @author Johannes Ridinger
 */
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a new user entry in the database
     *
     * @param user to be created
     * @return the created user
     */
    public User create(final User user) {
        entityManager.persist(user);
        return user;
    }

    /**
     * Updates a user entry in the database
     *
     * @param user to be uodated
     * @return the updated user
     */
    public User update(final User user) {
        entityManager.merge(user);
        return user;
    }

    /**
     * Login a user in the database
     *
     * @param user to be logged in
     * @return the logged in user
     */
    public User login(final User user) {
        User toLogin = findOne(user.getId());
        toLogin.setLoggedIn(TRUE);
        return toLogin;
    }

    /**
     * logout a user in the database
     *
     * @param user to be logged out
     * @return the logged out user
     */
    public User logout(final User user) {
        User toLogout = findOne(user.getId());
        toLogout.setLoggedIn(FALSE);
        return toLogout;
    }

    /**
     * Get users that are logged in in the database
     *
     * @return a list of logged in users
     */
    public  List<User> getLoggedInUser() throws MoreThanOneUserLoggedInException, NoUserFoundException {
        List<User> loggedIn = entityManager
                .createQuery("SELECT u FROM User u WHERE u.loggedIn = TRUE",
                        User.class)
                .getResultList();
        if (loggedIn.isEmpty()) {throw new NoUserFoundException("Kein User eingeloggt");}
        return loggedIn;
    }

    /**
     * Check if any user is logged in the database
     *
     * @return boolean value if there is a user logged in
     */
    public boolean userLoggedIn() {
        return findLoggedInUsers().isEmpty();
    }


    /**
     * Get all users entries of the databse
     *
     * @return a list of all users
     */
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    /**
     * Find a specific user by a userID
     *
     * @param userId Id of user that has to be found
     * @return the user with this ID
     */
    public User findOne(final long userId) {
        return entityManager.find(User.class, userId);
    }

    /**
     * Find a specific user by a kennung
     *
     * @param kennung Kennung of user that has to be found
     * @return the user with this kennung
     */
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

    /**
     * Find all specific users by a kennung
     *
     * @param kennung Kennung of users that have to be found
     * @return the users with this kennung
     */
    public List<User> findAllWithKennung(String kennung) throws NoUserFoundException{
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.kennung = ?1",
                        User.class)
                .setParameter(1, kennung)
                .getResultList();
    }


    /**
     * Find all specific users by full name
     *
     * @param firstName First name of users that have to be found
     * @param lastName Last name of users that have to be found
     * @return the users with this full name
     */
    public List<User> findByName(String firstName, String lastName) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2",
                        User.class)
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .getResultList();
    }


    /**
     * Find all users that are logged in in the database
     *
     * @return all logged in Users
     */
    public List<User> findLoggedInUsers() {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.loggedIn = TRUE",
                        User.class)
                .getResultList();
    }

    /**
     * Find all users with a specific kennung that are logged in in the database
     *
     * @param kennung Kennung of users that have to be found
     * @return all logged in Users
     */
    public List<User> checkIfLoggedIn(String kennung) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.kennung = ?1 AND u.loggedIn = TRUE",
                        User.class)
                .setParameter(1, kennung)
                .getResultList();
    }


}
