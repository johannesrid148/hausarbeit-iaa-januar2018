package de.nordakademie.iaa.surveyTool.user.ui;

import de.nordakademie.iaa.surveyTool.exception.*;
import de.nordakademie.iaa.surveyTool.user.model.User;
import de.nordakademie.iaa.surveyTool.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for users
 *
 * @author Johannes Ridinger
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to get all user entries in the database
     *
     * @return a List of all user entries
     */
    @GetMapping
    @RequestMapping("/showUser")
    public List<User> findAll() {
        return userService.findAll();
    }


    /**
     * Method to get a specific user entry from the database
     *
     * @param userID userId of specific user of the database
     * @return a user
     */
    @GetMapping
    @RequestMapping("/getUser")
    public User findOne(@RequestParam("userId") Long userID){
        return  userService.findOne(userID);
    }

    /**
     * Method to create a new user entry
     * Validation of user attributes will be done
     *
     * @param user that has to be created
     * @return the created user
     * @throws ObjectAlreadyExistsException      if user with the same name is already in the database
     * @throws RequiredParameterMissingException if one of the parameters needed for the creation of the user is missing
     * @throws NoUserFoundException              if user with the same kennung is already in the database
     */
    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody final User user)
            throws ObjectAlreadyExistsException, RequiredParameterMissingException, NoUserFoundException {
        return userService.create(user);
    }

    /**
     * Method to login a user
     * Validation of the user attributes will be done
     *
     * @param kennung Kennung of user to be logged in
     * @param password Password of user to be logged in
     * @return the logged in user
     * @throws AnotherUserLoggedInException if another user is already logged in in the database
     * @throws SelfLoggedInException        if the user to be logged in is already logged in
     * @throws WrongPasswordException       if the password entered is worng
     * @throws NoUserFoundException         if no user found for this kennung
     */
    @GetMapping
    @RequestMapping("/userLogin")
    public User loginUser(@RequestParam("enteredPassword") String password,
                          @RequestParam("enteredKennung") String kennung)
            throws AnotherUserLoggedInException, SelfLoggedInException,WrongPasswordException,NoUserFoundException {
        return userService.login(password,kennung);
    }

    /**
     * Method to logout a user
     * Validation of the user attributes will be done
     *
     * @param kennung Kennung of user to be logged out
     * @return the logged out user
     * @throws SelfLoggedOutException       if the user to be logged out is not logged in
     * @throws NoUserFoundException         if no user found for this kennung
     */
    @GetMapping
    @RequestMapping("/userLogout")
    public User logoutUser(@RequestParam("kennung") String kennung)
            throws SelfLoggedOutException,NoUserFoundException {
        return userService.logout(kennung);
    }

    /**
     * Method to get all users that are logged in
     *
     * @return a List of all users that are logged in
     * @throws MoreThanOneUserLoggedInException     if there is more than one user logged in
     * @throws NoUserFoundException                 if there is no user logged in in the database
     */
    @GetMapping
    @RequestMapping("/getLoggedInUser")
    public List<User> getLoggedInUser() throws MoreThanOneUserLoggedInException, NoUserFoundException {
        return userService.getLoggedInUser();
    }

    /**
     * Check if any user is logged in the database
     *
     * @return boolean value if there is a user logged in
     */
    @GetMapping
    @RequestMapping("/userLoggedIn")
    public boolean userLoggedIn(){
        return userService.userLoggedIn();
    }

}


