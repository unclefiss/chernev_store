package com.epam.chernev.servlet.registration.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Storage {

    void setCaptcha(HttpServletRequest request, HttpServletResponse response, int captchaId);

    String getCaptcha(HttpServletRequest request);

}
