package com.epam.chernev.servlet.registration;

import com.epam.chernev.constants.Constants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.RegistrationConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.service.UserService;
import com.epam.chernev.servlet.registration.captcha.Captcha;
import com.epam.chernev.servlet.registration.captcha.CaptchaServlet;
import com.epam.chernev.servlet.registration.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 3594345519020596866L;

    private static final Logger log = Logger.getLogger(RegistrationServlet.class.getName());

    private static final String UPLOAD_DIR = "images/userpics";


    private static final String DEFAULT_USER_PIC_PATH = "images" + File.separator + "userpics" + File.separator + "default.png";

    private UserService userService;

    private Storage storage;

    @Override
    public void init(ServletConfig servletConfig) {
        log.info("Initialization");
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            log.warning(e.getMessage());
        }
        storage = (Storage) servletConfig.getServletContext().getAttribute(ContextConstants.CAPTCHA_STORAGE);
        userService = (UserService) servletConfig.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            log.info("Start");
            response.setContentType(Constants.RESPONSE_TYPE_TEXT);
            HttpSession session = request.getSession();
            log.info("Validation form");
            UserDTO userDTO = readForm(request);
            List<String> errors = validateData(userDTO);

            if (errors.isEmpty() && checkFile(request)) {
                Part part = request.getPart("file");
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

                String fileFormat = getFileFormat(part);
                if (!fileFormat.isEmpty()) {
                    List<String> imageFormats = new ArrayList<>(Arrays.asList("jpeg", "png", "jpg", "gif"));
                    if (!imageFormats.contains(fileFormat)) {
                        errors.add(RegistrationConstants.IMAGE_ERROR);
                    } else {
                        userDTO.setUserPicturePath(saveFile(part, userDTO.getLogin(), uploadFilePath));
                    }
                } else {
                    userDTO.setUserPicturePath(DEFAULT_USER_PIC_PATH);
                }

            }

            log.info("Checking for the existence of a user with this login");
            if (userService.getUserByLogin(userDTO.getLogin()) != null) {
                errors.add(RegistrationConstants.LOGIN_EXIST_ERROR);
            }
            log.info("Captcha validation");
            String captchaId = storage.getCaptcha(request);
            if (!CaptchaServlet.checkCaptcha(captchaId, new Captcha(userDTO.getCaptcha(), 0L))) {
                errors.add(RegistrationConstants.CAPTCHA_ERROR);
            }

            if (errors.isEmpty()) {
                log.info("Creating user in database");
                userService.createUser(userDTO.extractUser());
                log.info("Creating user attribute");
                session.setAttribute("user", userDTO.extractUser());
                log.info("Deleting the value of userDTO and errors attributes");
                session.setAttribute(RegistrationConstants.USER_DTO_SESSION, null);
                session.setAttribute(RegistrationConstants.ERRORS_SESSION, null);
                log.info("Redirect to success page");
                response.sendRedirect(Paths.SUCCESS_REDIRECT_JSP);
            } else {
                log.info("Deleting passwords");
                userDTO.setPassword("");
                userDTO.setPasswordRepeat("");
                log.info("Creating session attributes for userDTO and errors");
                session.setAttribute(RegistrationConstants.USER_DTO_SESSION, userDTO);
                session.setAttribute(RegistrationConstants.ERRORS_SESSION, errors);
                log.info("Forward to registration");
                RequestDispatcher rd = request.getRequestDispatcher(Paths.REGISTRATION_FORWARD_JSP);
                rd.forward(request, response);
            }
        } catch (IOException | NumberFormatException | RepositoryException e) {
            log.warning(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            log.info("Forward to registration");
            response.setContentType(Constants.RESPONSE_TYPE_TEXT);
            RequestDispatcher rd = request.getRequestDispatcher(Paths.REGISTRATION_FORWARD_JSP);
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            log.warning(e.getMessage());
        }
    }


    UserDTO readForm(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(request.getParameter(RegistrationConstants.FIRST_NAME));
        userDTO.setLastName(request.getParameter(RegistrationConstants.LAST_NAME));
        userDTO.setLogin(request.getParameter(RegistrationConstants.LOGIN));
        userDTO.setEmail(request.getParameter(RegistrationConstants.EMAIL));
        userDTO.setPhone(request.getParameter(RegistrationConstants.PHONE));
        userDTO.setPassword(request.getParameter(RegistrationConstants.PASSWORD));
        userDTO.setPasswordRepeat(request.getParameter(RegistrationConstants.PASSWORD_REPEAT));
        userDTO.setCaptcha(request.getParameter(RegistrationConstants.CAPTCHA));
        return userDTO;
    }

    List<String> validateData(UserDTO userDTO) {
        List<String> errors = new ArrayList<>();
        if (!userDTO.getFirstName().matches(RegistrationConstants.NAME_REGEX)) {
            errors.add(RegistrationConstants.FIRST_NAME_ERROR);
        }
        if (!userDTO.getLastName().matches(RegistrationConstants.NAME_REGEX)) {
            errors.add(RegistrationConstants.LAST_NAME_ERROR);
        }
        if (!userDTO.getLogin().matches(RegistrationConstants.LOGIN_REGEX)) {
            errors.add(RegistrationConstants.LOGIN_ERROR);
        }
        if (!userDTO.getEmail().matches(RegistrationConstants.EMAIL_REGEX)) {
            errors.add(RegistrationConstants.EMAIL_ERROR);
        }
        if (!userDTO.getPhone().matches(RegistrationConstants.PHONE_REGEX)) {
            errors.add(RegistrationConstants.PHONE_ERROR);
        }
        if (!userDTO.getPassword().equalsIgnoreCase(userDTO.getPasswordRepeat())) {
            errors.add(RegistrationConstants.PASSWORD_ERROR);
        }
        return errors;
    }

    private String getFileFormat(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename") && token.contains(".")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1).split("\\.")[1];
            }
        }
        return "";
    }

    private boolean checkFile(HttpServletRequest request) throws IOException, ServletException {
        boolean result = false;
        ArrayList<Part> parts = (ArrayList<Part>) request.getParts();
        for (Part part : parts) {
            if (part.getName().equalsIgnoreCase("file")) {
                result = true;
            }
        }
        return result;
    }

    private String saveFile(Part part, String login, String uploadFilePath) {
        String filePath = "";
        try {
            log.info("Writing file");
            String filename = login + "." + getFileFormat(part);
            filePath = uploadFilePath + File.separator + filename;
            part.write(filePath);
            return UPLOAD_DIR + File.separator + filename;
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
        return filePath;
    }

}