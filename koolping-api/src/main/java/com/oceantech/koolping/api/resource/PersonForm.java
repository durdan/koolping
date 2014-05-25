package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Sanjoy Roy
 */
public class PersonForm extends ResourceSupport {

    private String username = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String birthDate = "";
    private String gender = "";
    private String facebookId = "";
    private String twitterId = "";
    private String googleplusId = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getGoogleplusId() {
        return googleplusId;
    }

    public void setGoogleplusId(String googleplusId) {
        this.googleplusId = googleplusId;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", twitterId='" + twitterId + '\'' +
                ", googleplusId='" + googleplusId + '\'' +
                '}';
    }
}
