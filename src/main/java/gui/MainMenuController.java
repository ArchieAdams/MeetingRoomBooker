package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainMenuController{

    @FXML
    Button loginButton, createAccountButton;


    public void loginEvent(ActionEvent actionEvent){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        new SceneManager().fxmlLoader("Login", stage);
    }

    public void createAccountEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        new SceneManager().fxmlLoader("CreateAccount", stage);
    }
}


