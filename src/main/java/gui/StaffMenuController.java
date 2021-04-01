package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import libraryFunctions.Helper;
import libraryFunctions.Repository;
import objects.Staff;
import objects.User;


public class StaffMenuController {

    @FXML
    Button cateringOrdersButton, cleaningScheduleButton, backButton;

    public void viewCateringOrders(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("CateringOrders", (Stage) cateringOrdersButton.getScene().getWindow());
    }

    public void viewCleaningSchedule(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("CleaningSchedule", (Stage) cleaningScheduleButton.getScene().getWindow());
    }

    public void backEvent(ActionEvent actionEvent) {
        new SceneManager().fxmlLoader("Menu", (Stage) backButton.getScene().getWindow());
    }
}


