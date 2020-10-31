package com.epam.chernev.filter.gzip;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipEncodingFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if ((threshold == 0) || (!isGzipSupported(req)) || (!isGzipEligible(req))) {
            chain.doFilter(request, response);
            return;
        }
        GzipResponseWrapper wrappedResponse = new GzipResponseWrapper(res);
        try {
            chain.doFilter(request, wrappedResponse);
        } finally {
            wrappedResponse.finishResponse();
        }
    }

    protected String uriMatch;

    protected int threshold;

    private String[] extensions;

    @Override
    public void init(FilterConfig config) {

        try {
            threshold = Integer.parseInt(config.getInitParameter("threshold"));
        } catch (NumberFormatException nfe) {
            threshold = 0;
        }

        int minThreshold = 128;
        if (threshold < minThreshold) {
            threshold = 0;
        }

        uriMatch = config.getInitParameter("match");
        if ((uriMatch != null) && (!uriMatch.equals("*"))) {
            StringTokenizer st = new StringTokenizer(uriMatch, ",");
            int i = st.countTokens();
            if (i >= 1) {
                extensions = new String[i];
                i = 0;
                while (st.hasMoreTokens()) {
                    extensions[i] = st.nextToken().trim();
                    i++;
                }
            }
        }
    }

    @Override
    public void destroy() {
        extensions = null;
    }

    private boolean isGzipSupported(HttpServletRequest req) {
        String browserEncodings = req.getHeader("Accept-Encoding");
        return (browserEncodings != null) && (browserEncodings.contains("gzip"));
    }

    private boolean isGzipEligible(HttpServletRequest req) {
        String uri = req.getRequestURI();
        if (uri == null) {
            return false;
        }
        boolean result = false;

        if (extensions == null) {
            result = true;
        } else {
            for (String extension : extensions) {
                if (uri.contains(extension)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
