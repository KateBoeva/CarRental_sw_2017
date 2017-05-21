package ru.kpfu.itis.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.ConfigControllers;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.service.AuthService;

import java.io.IOException;

import static ru.kpfu.itis.utils.Utils.isEmpty;
import static ru.kpfu.itis.utils.Utils.showFrame;

/**
 * Created by katemrrr on 12.05.17.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    @Qualifier("signUpView")
    @Autowired
    private ConfigControllers.View signUpView;

    @Qualifier("adminView")
    @Autowired
    private ConfigControllers.View adminView;

    @Qualifier("userView")
    @Autowired
    private ConfigControllers.View userView;

    private Stage signUpStage;
    private Stage adminStage;
    private Stage userStage;


    @FXML
    public void initialize() {}

    public void signIn() throws IOException {
        if(isValid()){

            Token token = authService.auth(new User(loginField.getText(), passwordField.getText()));
            if(token.getStatus() == 1){
                showFrame("Администратор", adminView);
            } else if(token.getStatus() == 0){
                showFrame("Пользователь", userView);
            } else {
                new Alert(Alert.AlertType.ERROR, "not valid user data")
                        .show();
            }
        }
    }

    public void signUp() throws IOException {
        showFrame("Регистрация", signUpView);
    }

    private boolean isValid(){

        boolean isError = false;

        if (isEmpty(loginField.getText()) || isEmpty(passwordField.getText())) {
            isError = true;
        }

        if (isError){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Correct fields!");
            alert.setTitle("Error!");
            alert.setContentText("Введите корректные данные!");
            alert.showAndWait();

            return false;
        }
        return true;
    }
}
