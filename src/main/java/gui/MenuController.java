package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import libraryFunctions.Repository;


public class MenuController {

    @FXML
    Label errorLabel;

    @FXML
    Button viewBookingsButton, bookRoomButton, staffMenuButton,logOutButton;

    public void viewBookings(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("ViewBookings", (Stage) viewBookingsButton.getScene().getWindow());
    }

    public void bookRoom(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("BookRoom", (Stage) bookRoomButton.getScene().getWindow());
    }

    public void staffMenu(ActionEvent actionEvent) {
        if (Repository.isStaff(Repository.getCurrentUser())) {
            new SceneManager().fxmlLoader("StaffMenu", (Stage) staffMenuButton.getScene().getWindow());
        }
        else {
            errorLabel.setText("You are not staff.");
        }
    }

    public void logOut(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("MainMenu", (Stage) logOutButton.getScene().getWindow());
    }
}


