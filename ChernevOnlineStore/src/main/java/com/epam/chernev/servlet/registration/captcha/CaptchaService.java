package com.epam.chernev.servlet.registration.captcha;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Logger;

public class CaptchaService {

    private CaptchaService() {

    }

    private static final Logger log = Logger.getLogger(CaptchaService.class.getName());

    private static final int WIDTH = 150;

    private static final int HEIGHT = 40;

    private static final Color BACKGROUND_COLOR = new Color(51, 51, 153);

    private static final Color TEXT_COLOR = new Color(255, 215, 0);

    private static final Random rand = new Random(System.currentTimeMillis());

    public static BufferedImage drawCaptcha(Captcha captcha) {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        log.info("Drawing captcha");
        setGraphics(g2d);
        drawCaptcha(g2d, captcha.getCaptchaValue());
        return bufferedImage;
    }

    private static void setGraphics(Graphics2D g2d) {
        Font font = new Font("Courier New", Font.BOLD, 18);
        g2d.setFont(font);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        GradientPaint gp = new GradientPaint(0, 0, BACKGROUND_COLOR, 150, 50, Color.GRAY, false);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private static void drawCaptcha(Graphics2D g2d, String captchaValue) {
        g2d.setColor(TEXT_COLOR);
        int x = -15;
        int y;
        int xInterval = 17;
        int yInterval = 24;
        for (int i = 0; i < captchaValue.length(); i++) {
            x += xInterval + (Math.abs(rand.nextInt() % 10));
            y = yInterval + (Math.abs(rand.nextInt() % 15));
            g2d.drawChars(captchaValue.toCharArray(), i, 1, x, y);
        }
        for (int i = 0; i < 3; i++) {
            g2d.drawLine(0, Math.abs(rand.nextInt() % (HEIGHT)), WIDTH, Math.abs(rand.nextInt() % (HEIGHT)));
        }
        for (int i = 0; i < 3; i++) {
            g2d.drawLine(Math.abs(rand.nextInt() % (WIDTH)), 0, Math.abs(rand.nextInt() % (WIDTH)), HEIGHT);
        }
    }

}
