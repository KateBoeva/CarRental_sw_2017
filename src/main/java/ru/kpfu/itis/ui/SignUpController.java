package ru.kpfu.itis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.service.AuthService;

import static ru.kpfu.itis.utils.Utils.isEmpty;


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
    @FXML private Button closeButton;

    private Stage signUpStage;

    private User user = new User();

    @FXML public void initialize() {}

    @FXML
    public void cancelSignUp(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void signUp(){
        if (isValidInput()){
            authService.register(new User(loginTxt.getText(), passwordTxt.getText(), false));

        }
    }

    public boolean isValidInput(){
        boolean isError = false;

        if (isEmpty(loginTxt.getText()) || isEmpty(passwordTxt.getText())) {
            isError = true;
        }

        if (isError){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Incorrect fields!");
            alert.setTitle("Error!");
            alert.setContentText("Введите корректные данные!");
            alert.showAndWait();

            return false;
        }
        return true;
    }

}
