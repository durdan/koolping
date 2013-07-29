package com.oceantech.koolping.security;

import com.oceantech.koolping.domain.model.User;
import com.oceantech.koolping.service.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for working with Spring Security.
 */
public class SecurityUtil {

    public static User getLoggedInUser() {
        User user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth;
        if (securityContext != null) {
            auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof UserDetailsImpl) {
                    UserDetailsImpl authUser = (UserDetailsImpl) principal;
                    user = authUser.getUser();
                }
            }
        }
        return user;
    }

    public static Authentication signInUser(User user) {
        List<GrantedAuthority> roles = getRoles(user);
        UserDetailsImpl springSecurityUser = new UserDetailsImpl(user, roles);
        Authentication authentication = new UsernamePasswordAuthenticationToken(springSecurityUser, user.getPassword(), roles);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public static List<GrantedAuthority> getRoles(User user) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new GrantedAuthorityImpl("ROLE_USER"));
        return roles;
    }
}
