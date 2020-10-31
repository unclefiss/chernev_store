package com.epam.chernev.filter.locale.storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LocaleStorage {

    void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) throws IOException, ServletException;

    String getLocale(HttpServletRequest request);

}
