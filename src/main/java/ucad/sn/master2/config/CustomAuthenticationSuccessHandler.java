package ucad.sn.master2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        List<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities()).stream().toList();

//      roles.forEach(r->{
//            System.out.println("Les role de l utilisateur " +r);
//        });
        if (roles.contains("ADMINISTRATEUR")) {
            logger.info("Redirecting to /admin/adminDashboard");
            response.sendRedirect("/admin/adminDashboard");
        } else if (roles.contains("ETUDIANT")) {
            response.sendRedirect("/etudiants/etudiantDashboard");
        } else if (roles.contains("ENSEIGNANT")) {
            response.sendRedirect("/enseignant/enseignantDashboard");
        } else if (roles.contains("ENSEIGNANT_RESPONSABLE")) {
            response.sendRedirect("/enseignant_responsable/enseignantresponsableDashboard");
        } else {
            response.sendRedirect("/login");
        }
    }
}
