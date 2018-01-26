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

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User saveUser(@RequestBody final User user) {
        return userService.create(user);
    }
}