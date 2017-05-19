package ru.kpfu.itis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.service.AuthService;


/**
 * Created by katemrrr on 12.05.17.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
public class SignUpController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private AuthService authService;

    @FXML private TextField loginTxt;
    @FXML private PasswordField passwordTxt;

    private Stage signUpStage;

    private User user = new User();

    @FXML public void initialize() {}

    @FXML
    public void cancelSignUp(){
        signUpStage.close();
    }

    @FXML
    public void signUp(){
        if (isValidInput()){
            authService.register(new User(loginTxt.getText(), passwordTxt.getText(), false));
            signUpStage.close();
        }
    }


    public void setSignUpStage(Stage signUpStage) {
        this.signUpStage = signUpStage;
    }

    public boolean isValidInput(){
        String error = "";

        if ((loginTxt.getText() == null || loginTxt.getText().equals(""))||(passwordTxt.getText() == null || passwordTxt.getText().equals(""))){
            error += "Enter a login/password!";
        }

        if (error.equals("")){
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "Error")
                .showAndWait();
            return false;
        }
    }
}
