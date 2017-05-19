package ru.kpfu.itis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.ui.AdminController;
import ru.kpfu.itis.ui.AuthController;
import ru.kpfu.itis.ui.SignUpController;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by katemrrr on 16.05.17.
 */

@Configuration
public class ConfigControllers {

    @Bean(name = "authView")
    public View getAuthView() throws IOException {
        return loadView("fxml/auth.fxml");
    }

    @Bean
    public AuthController getAuthController() throws IOException {
        return (AuthController) getAuthView().getController();
    }

    @Bean(name = "signUpView")
    public View getSignUpView() throws IOException {
        return loadView("fxml/signUp.fxml");
    }

    @Bean
    public SignUpController getSignUpController() throws IOException {
        return (SignUpController) getSignUpView().getController();
    }

    @Bean(name = "adminView")
    public View getAdminView() throws IOException {
        return loadView("fxml/admin.fxml");
    }

    @Bean
    public AdminController getAdminController() throws IOException {
        return (AdminController) getAdminView().getController();
    }

//    @Bean(name = "userView")
//    public View getUserView() throws IOException {
//        return loadView("fxml/user.fxml");
//    }
//
//    @Bean(name = "editCarView")
//    public View getEditCarView() throws IOException {
//        return loadView("fxml/editCar.fxml");
//    }
//
//    @Bean(name = "editOrderView")
//    public View getEditOrderView() throws IOException {
//        return loadView("fxml/editOrder.fxml");
//    }
//
//    @Bean(name = "formOrderView")
//    public View getFormOrderView() throws IOException {
//        return loadView("fxml/formOrder.fxml");
//    }

    private View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }

    }

    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller){
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}
