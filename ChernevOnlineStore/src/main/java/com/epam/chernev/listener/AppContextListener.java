package com.epam.chernev.listener;

import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.filter.locale.storage.CookieLocaleStorage;
import com.epam.chernev.filter.locale.storage.LocaleStorage;
import com.epam.chernev.filter.locale.storage.SessionLocaleStorage;
import com.epam.chernev.service.OrderService;
import com.epam.chernev.service.ProductService;
import com.epam.chernev.service.StatusService;
import com.epam.chernev.service.UserService;
import com.epam.chernev.servlet.registration.captcha.Captcha;
import com.epam.chernev.servlet.registration.captcha.CaptchaTimeout;
import com.epam.chernev.servlet.registration.storage.CookieStorage;
import com.epam.chernev.servlet.registration.storage.SessionStorage;
import com.epam.chernev.servlet.registration.storage.Storage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String storageName = ctx.getInitParameter(ContextConstants.STORAGE_NAME);
        Storage storage = createStorage(storageName);
        ctx.setAttribute(ContextConstants.CAPTCHA_STORAGE, storage);

        String localeStorageName = ctx.getInitParameter(ContextConstants.LOCALE_STORAGE_NAME);
        LocaleStorage localeStorage = createLocaleStorage(localeStorageName);
        ctx.setAttribute(ContextConstants.LOCALE_STORAGE, localeStorage);

        Map<String, Captcha> captchaMap = Collections.synchronizedMap(new HashMap<>());
        ctx.setAttribute(ContextConstants.CAPTCHA_MAP, captchaMap);

        UserService userService = new UserService();
        ctx.setAttribute(ContextConstants.USER_SERVICE, userService);

        ProductService productService = new ProductService();
        ctx.setAttribute(ContextConstants.PRODUCT_SERVICE, productService);

        OrderService orderService = new OrderService();
        ctx.setAttribute(ContextConstants.ORDER_SERVICE, orderService);


        StatusService statusService = new StatusService();
        ctx.setAttribute(ContextConstants.STATUS_SERVICE, statusService);

        Exchanger<Boolean> captchaExchanger = new Exchanger<>();
        ctx.setAttribute(ContextConstants.CAPTCHA_EXCHANGER, statusService);

        Thread captchaTimeOut = new Thread(new CaptchaTimeout(captchaMap, captchaExchanger));
        captchaTimeOut.setDaemon(true);
        captchaTimeOut.start();
    }

    private Storage createStorage(String storageName) {
        if (storageName.equalsIgnoreCase("cookie")) {
            return new CookieStorage();
        } else if (storageName.equalsIgnoreCase("session")) {
            return new SessionStorage();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private LocaleStorage createLocaleStorage(String localeStorageName) {
        if (localeStorageName.equalsIgnoreCase("cookie")) {
            return new CookieLocaleStorage();
        } else if (localeStorageName.equalsIgnoreCase("session")) {
            return new SessionLocaleStorage();
        } else {
            throw new IllegalArgumentException();
        }
    }


}
