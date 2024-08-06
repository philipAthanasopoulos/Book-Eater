package objectArmy.bookEater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String gotoLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String accessPage() {
        return "login";
    }
}
