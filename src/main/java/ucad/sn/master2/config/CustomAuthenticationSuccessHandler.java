package ucad.sn.master2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        //System.out.println("Roles: " + roles);
        if (roles.contains("ADMINISTRATEUR")) {
            response.sendRedirect("/admin/adminDashboard");
        } else if (roles.contains("ETUDIANT")) {
            response.sendRedirect("/etudiants/etudiantDashboard");
        } else if (roles.contains("ENSEIGNANT")) {
            response.sendRedirect("/enseignant/dashboard");
        } else if (roles.contains("ENSEIGNANT_RESPONSABLE")) {
            response.sendRedirect("/enseignant-responsable/dashboard");
        } else {
            response.sendRedirect("/login");
        }
    }
}
