package ru.kpfu.itis.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ConfigControllers;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.service.AuthService;

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

    @FXML
    public void initialize() {}

    public void signIn() throws IOException {
        if(isValid()){

            // TODO signIn algorithm

            showAdminFrame();
        }
    }

    public void signUp() throws IOException {
        showSignUpFrame();
    }

    private boolean isValid(){

        String error = "";

        if ((loginField.getText() == null || loginField.getText().equals(""))||(passwordField.getText() == null || passwordField.getText().equals(""))){
            error += "Enter a login/password!";
        }

        if (error.equals("")){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, error);
            alert.showAndWait();
            return false;
        }
    }

    // вынести в один метод
    private void showSignUpFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthController.class.getResource("/fxml/signUp.fxml"));
        AnchorPane anchPane = (AnchorPane) loader.load();

        Stage signUpStage = new Stage();
        signUpStage.setTitle("Регистрация");
        signUpStage.initModality(Modality.WINDOW_MODAL);

//        signUpStage.setScene(new Scene(signUpView.getView()));
//        signUpStage.setResizable(true);
//        signUpStage.centerOnScreen();

        Scene scene = new Scene(anchPane);
        signUpStage.setScene(scene);

        SignUpController controller = loader.getController();
        controller.setSignUpStage(signUpStage);

        signUpStage.showAndWait();

    }

    private void showAdminFrame() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthController.class.getResource("/fxml/admin.fxml"));
//        TabPane tabPane = (TabPane) loader.load();
        AnchorPane anchPane = (AnchorPane) loader.load();

        Stage adminStage = new Stage();
        adminStage.setTitle("Администратор");
        adminStage.initModality(Modality.WINDOW_MODAL);

//        adminStage.setScene(new Scene(view.getView()));
//        adminStage.setResizable(true);
//        adminStage.centerOnScreen();

        Scene scene = new Scene(anchPane);
        adminStage.setScene(scene);

        AdminController controller = loader.getController();
        controller.setAdminStage(adminStage);

        adminStage.showAndWait();
    }

}
