package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Philip Athanasopoulos
 */

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String goToRegisterForm(Model model) {
        model.addAttribute("user", new UserProfile());
        return "register/registerForm";
    }

    @PostMapping("register/registerForm")
    public String registerUser(@ModelAttribute("user") UserProfile user, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        // Validate the passwords
        if (!user.getPassword().equals(repeatPassword)) {
            model.addAttribute("wrongRepeatedPassword", true);
            return "register/registerForm";
        }
        // Everything's fine? Save the user
        userService.saveUser(user);
        model.addAttribute("success", true);
        return "redirect:register/registerForm";
    }

}
