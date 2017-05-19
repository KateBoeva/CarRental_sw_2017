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
import ru.kpfu.itis.service.UserService;


/**
 * Created by katemrrr on 12.05.17.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
public class SignUpController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    UserService userService;

    @FXML private TextField loginTxt;
    @FXML private PasswordField passwordTxt;

    private Stage signUpStage;

    private User user = new User();

//    private AuthController ac = new AuthController(); // show later

    @FXML public void initialize() {}

    @FXML
    public void cancelSignUp(){
        signUpStage.close();
    }

    @FXML
    public void signUp(){
        if (isValidInput()){
//            user.setLogin(loginTxt.getText());
//            user.setPassword(passwordTxt.getText());
//            userService.save(new User(loginTxt.getText(), passwordTxt.getText()));
            signUpStage.close();

//            Contact contact = new Contact(txtName.getText(), txtPhone.getText(), txtEmail.getText());
//            contactService.save(contact);
//            data.add(contact);
        }
    }

//    @PostConstruct
//    public void generateTestData() {
//
//    }


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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error");
            alert.showAndWait();
            return false;
        }
    }
}
