package com.oceantech.koolping.social.web;

import com.oceantech.koolping.domain.model.User;
import com.oceantech.koolping.security.SecurityUtil;
import com.oceantech.koolping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInAdapterImpl implements SignInAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenBasedRememberMeServices tokenBasedRememberMeServices;

    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        User user = userService.findByLogin(userId);
        Authentication authentication = SecurityUtil.signInUser(user);

        // set remember-me cookie
        tokenBasedRememberMeServices.onLoginSuccess(
                (HttpServletRequest) request.getNativeRequest(),
                (HttpServletResponse) request.getNativeResponse(),
                authentication);

        return null;
    }
}
