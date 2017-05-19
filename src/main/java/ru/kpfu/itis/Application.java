package ru.kpfu.itis;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends ApplicationSupport {

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Qualifier("authView")
    @Autowired
    private ConfigControllers.View view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
        //TODO close stage, when user was sign in
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
