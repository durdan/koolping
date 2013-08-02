package com.oceantech.koolping.service;


import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.infrastructure.persistence.UserRepository;
import com.oceantech.koolping.infrastructure.persistence.UserRolesRepository;
import com.oceantech.koolping.security.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RepositoryBasedUserDetailsService implements UserDetailsService {

    private final static Log logger = LogFactory.getLog(RepositoryBasedUserDetailsService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    /**
     * Returns a populated {@link UserDetails} object. The username is first retrieved from
     * the database and then mapped to a {@link UserDetails} object.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User domainUser = userRepository.findByUserName(username);

            if (domainUser == null) {
                throw new UsernameNotFoundException("Could not find user with name '" + username + "'");
            }
            List<GrantedAuthority> roles = SecurityUtil.getRoles(domainUser);
            return new UserDetailsImpl(domainUser, roles);

    }

        /**
         * Retrieves a collection of {@link GrantedAuthority} based on a numerical role.
         *
         * @param role the numerical role
         * @return a collection of {@link GrantedAuthority
         */
        public Collection<? extends GrantedAuthority> getAuthorities (Integer role){
            List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
            return authList;
        }

        /**
         * Converts a numerical role to an equivalent list of roles.
         *
         * @param role the numerical role
         * @return list of roles as as a list of {@link String}
         */
        public List<String> getRoles (Integer role){
            List<String> roles = new ArrayList<String>();

            if (role.intValue() == 1) {
                roles.add("ROLE_USER");
                roles.add("ROLE_ADMIN");

            } else if (role.intValue() == 2) {
                roles.add("ROLE_USER");
            }

            return roles;
        }

        /**
         * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects.
         *
         * @param roles {@link String} of roles
         * @return list of granted authorities
         */
        public static List<GrantedAuthority> getGrantedAuthorities (List < String > roles) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return authorities;
        }
    }