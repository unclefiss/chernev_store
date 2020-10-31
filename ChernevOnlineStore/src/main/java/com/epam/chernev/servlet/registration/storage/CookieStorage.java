package com.epam.chernev.servlet.registration.storage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieStorage implements Storage {

    @Override
    public void setCaptcha(HttpServletRequest request, HttpServletResponse response, int captchaId) {
        Cookie cookie = new Cookie("captchaId", Integer.toString(captchaId));
        response.addCookie(cookie);
    }

    @Override
    public String getCaptcha(HttpServletRequest request) {
        String captchaId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("captchaId")) {
                captchaId = cookie.getValue();
            }
        }
        return captchaId;
    }

}
