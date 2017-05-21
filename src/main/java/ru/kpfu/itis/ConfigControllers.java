package ru.kpfu.itis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.ui.*;

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

    @Bean(name = "signUpView")
    public View getSignUpView() throws IOException {
        return loadView("fxml/signUp.fxml");
    }

    @Bean(name = "adminView")
    public View getAdminView() throws IOException {
        return loadView("fxml/admin.fxml");
    }

    @Bean(name = "userView")
    public View getUserView() throws IOException {
        return loadView("fxml/user.fxml");
    }

    @Bean(name = "formCarView")
    public View getFormCarView() throws IOException {
        return loadView("fxml/formCar.fxml");
    }

    @Bean(name = "formOrderView")
    public View getFormOrderView() throws IOException {
        return loadView("fxml/formOrder.fxml");
    }

    @Bean
    public AuthController getAuthController() throws IOException {
        return (AuthController) getAuthView().getController();
    }

    @Bean
    public SignUpController getSignUpController() throws IOException {
        return (SignUpController) getSignUpView().getController();
    }

    @Bean
    public AdminController getAdminController() throws IOException {
        return (AdminController) getAdminView().getController();
    }

    @Bean
    public UserController getUserController() throws IOException {
        return (UserController) getUserView().getController();
    }

    @Bean
    public FormOrderController getFormOrderController() throws IOException {
        return (FormOrderController) getFormOrderView().getController();
    }

    @Bean
    public FormCarController getFormCarController() throws IOException {
        return (FormCarController) getFormCarView().getController();
    }

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
