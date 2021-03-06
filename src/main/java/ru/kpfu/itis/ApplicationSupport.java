package ru.kpfu.itis;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class ApplicationSupport extends Application {

    private static String[] savedArgs;

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends ApplicationSupport> appClass, String[] args) {
        ApplicationSupport.savedArgs = args;
        Application.launch(appClass, args);
    }
}
