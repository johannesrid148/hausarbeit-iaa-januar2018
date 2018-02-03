package de.nordakademie.iaa.surveyTool.user.ui;

import de.nordakademie.iaa.surveyTool.exception.WrongAccessDataException;
import de.nordakademie.iaa.surveyTool.survey.model.Survey;
import de.nordakademie.iaa.surveyTool.user.model.User;
import de.nordakademie.iaa.surveyTool.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*Klasse geschrieben von Max Schumann*/
@RestController
@RequestMapping("/backUser")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }

    //Einloggen
    @RequestMapping(method = RequestMethod.GET, path = "/{login}")
    public User checkLogin(@RequestParam String password, @RequestParam String username) throws WrongAccessDataException {
        return (userService.checkAccess(password, username));
    }

    //Erhalte einen einzelnen
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public User getUser(@PathVariable("id") Long userID){
        return  userService.getUser(userID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody final User user) {
        return userService.create(user);
    }

}


