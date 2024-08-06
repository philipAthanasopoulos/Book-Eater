package objectArmy.bookEater.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @author Philip Athanasopoulos
 */
@Configuration
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws java.io.IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        UserProfile user = (UserProfile) authentication.getPrincipal();
        String url = "/login?error=true";

        if (authentication.isAuthenticated()) {
            url = "/homepage";
        }
        return url;
    }
}
