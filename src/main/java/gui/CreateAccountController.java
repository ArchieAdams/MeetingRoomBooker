package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Helper;
import libraryFunctions.Repository;
import objects.User;

import java.util.regex.Pattern;

public class CreateAccountController {

    @FXML
    Label errorLabel;

    @FXML
    TextField usernameField,firstNameField, lastNameField, emailField, passwordField, confirmedPasswordField;

    @FXML
    Button createAccountButton, backButton;

    public void createAccountEvent(MouseEvent mouseEvent) {
        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = Helper.hashPassword(passwordField.getText());
        String confirmedPassword = Helper.hashPassword(confirmedPasswordField.getText());

        errorLabel.setText("");
        if (!username.equals("") && !firstName.equals("") && !lastName.equals("") && !email.equals("") && !password.equals("") && !confirmedPassword.equals("")){
            Pattern p = Pattern.compile("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b");
            if (p.matcher(email).find()){
                if (!Repository.getAllUsernames().contains(username)){
                    if (!Repository.getAllEmails().contains(email)){
                        if (Helper.compareHashed(password,confirmedPassword)){
                            Repository.insertNewUser(new User(username, firstName, lastName, email, password));
                            new SceneManager().fxmlLoader("Login", (Stage) errorLabel.getScene().getWindow());
                        }
                        else {
                            errorLabel.setText("Your passwords don't matched");
                        }
                    }
                    else{
                        errorLabel.setText("That email is already taken.");
                    }
                }
                else{
                    errorLabel.setText("That username is already taken.");
                }
            }
            else{
                errorLabel.setText("Email is not valid.");
            }
        }
        else {
            errorLabel.setText("Please enter something in each field.");
        }
    }

    public void backEvent(MouseEvent mouseEvent) {
        new SceneManager().fxmlLoader("MainMenu", (Stage) backButton.getScene().getWindow());
    }
}
