package com.oceantech.koolping.controller;


import com.oceantech.koolping.command.UserCommand;
import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.domain.model.identity.UserRoles;
import com.oceantech.koolping.infrastructure.persistence.UserIdRepository;
import com.oceantech.koolping.infrastructure.persistence.UserRepository;
import com.oceantech.koolping.infrastructure.persistence.UserRolesRepository;
import com.oceantech.koolping.validator.UserCommandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private static final String COMMAND = "userCommand";
    private static final String HOME_VIEW = "home";
    private static final String LOGIN_VIEW = "login";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserIdRepository userIdRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserCommandValidator validator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(UserCommand command, ModelMap map, BindingResult result){
        LOGGER.info("Registering a user {}", command);

        validator.validate(command, result);
        if(result.hasErrors()){
            LOGGER.error("Registering a user has errors - {}", result.getAllErrors());
            map.addAttribute("errors", result.getAllErrors());
            map.addAttribute(COMMAND, new UserCommand());
        } else {
            User user = command.convertToUser();
            user.setUserId(userIdRepository.nextIdentity());
            User savedUser = userRepository.save(user);
            UserRoles roles = createUserRoles(savedUser.getId());
            map.addAttribute("user", savedUser);
            map.addAttribute("roles", roles);
            return HOME_VIEW;
        }
        return LOGIN_VIEW;
    }

    private UserRoles createUserRoles(Long userId){
        UserRoles roles = new UserRoles();
        roles.setUserId(userId);
        roles.setAuthority("ROLE_USER");
        return userRolesRepository.save(roles);
    }
}
