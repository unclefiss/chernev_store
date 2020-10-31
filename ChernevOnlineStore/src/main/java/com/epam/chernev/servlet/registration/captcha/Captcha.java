package com.epam.chernev.servlet.registration.captcha;

import java.io.Serializable;

public class Captcha implements Serializable {

    private static final long serialVersionUID = -3303340313295954119L;

    private final String captchaValue;

    private final Long lifetime;

    private final Long creationTime;

    public Captcha(String captchaValue, Long lifetime) {
        this.captchaValue = captchaValue;
        this.lifetime = lifetime;
        this.creationTime = System.currentTimeMillis();
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public Long getCreationTime() {
        return creationTime;
    }
}
