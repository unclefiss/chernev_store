package com.epam.chernev.servlet.registration.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionStorage implements Storage {

    @Override
    public void setCaptcha(HttpServletRequest request, HttpServletResponse response, int captchaId) {
        request.getSession().setAttribute("captchaId", Integer.toString(captchaId));
    }

    @Override
    public String getCaptcha(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("captchaId");
    }

}
