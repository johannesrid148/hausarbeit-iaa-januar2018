package de.nordakademie.iaa.user.ui;

import de.nordakademie.iaa.user.model.User;
import de.nordakademie.iaa.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
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

