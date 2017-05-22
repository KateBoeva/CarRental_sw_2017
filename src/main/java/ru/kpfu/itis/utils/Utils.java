package ru.kpfu.itis.utils;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.kpfu.itis.ConfigControllers;

/**
 * Created by katemrrr on 20.05.17.
 */
public class Utils {

    public static boolean isEmpty(String s){
        if(s == null || s.equals(""))
            return true;
        return false;
    }


    public static void createFrame(String title, ConfigControllers.View view, Stage stage) {
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);

        if(view.getScene() == null) {
            view.setScene(new Scene(view.getView()));
        }

        stage.setScene(view.getScene());

        stage.setResizable(false);
        stage.centerOnScreen();
    }

}
