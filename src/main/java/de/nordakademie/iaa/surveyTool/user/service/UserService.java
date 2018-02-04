package de.nordakademie.iaa.surveyTool.user.service;

import de.nordakademie.iaa.surveyTool.exception.*;
import de.nordakademie.iaa.surveyTool.user.model.User;
import de.nordakademie.iaa.surveyTool.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*Sandra*/

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }

    @Transactional
    public User create(final User user)
            throws ObjectAlreadyExistsException, RequiredParameterMissingException, NoUserFoundException {
        validateUser(user);
        return userRepository.create(user);
    }

    @Transactional
    public User login(final String enteredPassword,final String enteredKennung)
            throws AnotherUserLoggedInException, SelfLoggedInException,WrongPasswordException,NoUserFoundException {
        checkIfPossibleToLogin(enteredPassword,enteredKennung);
        return userRepository.update(userRepository.login(userRepository.findByKennung(enteredKennung)));
    }

    @Transactional
    public User logout(final String enteredKennung)
            throws SelfLoggedOutException,NoUserFoundException {
        checkIfPossibleToLogout(enteredKennung);
        return userRepository.update(userRepository.logout(userRepository.findByKennung(enteredKennung)));
    }

    @Transactional
    public  List<User> getLoggedInUser() throws MoreThanOneUserLoggedInException, NoUserFoundException {
        return userRepository.getLoggedInUser();
    }

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

    private void checkIfPossibleToLogout(String enteredKennung)
            throws SelfLoggedOutException,NoUserFoundException {
        User user=userRepository.findByKennung(enteredKennung);
        if (userRepository
                .checkIfLoggedIn(user.getKennung())
                .isEmpty()) {
            throw new SelfLoggedOutException("Sie sind nicht eingeloggt");
        }
    }


    /*Muss der Rückgabewert exemplar der Klasse UserService sein?
    public User checkAccess (String password, String username) throws WrongAccessDataException {
        User user = userRepository.findOneByName(username);
        if (user.getLastName().equals(username)) {
            return user;
        }
        else throw new WrongAccessDataException("Zugang verwährt. Bitte geben sie ihre korrekten Anmeldedaten ein");


    }*/
}

