package com.example.currencyConverter.startup;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebListener
public class TomcatStartupListener implements ServletContextListener {

    private AppStartupService startupService;

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        WebApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);

        startupService = ctx.getBean(AppStartupService.class);
        startupService.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (startupService != null) {
            startupService.stop();
        }
    }
}