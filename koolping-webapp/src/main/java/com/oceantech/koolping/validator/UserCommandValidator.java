package com.oceantech.koolping.validator;


import com.oceantech.koolping.command.UserCommand;
import com.oceantech.koolping.domain.model.identity.Email;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Component
public class UserCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserCommand command = (UserCommand)o;

        if(isEmpty(command.getFirstname())){
            errors.reject("koolping.firstname.required" , "First name is required.");
        }
        if(isEmpty(command.getLastname())){
            errors.reject("koolping.lastname.required" , "Last name is required.");
        }
        if(isEmpty(command.getEmail())){
            errors.reject("koolping.email.required" , "Email is required.");
        }
        try{
            Email email = new Email(command.getEmail());
        } catch(IllegalArgumentException ex) {
            errors.reject("koolping.email.invalid" , ex.getMessage());
        }
        if(isEmpty(command.getUsername())){
            errors.reject("koolping.username.required" , "Username is required.");
        }
        if(isEmpty(command.getPassword())){
            errors.reject("koolping.password.required" , "Password is required.");
        }

    }
}
