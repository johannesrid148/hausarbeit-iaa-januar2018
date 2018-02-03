package de.nordakademie.iaa.surveyTool.user.service;

import de.nordakademie.iaa.surveyTool.exception.WrongAccessDataException;
import de.nordakademie.iaa.surveyTool.user.model.User;
import de.nordakademie.iaa.surveyTool.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*Klasse geschrieben von Max Schumann*/
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

    @Transactional
    public User create(final User user) {
        userRepository.create(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User getUser(Long userID) {
        return userRepository.findOne(userID);
    }

    //Muss der Rückgabewert exemplar der Klasse UserService sein?
    public User checkAccess (String password, String username) throws WrongAccessDataException {
        User user = userRepository.findOneByName(username);
        if (user.getLastName().equals(username)) {
            return user;
        }
        else throw new WrongAccessDataException("Zugang verwährt. Bitte geben sie ihre korrekten Anmeldedaten ein");


    }
}

