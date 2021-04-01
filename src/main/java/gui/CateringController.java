package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Helper;
import libraryFunctions.Repository;
import objects.Booking;
import objects.CateringOrder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

import static libraryFunctions.Repository.getBookingTimes;
import static libraryFunctions.Repository.getNewestBookingID;


public class CateringController {


    @FXML
    Button saveButton;

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

    private final ArrayList<CateringOrder> cateringOrderArrayList = new ArrayList<>();
    private final ArrayList<String> itemsArrayList = new ArrayList<>(Arrays.asList("Tea","Coffee","Water","Oreo","Quavers","Ham Sandwich","Chicken Wrap"));
    private final ArrayList<Time> timesDisplayedList = new ArrayList<>();

    public void start(MouseEvent mouseEvent) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            integerArrayList.add(i);
        }

        if (timeColumn.getCellData(0) == null){
            itemQuantityDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
            itemDropdown.setItems(FXCollections.observableArrayList(itemsArrayList));
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

    public void saveEvent(MouseEvent actionEvent){
        for (int i = 0; i < cateringTable.getItems().size(); i++) {
            System.out.println("HI");
            Repository.insertCateringOrder(cateringTable.getItems().get(i));
        }
        new SceneManager().fxmlLoader("Menu", (Stage) saveButton.getScene().getWindow());
    }

    public void addTimeEvent(MouseEvent mouseEvent) {
        errorLabel.setText("");
        int bookingID = getNewestBookingID();
        try {
            Time time = Time.valueOf(timeField.getText() + ":00");
            if (!timesDisplayedList.contains(time)) {
                ArrayList<Time> bookingTime =  getBookingTimes(bookingID);
                if(new Helper().doTimesOverLap(time, time, bookingTime)) {
                    ArrayList<Time> timeArrayList = new ArrayList<>();
                    CateringOrder cateringOrder = new CateringOrder(bookingID, Repository.getRoomNumbers(bookingID) ,time , 10, 0, 0, 0, 0, 0, 0);
                    cateringOrderArrayList.add(cateringOrder);

                    cateringTable.getItems().clear();
                    for (CateringOrder order : cateringOrderArrayList) {
                        cateringTable.getItems().add(order);
                        timeArrayList.add(order.getTime());
                        timesDisplayedList.add(time);
                    }
                    timeDropdown.setItems(FXCollections.observableArrayList(timeArrayList));
                }
                else {
                    errorLabel.setText("Please enter a time between "+bookingTime.get(0)+" and "+bookingTime.get(1));
                }
            }
            else{
                errorLabel.setText("Please enter a new time.");
            }
        }
        catch (Exception e){
            errorLabel.setText("Please enter time in the form hh:mm e.g. 09:12");
        }
    }

    public void addItemEvent(MouseEvent mouseEvent) {

        if (timeDropdown.getValue() != null && itemDropdown.getValue() != null && itemQuantityDropdown.getValue() != null){
            for (int i = 0; i < cateringOrderArrayList.size(); i++) {

                CateringOrder cateringOrder = cateringOrderArrayList.get(i);
                if (cateringOrder.getTime().equals(timeDropdown.getValue())){

                    cateringOrderArrayList.set(i,new Helper().changeCateringOrder(itemsArrayList.indexOf(itemDropdown.getValue()),itemQuantityDropdown.getValue(), cateringOrder));
                    cateringTable.getItems().clear();
                    for (CateringOrder order : cateringOrderArrayList) {
                        cateringTable.getItems().add(order);
                    }
                    break;
                }
            }
        }
        else {
            errorLabel.setText("Please select something in each dropdown.");
        }
    }
}


