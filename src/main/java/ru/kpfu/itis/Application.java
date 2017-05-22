package ru.kpfu.itis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends ApplicationSupport {

    //TODO
    @Value("Hello!")
    private String windowTitle;

    @Qualifier("authView")
    @Autowired
    private ConfigControllers.View view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        //TODO close stage, when user was sign in
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }
}
