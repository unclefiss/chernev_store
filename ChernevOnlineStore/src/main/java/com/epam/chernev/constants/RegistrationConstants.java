package com.epam.chernev.constants;

public class RegistrationConstants {

    private RegistrationConstants() {

    }

    public static final String FIRST_NAME = "first-name";

    public static final String LAST_NAME = "last-name";

    public static final String LOGIN = "login";

    public static final String EMAIL = "email-address";

    public static final String PHONE = "phone-number";

    public static final String PASSWORD = "password";

    public static final String PASSWORD_REPEAT = "password-repeat";

    public static final String CAPTCHA = "captcha";

    public static final String FIRST_NAME_ERROR = "Please write your first name correct";

    public static final String LAST_NAME_ERROR = "Please write your last name correct";

    public static final String LOGIN_ERROR = "Please write your login correct";

    public static final String LOGIN_EXIST_ERROR = "User with this login is already exists";

    public static final String EMAIL_ERROR = "Please write your email correct";

    public static final String PHONE_ERROR = "Please write your phone number correct";

    public static final String PASSWORD_ERROR = "Please write your password correct";

    public static final String CAPTCHA_ERROR = "Please rewrite captcha";

    public static final String IMAGE_ERROR = "Please attach picture file";

    public static final String NAME_REGEX = "^([^;:=+<>\"']+)$";

    public static final String LOGIN_REGEX = "^([^\"']+)$";

    public static final String EMAIL_REGEX = "^.+@.+\\..+$";

    public static final String PHONE_REGEX = "^\\+38 \\d{3} \\d{3} \\d{4}$";

    public static final String USER_DTO_SESSION = "userDTO";

    public static final String ERRORS_SESSION = "errors";
}
