package ru.kpfu.itis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import static ru.kpfu.itis.utils.Utils.createFrame;

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

    @Autowired
    SignUpController signUpController;

    @Autowired
    UserController userController;

    @Autowired
    AdminController adminController;

    @FXML
    public void initialize() {}

    public void signIn() throws IOException {
        if(isValid()){

            Token token = authService.auth(new User(loginField.getText(), passwordField.getText()));
            if(token.getStatus() == 1){
                Stage adminStage = new Stage();
                createFrame("Администратор", adminView, adminStage);
                adminController.setAdminStage(adminStage);
                adminController.refreshTable();
                adminStage.show();
                loginField.setText("");
                passwordField.setText("");
            } else if(token.getStatus() == 0){
                Stage userStage = new Stage();
                createFrame("Пользователь", userView, userStage);
                userController.setUserStage(userStage);
                userStage.show();
                loginField.setText("");
                passwordField.setText("");
            } else {
                new Alert(Alert.AlertType.ERROR, "not valid user data")
                        .show();
            }
        }
    }

    public void signUp() throws IOException {
        Stage signUpStage = new Stage();
        createFrame("Регистрация", signUpView, signUpStage);
        signUpController.setSignUpStage(signUpStage);
        signUpStage.show();
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
