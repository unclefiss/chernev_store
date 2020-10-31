package com.epam.chernev.servlet.shop;

import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.FiltrationConstants;
import com.epam.chernev.constants.ShopConstants;
import com.epam.chernev.constants.Constants;
import com.epam.chernev.constants.Paths;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Product;
import com.epam.chernev.service.ProductService;
import com.epam.chernev.service.StatusService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ShopServlet extends HttpServlet {

    private static final long serialVersionUID = -4650285877986540342L;

    private final Logger log = Logger.getLogger(ShopServlet.class.getName());

    private ProductService productService;

    private StatusService statusService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productService = (ProductService) getServletContext().getAttribute(ContextConstants.PRODUCT_SERVICE);
        statusService = (StatusService) getServletContext().getAttribute(ContextConstants.STATUS_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            log.info("Start");
            resp.setContentType(Constants.RESPONSE_TYPE_TEXT);
            log.info("Read parameters from form");
            FiltrationDTO filtrationDTO = readForm(req);
            log.info("Create product service");
            log.info("Get products from database by pages");
            List<List<Product>> pages = productService.filtrateProducts(filtrationDTO);
            log.info("Set request attribute pages for pages");
            req.setAttribute(ShopConstants.PAGES_REQUEST_ATTRIBUTE, pages);
            if (pages.isEmpty()) {
                log.info("Set request attribute empty to flag that there are no products for these filters");
                req.setAttribute(ShopConstants.NO_PRODUCTS_REQUEST_ATTRIBUTE, true);
            } else {
                log.info("Set request attribute pagesSize for pages size");
                req.setAttribute(ShopConstants.CURRENT_PAGE_REQUEST_ATTRIBUTE, Integer.parseInt(filtrationDTO.getPage()));
                log.info("Set request attribute for products by current page");
                req.setAttribute(ShopConstants.PRODUCTS_REQUEST_ATTRIBUTE, pages.get(Integer.parseInt(filtrationDTO.getPage())));
            }
            req.setAttribute(ShopConstants.CATEGORIES_REQUEST_ATTRIBUTE, statusService.getCategories());
            req.setAttribute(ShopConstants.BRANDS_REQUEST_ATTRIBUTE, statusService.getBrands());
            log.info("Set request attribute filters for all filters");
            req.setAttribute(ShopConstants.FILTERS_REQUEST_ATTRIBUTE, filtrationDTO);
            writeAttributes(req, filtrationDTO);
            log.info("Forward to shop");
            RequestDispatcher rd = req.getRequestDispatcher(Paths.SHOP_FORWARD_PAGE);
            rd.forward(req, resp);
        } catch (NumberFormatException | ServletException | IOException | RepositoryException e) {
            log.warning(e.getMessage());
        }
    }

    FiltrationDTO readForm(HttpServletRequest request) {
        FiltrationDTO filtrationDTO = new FiltrationDTO();
        filtrationDTO.setName(getParameter(request, FiltrationConstants.NAME_FORM));
        filtrationDTO.setCategories(getParameters(request, FiltrationConstants.CATEGORY_FORM));
        filtrationDTO.setFrom(getParameter(request, FiltrationConstants.FROM_FORM));
        filtrationDTO.setTo(getParameter(request, FiltrationConstants.TO_FORM));
        filtrationDTO.setBrands(getParameters(request, FiltrationConstants.BRAND_FORM));
        if (request.getParameter(FiltrationConstants.SORT_BY_FORM) != null && !request.getParameter(FiltrationConstants.SORT_BY_FORM).isEmpty()) {
            filtrationDTO.setPage(getParameter(request, FiltrationConstants.PAGE_FORM));
        } else {
            filtrationDTO.setPage("0");
        }
        if (request.getParameter(FiltrationConstants.SORT_BY_FORM) != null && !request.getParameter(FiltrationConstants.SORT_BY_FORM).isEmpty()) {
            filtrationDTO.setByPage(getParameter(request, FiltrationConstants.BY_PAGE_FORM));
        } else {
            filtrationDTO.setByPage("3");
        }
        if (request.getParameter(FiltrationConstants.SORT_BY_FORM) != null && !request.getParameter(FiltrationConstants.SORT_BY_FORM).isEmpty()) {
            filtrationDTO.setSortBy(request.getParameter(FiltrationConstants.SORT_BY_FORM));
        } else {
            filtrationDTO.setSortBy("0");
        }
        return filtrationDTO;
    }

    String getParameter(HttpServletRequest request, String name) {
        if (request.getParameter(name) != null && !request.getParameter(name).isEmpty()) {
            return request.getParameter(name);
        }
        return null;
    }

    List<String> getParameters(HttpServletRequest request, String name) {
        if (request.getParameter(name) != null && !request.getParameter(name).isEmpty()) {
            List<String> parameters = new ArrayList<>();
            Collections.addAll(parameters, request.getParameterValues(name));
            return parameters;
        }
        return new ArrayList<>();
    }

    void writeAttributes(HttpServletRequest request, FiltrationDTO filtrationDTO) {
        if (filtrationDTO.getName() != null) {
            request.setAttribute(FiltrationConstants.NAME_FORM, filtrationDTO.getName());
        }
        if (!filtrationDTO.getCategories().isEmpty()) {
            request.setAttribute(FiltrationConstants.CATEGORY_FORM, filtrationDTO.getCategories());
        }
        if (filtrationDTO.getFrom() != null) {
            request.setAttribute(FiltrationConstants.FROM_FORM, filtrationDTO.getFrom());
        }
        if (filtrationDTO.getTo() != null) {
            request.setAttribute(FiltrationConstants.TO_FORM, filtrationDTO.getTo());
        }
        if (!filtrationDTO.getBrands().isEmpty()) {
            request.setAttribute(FiltrationConstants.BRAND_FORM, filtrationDTO.getBrands());
        }
        if (filtrationDTO.getByPage() != null) {
            request.setAttribute(FiltrationConstants.BY_PAGE_FORM, filtrationDTO.getByPage());
        }
        if (filtrationDTO.getSortBy() != null) {
            request.setAttribute(FiltrationConstants.SORT_BY_FORM, filtrationDTO.getSortBy());
        }
    }

}
