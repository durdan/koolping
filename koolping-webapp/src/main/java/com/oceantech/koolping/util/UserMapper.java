package com.oceantech.koolping.util;

import com.oceantech.koolping.command.UserCommand;
import com.oceantech.koolping.domain.model.identity.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserCommand map(User user) {
        UserCommand dto = new UserCommand();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstName());
        dto.setLastname(user.getLastName());
        dto.setUsername(user.getUserName());
        if (user.getRole() == null) {
            dto.setRole(new Integer(1));
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
