package com.epam.chernev.servlet.registration;

import com.epam.chernev.model.Role;
import com.epam.chernev.model.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8744051241762024063L;

    private Long id;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String phone;

    private String password;

    private String check;

    private String passwordRepeat;

    private String captcha;

    private String userPicturePath;

    public User extractUser() {
        return new User.Builder().
                addId(id).
                addFirstName(firstName).
                addLastName(lastName).
                addLogin(login).
                addEmail(email).
                addPhone(phone).
                addPassword(password).
                addUserPicturePath(userPicturePath).
                addRole(Role.USER).
                build();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }
}
