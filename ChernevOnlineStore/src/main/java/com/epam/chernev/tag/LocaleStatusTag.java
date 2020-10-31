package com.epam.chernev.tag;

import com.epam.chernev.model.Status;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class LocaleStatusTag extends SimpleTagSupport {

    private String locale;

    private List<Status> collection;

    private long id;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setCollection(List<Status> collection) {
        this.collection = collection;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        for (Status status : collection) {
            if (status.getId().compareTo(id) == 0) {
                if (locale.equalsIgnoreCase("en")) {
                    out.println(status.getName());
                } else if (locale.equalsIgnoreCase("ru")) {
                    out.println(status.getNameRu());
                }
            }
        }
    }


}
