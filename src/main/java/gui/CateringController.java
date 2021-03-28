package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Repository;
import objects.Booking;
import objects.CateringOrder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


public class CateringController {


    @FXML
    Label errorLabel;

    @FXML
    ComboBox<Integer> itemQuantityDropdown;

    @FXML
    ComboBox<String> itemDropdown;

    @FXML
    ComboBox<Time> timeDropdown;

    @FXML
    TextField timeField;

    @FXML
    TableView<CateringOrder> cateringTable;

    @FXML
    TableColumn<Booking,Integer> teaColumn, coffeeColumn, waterColumn, oreoColumn, quaversColumn, hamSandwichesColumn, chickenWrapsColumn;

    @FXML
    TableColumn<Booking, Time> timeColumn;

    public void start(MouseEvent mouseEvent) {
        if (timeColumn.getCellData(0) == null){
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
            teaColumn.setCellValueFactory(new PropertyValueFactory<>("TeaNum"));
            coffeeColumn.setCellValueFactory(new PropertyValueFactory<>("CoffeeNum"));
            waterColumn.setCellValueFactory(new PropertyValueFactory<>("WaterNum"));
            oreoColumn.setCellValueFactory(new PropertyValueFactory<>("OreoNum"));
            quaversColumn.setCellValueFactory(new PropertyValueFactory<>("QuaversNum"));
            hamSandwichesColumn.setCellValueFactory(new PropertyValueFactory<>("HamSandwichNum"));
            chickenWrapsColumn.setCellValueFactory(new PropertyValueFactory<>("ChickenWrapNum"));
        }
    }

    public void saveEvent(ActionEvent actionEvent){
        new SceneManager().fxmlLoader("Menu", (Stage) errorLabel.getScene().getWindow());
    }

    public void addTimeEvent(MouseEvent mouseEvent) {
        CateringOrder cateringOrder = new CateringOrder(1,Time.valueOf("09:00:00"), 0,0,0,0,0,0,0);
        cateringTable.getItems().add(cateringOrder);
    }

    public void addItemEvent(MouseEvent mouseEvent) {

    }
}


