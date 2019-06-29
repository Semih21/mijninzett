package com.mijninzet.projectteamdrie.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepo;
    private User user;


    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=true";

        // Fetch the roles from Authentication object
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        //Brahim Code--->
        // Haal de ingelogde email adres op en bepaal de user ID:
        String emailLoginUser = authentication.getName();
       int loggedInUserId = userRepo.getIdLoggedInUser(emailLoginUser);
        System.out.println("de email vd ingelogde persoon is -----> : " + emailLoginUser);
        System.out.println("De id die opgehaald is mbv de authentication.getName : ---->" + loggedInUserId);

        //set  userId in UserSingleton Class
        System.out.println("de count is nu: " +UserSingleton.getInstance().getCount() );
       UserSingleton.getInstance().setId(loggedInUserId);
        System.out.println("de user id die opgehaald is via Singleton gettter  ----> : " + UserSingleton.getInstance().getId());
        System.out.println("de count is nu: " +UserSingleton.getInstance().getCount() );

        //set user met loggedInUserId tbv weergave in de welkomstscherm html
        User.setCurrentUserId(loggedInUserId);
        // end Brahim Code


        // check user role and decide the redirect URL

        url = "/welcomeScreen";


        return url;
    }


}



