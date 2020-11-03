package com.epam.chernev.filter.locale;

import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.LocaleFilterConstants;
import com.epam.chernev.filter.locale.storage.LocaleStorage;

import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class LocaleFilter implements Filter {

    private final Logger log = Logger.getLogger(LocaleFilter.class.getName());

    private List<Locale> locales;

    private Locale defaultLocale;

    private LocaleStorage localeStorage;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse resp = (HttpServletResponse) response;

        ServletRequestWrapper localeRequestWrapper = new LocaleRequestWrapper(req, chooseLocale(request));

        if (req.getParameter(LocaleFilterConstants.LANG_REQUEST_PARAMETER) != null) {
            log.info("Found lang parameter in request");
            localeStorage.setLocale(req, resp, req.getParameter(LocaleFilterConstants.LANG_REQUEST_PARAMETER));
            localeRequestWrapper = new LocaleRequestWrapper(req, new Locale(localeStorage.getLocale(req)));
        } else {
            if (localeStorage.getLocale(req) != null && !localeStorage.getLocale(req).isEmpty()) {
                log.info("Found lang attribute in session");
                localeRequestWrapper = new LocaleRequestWrapper(req, new Locale(localeStorage.getLocale(req)));
            } else {
                log.info("Set most suitable locale for web-application");
                localeStorage.setLocale(req, resp, chooseLocale(req).toString());
            }
        }
        chain.doFilter(localeRequestWrapper, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Initialization");
        locales = new ArrayList<>();
        log.info("Get locale from filter config");
        defaultLocale = new Locale(filterConfig.getInitParameter(LocaleFilterConstants.FILTER_INIT_PARAM_DEFAULT_LOCALE));
        String[] localeParams = filterConfig.getInitParameter(LocaleFilterConstants.FILTER_INIT_PARAM_LOCALES).split(",");
        for (String localeName : localeParams) {
            locales.add(new Locale(localeName));
        }

        localeStorage = (LocaleStorage) filterConfig.getServletContext().getAttribute(ContextConstants.LOCALE_STORAGE);
    }

    private Locale chooseLocale(ServletRequest req) {
        if (locales.contains(req.getLocale())) {
            return req.getLocale();
        }
        Enumeration<Locale> requestLocales = req.getLocales();
        while (requestLocales.hasMoreElements()) {
            Locale currentLocale = requestLocales.nextElement();
            if (locales.contains(currentLocale)) {
                return currentLocale;
            }
        }
        return defaultLocale;
    }

}