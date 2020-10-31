package com.epam.chernev.tag;

import com.epam.chernev.constants.Paths;
import com.epam.chernev.model.User;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginTag extends SimpleTagSupport {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        if (user == null) {
            out.println(htmlLinkBuilder(Paths.LOGIN_REDIRECT_SERVLET, "Log in"));
            out.println(htmlLinkBuilder(Paths.REGISTRATION_REDIRECT_SERVLET, "Registration"));
        } else {
            out.println(htmlImgBuilder(user.getUserPicturePath()));
            out.println("<big> <span class=\"text-dark mt-3\"> Hello, ");
            out.println(user.getFirstName() + " " + user.getLastName() + "! </span></big>");
            out.println(htmlLinkBuilder(Paths.LOGOUT_REDIRECT_SERVLET, "Logout"));
        }
    }

    private String htmlLinkBuilder(String link, String message) {
        return "<a class=\"text-light mr-2 my-2 text-center btn btn-primary \" href=\"" + link + "\">" + message + "</a>";
    }

    private String htmlImgBuilder(String link) {
        return "<img class=\"rounded my-2\" style=\"width:40px; height:40px; object-fit:cover\" alt=\"userpic\" src=\"" + link + "\">";
    }

}
