package com.epam.chernev.filter.locale.storage;

import com.epam.chernev.constants.LocaleFilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionLocaleStorage implements LocaleStorage {

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        request.getSession().setAttribute(LocaleFilterConstants.LANG_SESSION_ATTRIBUTE, locale);
    }

    @Override
    public String getLocale(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(LocaleFilterConstants.LANG_SESSION_ATTRIBUTE);
    }

}
