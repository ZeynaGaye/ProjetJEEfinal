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
    //private String security_password="d4e8faa7-124e-474c-9581-4f2bf5ab7b1a";
    @Autowired
    private final UserService userService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig( UserService userService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.userService = userService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Users users=userService.loadUserByUsername(email);
                Collection<GrantedAuthority> authorities=new ArrayList<>();
                users.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
                });
                return new User(users.getEmail(),users.getMotDePasse(),authorities);
            }
        });
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/administrateurs/**").hasRole("ADMINISTRATEUR")
                        .requestMatchers("/etudiant/**").hasRole("ETUDIANT")
                        .requestMatchers("/enseignant/**").hasRole("ENSEIGNANT")
                        .requestMatchers("/enseignant-responsable/**").hasRole("ENSEIGNANT_RESPONSABLE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                       // .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                      //  .permitAll()

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
