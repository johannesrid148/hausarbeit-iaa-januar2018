package de.nordakademie.iaa.surveyTool.user.service;

import de.nordakademie.iaa.surveyTool.exception.*;
import de.nordakademie.iaa.surveyTool.user.model.User;
import de.nordakademie.iaa.surveyTool.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*Sandra*/

import java.util.List;

/**
 * Service Class for users
 *
 * @author Johannes Ridinger
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to get all users in the database
     *
     * @return a List of all users
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method to get a user
     *
     * @return a user
     */
    @Transactional(readOnly = true)
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }

    /**
     * Method to create a new user
     * Validation of the user attributes will be done
     *
     * @param user to be created
     * @return the created user
     * @throws ObjectAlreadyExistsException      if user with the same name is already in the database
     * @throws RequiredParameterMissingException if one of the parameters needed for the creation of the user is missing
     * @throws NoUserFoundException              if user with the same kennung is already in the database
     */
    @Transactional
    public User create(final User user)
            throws ObjectAlreadyExistsException, RequiredParameterMissingException, NoUserFoundException {
        validateUser(user);
        return userRepository.create(user);
    }

    /**
     * Method to login a user in the database
     * Validation of the user attributes will be done
     *
     * @param enteredKennung Kennung of user to be logged in
     * @param enteredPassword Password of user to be logged in
     * @return the logged in user
     * @throws AnotherUserLoggedInException if another user is already logged in in the database
     * @throws SelfLoggedInException        if the user to be logged in is already logged in
     * @throws WrongPasswordException       if the password entered is worng
     * @throws NoUserFoundException         if no user found for this kennung
     */
    @Transactional
    public User login(final String enteredPassword,final String enteredKennung)
            throws AnotherUserLoggedInException, SelfLoggedInException,WrongPasswordException,NoUserFoundException {
        checkIfPossibleToLogin(enteredPassword,enteredKennung);
        return userRepository.update(userRepository.login(userRepository.findByKennung(enteredKennung)));
    }

    /**
     * Method to logout a user in the database
     * Validation of the user attributes will be done
     *
     * @param enteredKennung Kennung of user to be logged out
     * @return the logged out user
     * @throws SelfLoggedOutException       if the user to be logged out is not logged in
     * @throws NoUserFoundException         if no user found for this kennung
     */
    @Transactional
    public User logout(final String enteredKennung)
            throws SelfLoggedOutException,NoUserFoundException {
        checkIfPossibleToLogout(enteredKennung);
        return userRepository.update(userRepository.logout(userRepository.findByKennung(enteredKennung)));
    }

    /**
     * Method to get all users that are logged in in the database
     *
     * @return a List of all users that are logged in
     * @throws MoreThanOneUserLoggedInException     if there is more than one user logged in
     * @throws NoUserFoundException                 if there is no user logged in in the database
     */
    @Transactional
    public  List<User> getLoggedInUser() throws MoreThanOneUserLoggedInException, NoUserFoundException {
        return userRepository.getLoggedInUser();
    }

    /**
     * Check if any user is logged in the database
     *
     * @return boolean value if there is a user logged in
     */
    @Transactional
    public boolean userLoggedIn() {
        return userRepository.userLoggedIn();
    }

    /**
     * Validates the user attributes and throws exceptions if they are invalid
     *
     * @param user that should be created
     * @throws ObjectAlreadyExistsException      if user with the same name is already in the database
     * @throws RequiredParameterMissingException if one of the parameters needed for the creation of the user is missing
     * @throws NoUserFoundException              if user with the same kennung is already in the database
     */
    private void validateUser(User user)
            throws ObjectAlreadyExistsException, RequiredParameterMissingException, NoUserFoundException {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() || user.getLastName() == null ||
                user.getLastName().isEmpty()) {
            throw new RequiredParameterMissingException(
                    "Für den User muss der vollständige Name angegeben werden.");
        }
        if (!userRepository
                .findByName(user.getFirstName(), user.getLastName())
                .isEmpty()) {
            throw new ObjectAlreadyExistsException("Es existiert bereits ein User mit diesem Namen.");
        }
        if (!userRepository
                .findAllWithKennung(user.getKennung())
                .isEmpty()) {
            throw new ObjectAlreadyExistsException("Es existiert bereits ein User mit dieser Kennung");
        }
    }

    /**
     * Checks if it is possible to log in a user whose kennung and password have been entered
     * throws exceptions if not possible
     *
     * @param enteredKennung Kennung of user that wants to be logged in
     * @param enteredPassword Password of user that wants to be logged in
     * @throws AnotherUserLoggedInException if another user is already logged in in the database
     * @throws SelfLoggedInException        if the user to be logged in is already logged in
     * @throws WrongPasswordException       if the password entered is worng
     * @throws NoUserFoundException         if no user found for this kennung
     */
    private void checkIfPossibleToLogin(String enteredPassword,String enteredKennung)
            throws AnotherUserLoggedInException, SelfLoggedInException,WrongPasswordException,NoUserFoundException {
        User user=userRepository.findByKennung(enteredKennung);
        if (!userRepository
                .checkIfLoggedIn(user.getKennung())
                .isEmpty()) {
            throw new SelfLoggedInException("Sie sind bereits eingeloggt");
        }
        if (!userRepository
                .findLoggedInUsers()
                .isEmpty()) {
            throw new AnotherUserLoggedInException("Es ist bereits ein User eingeloggt");
        }
        if (!user.getPassword().equals(enteredPassword)){
            throw new WrongPasswordException("Passwort falsch");
        }
    }


    /**
     * Checks if it is possible to log out a user by its kennung
     * throws exceptions if not possible
     *
     * @param enteredKennung Kennung of user that wants to be logged out
     * @throws SelfLoggedOutException       if the user to be logged out is not logged in
     * @throws NoUserFoundException         if no user found for this kennung
     */
    private void checkIfPossibleToLogout(String enteredKennung)
            throws SelfLoggedOutException,NoUserFoundException {
        User user=userRepository.findByKennung(enteredKennung);
        if (userRepository
                .checkIfLoggedIn(user.getKennung())
                .isEmpty()) {
            throw new SelfLoggedOutException("Sie sind nicht eingeloggt");
        }
    }
}

