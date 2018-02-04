package de.nordakademie.iaa.surveyTool.user.ui;

import de.nordakademie.iaa.surveyTool.exception.*;
import de.nordakademie.iaa.surveyTool.user.model.User;
import de.nordakademie.iaa.surveyTool.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/showUser")
    public List<User> findAll() {
        return userService.findAll();
    }


    //Erhalte einen einzelnen
    @GetMapping
    @RequestMapping("/getUser")
    public User findOne(@RequestParam("userId") Long userID){
        return  userService.findOne(userID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody final User user)
            throws ObjectAlreadyExistsException, RequiredParameterMissingException, NoUserFoundException {
        return userService.create(user);
    }

    @GetMapping
    @RequestMapping("/userLogin")
    public User loginUser(@RequestParam("enteredPassword") String password,
                          @RequestParam("enteredKennung") String kennung)
            throws AnotherUserLoggedInException, SelfLoggedInException,WrongPasswordException,NoUserFoundException {
        return userService.login(password,kennung);
    }

    @GetMapping
    @RequestMapping("/userLogout")
    public User logoutUser(@RequestParam("kennung") String kennung)
            throws SelfLoggedOutException,NoUserFoundException {
        return userService.logout(kennung);
    }

    @GetMapping
    @RequestMapping("/getLoggedInUser")
    public List<User> getLoggedInUser() throws MoreThanOneUserLoggedInException, NoUserFoundException {
        return userService.getLoggedInUser();
    }

    @GetMapping
    @RequestMapping("/userLoggedIn")
    public boolean userLoggedIn(){
        return userService.userLoggedIn();
    }



}


