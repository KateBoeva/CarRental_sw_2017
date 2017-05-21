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

//    public static void showFrame(Stage stage, String title, ConfigControllers.View view) {
//        stage = new Stage();
//        stage.setTitle(title);
//        stage.initModality(Modality.WINDOW_MODAL);
//
//        Scene scene = new Scene(view.getView());
//        stage.setScene(scene);
//
//        stage.setResizable(true);
//        stage.centerOnScreen();
//        stage.show();
//    }

    public static void showFrame(String title, ConfigControllers.View view) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(view.getView());
        stage.setScene(scene);

        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

}
