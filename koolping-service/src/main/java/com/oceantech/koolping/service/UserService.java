package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.model.User;
import com.oceantech.koolping.infrastructure.persistence.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class UserService {
    private final static Log logger = LogFactory.getLog(UserService.class);
    @Autowired
    private UserRepository repository;

    public User findByLogin(String login) {
        return repository.findByUserName(login);
    }

    public Boolean create(User user) {
        logger.info(" Method Create User ");
        User existingUser = repository.findByUserName(user.getUserName());

        if (existingUser != null) {
            if (existingUser.getPassword() == null) {
                logger.info(" Method Create User (Adding Missing Password)  ");
                String password = generatePassword();
                user.setPassword(password);
                User saved = repository.save(user);
            }
            return false;
        }
        if (user.getPassword() == null) {
            String password = generatePassword();
            user.setPassword(password);
        }

        user.getRole().setUser(user);
        User saved = repository.save(user);
        if (saved == null)
            return false;

        return true;
    }

    public Boolean update(User user) {
        logger.info(" Method Update User ");
        User existingUser = repository.findByUserName(user.getUserName());
        if (existingUser == null)
            return false;

        // Only firstName, lastName, and role fields are updatable
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.getRole().setRole(user.getRole().getRole());
        if (user.getPassword() == null) {
            String password = generatePassword();
            user.setPassword(password);
        }
        User saved = repository.save(existingUser);
        if (saved == null)
            return false;

        return true;
    }

    public Boolean delete(User user) {
        User existingUser = repository.findOne(user.getId());
        if (existingUser == null)
            return false;

        repository.delete(existingUser);
        User deletedUser = repository.findOne(user.getId());
        if (deletedUser != null)
            return false;

        return true;
    }


    private static final String RANDOM_PASSWORD_CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_!$*";

    private static final int RANDOM_PASSWORD_LENGTH = 12;

    private String generatePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < RANDOM_PASSWORD_LENGTH; i++) {
            int charIndex = (int) (Math.random() * RANDOM_PASSWORD_CHARS.length());
            char randomChar = RANDOM_PASSWORD_CHARS.charAt(charIndex);
            password.append(randomChar);
        }
        return password.toString();
    }
}
