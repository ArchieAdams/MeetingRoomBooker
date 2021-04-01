package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;

public class SceneManager extends Application {


    @Override
    public void start(Stage primaryStage){
        fxmlLoader("MainMenu", primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void fxmlLoader(String sceneName, Stage stage){
        //stage.close();
        //stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(sceneName+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle(sceneName);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.isResizable();
        stage.show();
    }

}
