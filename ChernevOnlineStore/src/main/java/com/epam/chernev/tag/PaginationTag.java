package com.epam.chernev.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class PaginationTag extends SimpleTagSupport {

    private static final String ET_CETERE = "<li><a class=\" btn btn-outline-primary \"> ... </a></li>";

    private static final String UN_ACTIVE_BUTTON_START = "<li><button class=\"btn btn-outline-primary btn-circle\" name=\"page\" value=\"";

    private static final String ACTIVE_BUTTON_START = "<li><button name=\"page\" type=\"submit\" class=\"btn btn-primary btn-circle\" value=\"";

    private static final String PREV_BUTTON_END = "\">&lt;</button></li>";

    private static final String NEXT_BUTTON_END = "\">&gt;</button></li>";

    private static final String DEFAULT_BUTTON_END = "</button></li>";

    private List<List<String>> pages;

    public void setPages(List<List<String>> pages) {
        this.pages = pages;
    }

    private Integer currentPage;

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println(addPrev());
        if (pages.size() > 5) {
            if ((currentPage - 3) < 0) {
                out.println(addPages(0, 5));
                out.println(ET_CETERE);
            } else {
                if ((currentPage + 4) > pages.size()) {
                    out.println(ET_CETERE);
                    out.println(addPages(pages.size() - 5, pages.size()));
                } else {
                    out.println(ET_CETERE);
                    out.println(addPages(currentPage - 2, currentPage + 3));
                    out.println(ET_CETERE);
                }
            }
        } else {
            out.println(addPages(0, pages.size()));
        }
        out.println(addNext());
    }

    private String addPages(int startIndex, int endIndex) {
        StringBuilder pagination = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            if (i == currentPage) {
                pagination.append(ACTIVE_BUTTON_START).append(i).append("\">").append(i + 1).append(DEFAULT_BUTTON_END);
            } else {
                pagination.append(UN_ACTIVE_BUTTON_START).append(i).append("\">").append(i + 1).append(DEFAULT_BUTTON_END);
            }
            pagination.append(System.lineSeparator());
        }
        return pagination.toString();
    }

    private String addPrev() {
        if (currentPage == 0) {
            return UN_ACTIVE_BUTTON_START + 0 + PREV_BUTTON_END;
        } else {
            return UN_ACTIVE_BUTTON_START + (currentPage - 1) + PREV_BUTTON_END;
        }
    }

    private String addNext() {
        if (currentPage == pages.size() - 1) {
            return UN_ACTIVE_BUTTON_START + (pages.size() - 1) + NEXT_BUTTON_END;
        } else {
            return UN_ACTIVE_BUTTON_START + (currentPage + 1) + NEXT_BUTTON_END;
        }
    }
}
