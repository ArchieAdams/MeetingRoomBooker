package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainMenuController{

    @FXML
    Button loginButton, createAccountButton;


    public void loginEvent(ActionEvent actionEvent){
        new SceneManager().fxmlLoader("Login", (Stage) loginButton.getScene().getWindow());
    }

    public void createAccountEvent(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("CreateAccount", (Stage) createAccountButton.getScene().getWindow());
    }
}


