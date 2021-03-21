package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuController {

    @FXML
    Button viewBookingsButton, bookRoomButton;

    public void viewBookings(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("ViewBookings", (Stage) viewBookingsButton.getScene().getWindow());
    }

    public void bookRoom(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("BookRoom", (Stage) bookRoomButton.getScene().getWindow());
    }
}


