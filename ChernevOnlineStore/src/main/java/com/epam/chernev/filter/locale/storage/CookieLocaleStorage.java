package com.epam.chernev.filter.locale.storage;

import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.LocaleFilterConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleStorage implements LocaleStorage {

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(LocaleFilterConstants.LANG_COOKIE_ATTRIBUTE, locale);
        int maxAge = Integer.parseInt(request.getServletContext().getInitParameter(ContextConstants.LOCALE_COOKIE_MAX_AGE));
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    @Override
    public String getLocale(HttpServletRequest request) {
        String locale = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(LocaleFilterConstants.LANG_COOKIE_ATTRIBUTE)) {
                locale = cookie.getValue();
            }
        }
        return locale;
    }

}
