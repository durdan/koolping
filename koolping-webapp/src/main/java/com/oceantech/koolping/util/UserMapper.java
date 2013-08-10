package com.oceantech.koolping.util;

import com.oceantech.koolping.command.UserCommand;
import com.oceantech.koolping.domain.model.identity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private final static Log logger = LogFactory.getLog(UserMapper.class);
    public static UserCommand map(User user) {
        UserCommand dto = new UserCommand();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstName());
        dto.setLastname(user.getLastName());
        dto.setUsername(user.getUserName());
        if (user.getRole() == null) {
            logger.info("Role was null setting new role");
            dto.setRole(new Integer(2));
        } else
            dto.setRole(user.getRole().getRole());

        return dto;
    }

    public static List<UserCommand> map(Page<User> users) {
        List<UserCommand> dtos = new ArrayList<UserCommand>();
        for (User user : users) {
            dtos.add(map(user));
        }
        return dtos;
    }
}
