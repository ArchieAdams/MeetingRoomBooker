package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Helper;
import libraryFunctions.Repository;

public class LoginController{

    @FXML
    TextField usernameField, passwordField;

    @FXML
    Label errorLabel;

    @FXML
    Button loginButton, backButton;

    public void loginEvent(MouseEvent mouseEvent) {
        String username = usernameField.getText();
        String password = Helper.hashPassword(passwordField.getText());

        errorLabel.setText("");
        if (!username.equals("") && !password.equals("") && Repository.userLogIn(username, password)){
            Stage stage = (Stage) loginButton.getScene().getWindow();
            new SceneManager().fxmlLoader("Menu", stage);
        }
        else {
            errorLabel.setText("Your password or username are incorrect.");
        }
    }

    public void backEvent(MouseEvent mouseEvent) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        new SceneManager().fxmlLoader("MainMenu", stage);
    }
}
