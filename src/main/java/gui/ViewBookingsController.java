package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Repository;
import objects.Booking;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


public class ViewBookingsController {


    @FXML
    Button backButton;

    @FXML
    TableView<Booking> bookingTable;

    @FXML
    TableColumn<Booking,String> bookingNameColumn;

    @FXML
    TableColumn<Booking,Integer> roomColumn, numberOfPeopleColumn;

    @FXML
    TableColumn<Booking,Date> dateColumn;

    @FXML
    TableColumn<Booking,Time> startTimeColumn, endTimeColumn;

    public void backEvent(MouseEvent actionEvent){
        bookingTable.getItems().clear();
        new SceneManager().fxmlLoader("Menu", (Stage) backButton.getScene().getWindow());
    }

    public void start(MouseEvent mouseEvent) {
        if (bookingNameColumn.getCellData(0) == null){
            bookingNameColumn.setCellValueFactory(new PropertyValueFactory<>("BookingName"));
            roomColumn.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
            startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
            endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
            numberOfPeopleColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfPeople"));
            ArrayList<Booking> bookingArrayList = Repository.getAllBookings(Repository.getCurrentUser());
            for (Booking booking : bookingArrayList) {
                bookingTable.getItems().add(booking);
            }
        }
    }
}


