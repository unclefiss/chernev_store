package com.epam.chernev.filter.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Locale;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {

    private final Locale locale;

    public LocaleRequestWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return new Enumeration<Locale>() {
            @Override
            public boolean hasMoreElements() {
                return true;
            }

            @Override
            public Locale nextElement() {
                return locale;
            }
        };
    }
}
