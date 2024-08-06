package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.recommend.BookOfferRecommender;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.BookOfferService;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class HomepageController {

    private final BookOfferService bookOfferService;
    private final UserService userService;
    private final BookOfferRecommender bookOfferRecommender;

    @Autowired
    public HomepageController(BookOfferService bookOfferService, UserService userService, BookOfferRecommender bookOfferRecommender) {
        this.bookOfferService = bookOfferService;
        this.userService = userService;
        this.bookOfferRecommender = bookOfferRecommender;
    }

    @GetMapping("/homepage")
    public String gotoHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("recommendedBookOffers", bookOfferRecommender.getRecommendedOffers(user));
        model.addAttribute("allBookOffers", bookOfferService.getAllBookOffersExceptFor(user.getId()));
        model.addAttribute("user", user);

        return "homepage";
    }

    @GetMapping("/notifications")
    public String gotoNotificationsPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("user", user);

        return "profile/notifications";
    }
}
