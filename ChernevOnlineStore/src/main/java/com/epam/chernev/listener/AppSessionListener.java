package com.epam.chernev.listener;

import com.epam.chernev.repository.Cart;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AppSessionListener implements HttpSessionListener {

    private int sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount++;
        }
        event.getSession().setAttribute("count", 0);
        event.getSession().setAttribute("cart", new Cart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount--;
        }
    }
}
