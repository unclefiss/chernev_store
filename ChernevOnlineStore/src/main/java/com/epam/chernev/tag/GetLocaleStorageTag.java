package com.epam.chernev.tag;

import com.epam.chernev.constants.LocaleFilterConstants;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetLocaleStorageTag extends SimpleTagSupport {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println(getLocale(name));
    }


    private String getLocale(String name) {
        if (name.equalsIgnoreCase("cookie")) {
            return "${cookie['" + LocaleFilterConstants.LANG_COOKIE_ATTRIBUTE + "']}";
        } else if (name.equalsIgnoreCase("session")) {
            return "${" + LocaleFilterConstants.LANG_SESSION_ATTRIBUTE + "}";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
