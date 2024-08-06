package objectArmy.bookEater.security.config;

import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Philip Athanasopoulos
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class SecurityConfig {

    private final CustomSuccessHandler customSuccessHandler;
    private final UserService userService;

    //Circular dependency is resolved by using @Lazy
    @Autowired
    public SecurityConfig(CustomSuccessHandler customSuccessHandler, @Lazy UserService userService) {
        this.customSuccessHandler = customSuccessHandler;
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/", "/login", "/register/**").permitAll().requestMatchers("/users/**").authenticated().requestMatchers("/images/*").permitAll().anyRequest().authenticated());

        http.formLogin(fL -> fL.loginPage("/login").failureUrl("/login?error=true").successHandler(customSuccessHandler).usernameParameter("email").passwordParameter("password"));

        http.logout(logOut -> logOut.logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/"));

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
