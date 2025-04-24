package com.example.currencyConverter.startup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringStartupRunner implements ApplicationRunner {
    private final AppStartupService startupService;

    public SpringStartupRunner(AppStartupService startupService) {
        this.startupService = startupService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startupService.start();
    }
}
