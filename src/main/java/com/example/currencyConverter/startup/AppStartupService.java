package com.example.currencyConverter.startup;

import com.example.currencyConverter.db.CurrencyDbOperations;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AppStartupService {

    private static final long refreshPeriodTime = 10;

    private final Lock schedulerLock = new ReentrantLock();
    private ScheduledExecutorService scheduler;
    private CurrencyDbOperations currencyDbOperations;

    @Autowired
    public AppStartupService(CurrencyDbOperations currencyDbOperations) {
        this.currencyDbOperations = currencyDbOperations;
    }

    public void synchDb() throws Exception {
        if (schedulerLock.tryLock()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    currencyDbOperations.updateDb();
                } catch (Exception e) {
                    e.printStackTrace(); // Handle or log exception
                }
            }, refreshPeriodTime, refreshPeriodTime, TimeUnit.SECONDS);
        } else {
            System.out.println("Scheduler is already running, skipping this cycle...");
        }
    }

    public void closeScheduler() {
        if (scheduler != null) {
            scheduler.shutdownNow();
            schedulerLock.unlock();
        }
    }

    public void start() throws Exception {
        System.out.println("Starting up shared startup logic...");
        currencyDbOperations.initDb();
        synchDb();
    }

    @PreDestroy
    public void stop() {
        System.out.println("Shutting down startup logic...");
        closeScheduler();
    }
}
