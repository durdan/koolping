package com.oceantech.koolping.controller;

import com.oceantech.koolping.domain.model.identity.Role;
import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.security.SecurityUtil;
import com.oceantech.koolping.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Controller for handling the sign up (aka registration) process.
 * <p/>
 * Also handles when a new user signs in via Spring Social. The
 * Spring Social ProviderSignInController will redirect the new
 * user to the GET handler. In this app, a local account will be
 * automatically created for the new user and they will be signed
 * in to the Spring Security SecurityContext.
 */
@Controller
@RequestMapping("/signup")
public class SignUpController {
    private final static Log logger = LogFactory.getLog(SignUpController.class);
    private static final String FORM_VIEW = "signup";
    private static final String SUCCESS_VIEW = "redirect:/";

    @Autowired
    private UserService userService;
    @Autowired
    private SignInAdapter signInAdapter;

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(NativeWebRequest request, @ModelAttribute User user) {
        String view = FORM_VIEW;

        // check if this is a new user signing in via Spring Social
        Connection<?> connection = ProviderSignInUtils.getConnection(request);
        if (connection != null) {
            // populate new User from social connection user profile
            UserProfile userProfile = connection.fetchUserProfile();
            user.setUserName(userProfile.getUsername());
           // user.setUserId(new UserId(userProfile.getUsername()));

            user.setRole(new Role(new Integer("1")));

            // you may want to perform validation on the User object here
            // to check if you are able to get all the data your app requires
            // from the social connection user profile
            // (some providers may not provide email address, for example)
            // if the User is not valid for your app you will need to send the user
            // to your sign up form with some notice that more data is required

            // in this demo app, we assume we get all the data we need
            // the user is automatically registered and signed in
            logger.info("User XXX " + user);
            logger.info("User XXX1 " + user.getRole());
            userService.create(user);

            // finish social signup/login
            ProviderSignInUtils.handlePostSignUp(user.getUserName(), request);

            // sign the user in and send them to the user home page
            signInAdapter.signIn(user.getUserName(), connection, request);
            view = SUCCESS_VIEW;
        }

        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {
        // user signing up via signup form

        // register user
        userService.create(user);

        // sign user in
        SecurityUtil.signInUser(user);

        // send to user home page
        return SUCCESS_VIEW;
    }
}
