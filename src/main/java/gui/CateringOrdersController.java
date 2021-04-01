package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Repository;
import objects.Booking;
import objects.CateringOrder;

import java.sql.Time;
import java.util.ArrayList;


public class CateringOrdersController {

    @FXML
    Button backButton;

    @FXML
    Label errorLabel;

    @FXML
    TableView<CateringOrder> cateringOrdersTable;

    @FXML
    TableColumn<Booking,Integer> bookingIDColumn, roomNumberColumn, teaColumn, coffeeColumn, waterColumn, oreoColumn, quaversColumn, hamSandwichesColumn, chickenWrapsColumn;

    @FXML
    TableColumn<Booking, Time> timeColumn;


    public void start(MouseEvent mouseEvent) {
        if (bookingIDColumn.getCellData(0) == null){
            bookingIDColumn.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
            roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
            teaColumn.setCellValueFactory(new PropertyValueFactory<>("TeaNum"));
            coffeeColumn.setCellValueFactory(new PropertyValueFactory<>("CoffeeNum"));
            waterColumn.setCellValueFactory(new PropertyValueFactory<>("WaterNum"));
            oreoColumn.setCellValueFactory(new PropertyValueFactory<>("OreoNum"));
            quaversColumn.setCellValueFactory(new PropertyValueFactory<>("QuaversNum"));
            hamSandwichesColumn.setCellValueFactory(new PropertyValueFactory<>("HamSandwichNum"));
            chickenWrapsColumn.setCellValueFactory(new PropertyValueFactory<>("ChickenWrapNum"));
            final ArrayList<CateringOrder> cateringOrderArrayList = Repository.getCateringOrders();
            for (CateringOrder cateringOrder : cateringOrderArrayList) {
                cateringOrdersTable.getItems().add(cateringOrder);
            }
        }
    }

    public void backEvent(MouseEvent mouseEvent) {
        new SceneManager().fxmlLoader("StaffMenu", (Stage) backButton.getScene().getWindow());

    }

}


