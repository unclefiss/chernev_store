package com.epam.chernev.servlet.registration.captcha;

import java.util.Map;
import java.util.concurrent.Exchanger;

public class CaptchaTimeout implements Runnable {

    private final Map<String, Captcha> captchaMap;

    private final Exchanger<Boolean> threadExchangers;

    public CaptchaTimeout(Map<String, Captcha> captchaMap, Exchanger<Boolean> threadExchangers) {
        this.captchaMap = captchaMap;
        this.threadExchangers = threadExchangers;
    }

    @Override
    public void run() {
        try {
            boolean check = threadExchangers.exchange(true);
            Thread.sleep(500);
            while (check) {
                if (!captchaMap.isEmpty()) {
                    Long scale = 1000L;
                    for (Map.Entry<String, Captcha> entry : captchaMap.entrySet()) {
                        if ((System.currentTimeMillis() - entry.getValue().getCreationTime()) > entry.getValue().getLifetime() * scale) {
                            captchaMap.remove(entry.getKey());
                        }
                    }
                } else {
                    check = false;
                }
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run();
    }

}
