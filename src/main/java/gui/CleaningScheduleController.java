package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Repository;
import objects.Booking;
import objects.CateringOrder;
import objects.CleaningSchedule;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


public class CleaningScheduleController {

    @FXML
    Button backButton;

    @FXML
    Label errorLabel;

    @FXML
    TableView<CleaningSchedule> cleaningScheduleTable;

    @FXML
    TableColumn<Booking,Integer> bookingIDColumn, roomNumberColumn;

    @FXML
    TableColumn<Booking, Time> startTimeColumn, endTimeColumn;

    @FXML
    TableColumn<Booking, Date> dateColumn;


    public void start(MouseEvent mouseEvent) {
        if (bookingIDColumn.getCellData(0) == null){
            bookingIDColumn.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
            roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
            startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
            endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
            for (CleaningSchedule cleaningSchedule : Repository.getCleaningSchedule()) {
                cleaningScheduleTable.getItems().add(cleaningSchedule);
            }
        }
    }

    public void backEvent(MouseEvent mouseEvent) {
        new SceneManager().fxmlLoader("StaffMenu", (Stage) backButton.getScene().getWindow());

    }
}


