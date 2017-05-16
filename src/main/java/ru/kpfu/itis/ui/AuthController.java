package ru.kpfu.itis.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.service.AuthService;

import org.springframework.*;

import java.io.IOException;

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

    private ObservableList<Token> tokens;

    @FXML
    public void initialize() {}

//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/auth", method = RequestMethod.POST)
//    public ResponseEntity<TokenObject> auth(@RequestHeader(value = "ORIGIN") String origin,
//                                            @RequestBody LoginInfoDto loginInfoDto){
//        return new ResponseEntity<TokenObject>(mAuthService.auth(loginInfoDto), HttpStatus.OK);
//    }
//
//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity register(@RequestHeader(value = "ORIGIN") String origin,
//                                   @RequestBody LoginInfoDto loginInfoDto){
//        if(mAuthService.register(loginInfoDto))
//            return new ResponseEntity(HttpStatus.OK);
//        else
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }

    public void signIn(){
//        if(isValid()){
//            if()
//        }
    }

    public void signUp() throws IOException {
        showSignUpFrame();
    }

    private void showRegisterFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthController.class.getResource("/fxml/signUp.fxml"));
        AnchorPane anchorPane = loader.load(); // add (AncPane)

        Stage signUpStage = new Stage();
        signUpStage.setTitle("Register");
        signUpStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(anchorPane);
        signUpStage.setScene(scene);

        SignUpController controller = loader.getController();
        controller.setSignUpStage(signUpStage);
//        controller.setUser(user);
    }

    public boolean isValid(){

        String error = "";

        if (loginField.getText() == null || loginField.getText().equals("")){
            error += "Enter a login!";
        }
        if (passwordField.getText() == null || passwordField.getText().equals("")){
            error += "Enter a password!";
        }

        if (error.equals("")){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, error);
            alert.showAndWait();
            return false;
        }
    }

    private void showSignUpFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthController.class.getResource("/fxml/signUp.fxml"));
        AnchorPane anchPane = loader.load();

        Stage signUpStage = new Stage();
        signUpStage.setTitle("Регистрация");
        signUpStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(anchPane);
        signUpStage.setScene(scene);

//        SignUpController controller = loader.getController();
//        controller.setSignUpStage(signUpStage);

        signUpStage.showAndWait();
    }

}
