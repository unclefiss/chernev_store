package com.epam.chernev.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -557998206622118892L;

    private Long id;

    private Role role;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String phone;

    private String password;

    private String userPicturePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", userPicturePath='" + userPicturePath + '\'' +
                '}';
    }

    public static class Builder {

        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder addId(Long id) {
            user.setId(id);
            return this;
        }

        public Builder addRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder addFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder addLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder addLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder addEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder addPhone(String phone) {
            user.setPhone(phone);
            return this;
        }

        public Builder addPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder addUserPicturePath(String userPicturePath) {
            user.setUserPicturePath(userPicturePath);
            return this;
        }

        public User build() {
            return user;
        }

    }


}
