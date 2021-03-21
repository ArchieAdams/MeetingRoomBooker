package gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Repository;
import objects.Room;

import java.util.ArrayList;


public class BookRoom {

    @FXML
    Button backButton, bookRoomButton;

    @FXML
    ComboBox<String> roomsDropdown, typeOfPaperDropdown;
    @FXML
    ComboBox<Integer> numberOfPeopleDropdown, numberOfPensDropdown, numberOfPencilsDropdown, sheetsOfPaperDropdown;



    public void backEvent(MouseEvent mouseEvent) {
        new SceneManager().fxmlLoader("MainMenu", (Stage) backButton.getScene().getWindow());
    }

    public void bookRoomEvent(MouseEvent mouseEvent) {

    }

    public void start(MouseEvent mouseEvent) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            integerArrayList.add(i+1);
        }
        roomsDropdown.setItems(FXCollections.observableArrayList(Repository.getAllRoomNumbersAndCapacity()));
        typeOfPaperDropdown.setItems(FXCollections.observableArrayList("Lined Paper","Graph Paper","Plain A4 Paper","Plain A3 Paper"));
        numberOfPeopleDropdown.setTooltip(new Tooltip("Please pick a room first."));
        numberOfPensDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
        numberOfPencilsDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
        sheetsOfPaperDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
    }

    public void roomChosenEvent(ActionEvent actionEvent) {
        try{
            if (!roomsDropdown.getValue().equals(null)){
                Room room = Repository.getAllRooms().get(roomsDropdown.getItems().indexOf(roomsDropdown.getValue()));
                ArrayList<Integer> integerArrayList = new ArrayList<>();
                for (int i = 0; i < room.getCapacity(); i++) {
                    integerArrayList.add(i+1);
                }
                numberOfPeopleDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
            }
        }catch (Exception e){

        }
    }
}


