package com.epam.chernev.servlet.registration.captcha;

import com.epam.chernev.constants.Constants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.servlet.registration.storage.Storage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 6342578800693338338L;

    private static final Random rand = new Random(System.currentTimeMillis());

    private static final Logger log = Logger.getLogger(CaptchaServlet.class.getName());

    private static Map<String, Captcha> captchaMap;

    private Long timeout;

    private Storage storage;

    private Exchanger<Boolean> captchaExchanger;

    @Override
    public void init(ServletConfig servletConfig) {
        log.info("Initialization");
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            log.warning(e.getMessage());
        }
        captchaMap = (Map<String, Captcha>) servletConfig.getServletContext().getAttribute(ContextConstants.CAPTCHA_MAP);
        timeout = Long.parseLong(servletConfig.getInitParameter("timeout"));
        storage = (Storage) servletConfig.getServletContext().getAttribute(ContextConstants.CAPTCHA_STORAGE);
        captchaExchanger = (Exchanger<Boolean>) servletConfig.getServletContext().getAttribute(ContextConstants.CAPTCHA_EXCHANGER);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        try (OutputStream os = response.getOutputStream()) {
            log.info("Generation captcha info");
            String captchaValue = generateCaptchaString();
            int captchaId = captchaMap.size() + 1;
            Captcha captcha = new Captcha(captchaValue, timeout);
            if (captchaMap.isEmpty()) {
                captchaExchanger.exchange(true);
            }
            captchaMap.put(Integer.toString(captchaId), captcha);
            log.info("Setting captcha info");
            storage.setCaptcha(request, response, captchaId);
            log.info("Creating image object");
            BufferedImage bufferedImage = CaptchaService.drawCaptcha(captcha);
            log.info("Generation response");
            response.setContentType(Constants.RESPONSE_TYPE_PNG);
            ImageIO.write(bufferedImage, "png", os);
            os.flush();
        } catch (IOException | InterruptedException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public static boolean checkCaptcha(String id, Captcha captcha) {
        if (captchaMap.containsKey(id)) {
            return captchaMap.get(id).getCaptchaValue().equalsIgnoreCase(captcha.getCaptchaValue());
        } else {
            return false;
        }
    }

    private String generateCaptchaString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            string.append(Math.abs(rand.nextInt() % 10));
        }
        return string.toString();
    }

}