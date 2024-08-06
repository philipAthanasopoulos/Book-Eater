package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserProfile user = userService.getUserById(id);
        model.addAttribute("userOfThisProfile", user);
        return "profile/viewProfile";
    }
}
