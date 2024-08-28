package ucad.sn.master2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig(UserService userService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.userService = userService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Users users = userService.loadUserByUsername(email);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                users.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
                });
                return new User(users.getEmail(), users.getMotDePasse(), authorities);
            }
        });
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/uploader/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/enseignant/**").hasAuthority("ENSEIGNANT")
                        .requestMatchers("/etudiant/**").hasAuthority("ETUDIANT")
                        .requestMatchers("/enseignant-responsable/**").hasAuthority("ENSEIGNANT_RESPONSABLE")
                        .anyRequest().authenticated()


                )
                .formLogin(form -> form
                        //.loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                       // .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
