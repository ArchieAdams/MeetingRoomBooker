package gui;

import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import libraryFunctions.Helper;
import libraryFunctions.Repository;
import objects.Booking;
import objects.BookingExtras;
import objects.Room;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class BookRoomController {

    @FXML
    Button backButton, bookRoomButton;

    @FXML
    ComboBox<String> roomsDropdown, typeOfPaperDropdown;

    @FXML
    ComboBox<Integer> numberOfPeopleDropdown, numberOfPensDropdown, numberOfPencilsDropdown, sheetsOfPaperDropdown;

    @FXML
    DatePicker datePicker;

    @FXML
    TextField bookingNameField, startTimeField, endTimeField;

    @FXML
    Label errorLabel;

    @FXML
    CheckBox projectorCheckBox;


    public void backEvent(MouseEvent mouseEvent) {
        new SceneManager().fxmlLoader("Menu", (Stage) backButton.getScene().getWindow());
    }

    public void bookRoomEvent(MouseEvent mouseEvent) {
        errorLabel.setText("");
        Helper helper = new Helper();
        if (!bookingNameField.getText().equals("") && roomsDropdown.getValue() != null && datePicker.getValue() != null && !startTimeField.getText().equals("") && !endTimeField.getText().equals("") && numberOfPeopleDropdown.getValue() != null ){
            //TODO add note to tell open time and that bookings must be a day in advance
            Pattern p = Pattern.compile("\\b[0-9]+:[0-9]{2}\\b");
            if (p.matcher(startTimeField.getText()).find() && p.matcher(endTimeField.getText()).find() ) {
                if (datePicker.getValue().isAfter(LocalDate.now())) {
                    Time startTime = Time.valueOf(startTimeField.getText()+":00");
                    Time endTime = Time.valueOf(endTimeField.getText()+":00");
                    if (helper.isTimeAfter(startTime, Time.valueOf("08:59:59")) && helper.isTimeBefore(endTime, Time.valueOf("19:00:01")) &&
                            helper.isTimeBefore(startTime, endTime)) {

                        Booking booking = new Booking(bookingNameField.getText(),Integer.parseInt(Repository.getAllRoomNumbers().get(roomsDropdown.getItems().indexOf(roomsDropdown.getValue())))
                                , Repository.getCurrentUser().getUsername(), Date.valueOf(datePicker.getValue()), startTime, endTime, numberOfPeopleDropdown.getValue());

                        if (!helper.doTimesOverLap(startTime, endTime, Repository.getBookingTimes(booking), errorLabel)) {
                            Repository.insertNewBooking(booking);
                            int id = Repository.getBookingId(booking);
                            boolean projectCheckBoxSelected = projectorCheckBox.isSelected();
                            BookingExtras bookingExtras = new BookingExtras(id, projectCheckBoxSelected, numberOfPensDropdown.getValue(), numberOfPencilsDropdown.getValue(), sheetsOfPaperDropdown.getValue(), typeOfPaperDropdown.getValue());
                            Repository.insertBookingExtras(bookingExtras);
                            System.out.println("Booking added");
                            new SceneManager().fxmlLoader("Catering", (Stage) backButton.getScene().getWindow());
                        }
                    } else {
                        errorLabel.setText("Please make sure your time is with in our work hours (9 to 7) and start time is before end time.");
                    }
                }else {
                    errorLabel.setText("Please pick a date after the current date.");
                }
            }else {
                errorLabel.setText("Your time must be in the format hh:mm e.g 12:15");
            }
        }
        else {
            errorLabel.setText("Please enter something in each field.");
        }
    }

    public void start(MouseEvent mouseEvent) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            integerArrayList.add(i);
        }
        roomsDropdown.setItems(FXCollections.observableArrayList(Repository.getAllRoomNumbersAndCapacity()));
        typeOfPaperDropdown.setItems(FXCollections.observableArrayList("Lined Paper","Graph Paper","Plain A4 Paper","Plain A3 Paper"));
        numberOfPeopleDropdown.setTooltip(new Tooltip("Please pick a room first."));
        numberOfPensDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
        numberOfPencilsDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
        sheetsOfPaperDropdown.setItems(FXCollections.observableArrayList(integerArrayList));
        if (typeOfPaperDropdown.getValue() == null || numberOfPensDropdown.getValue() == null || numberOfPencilsDropdown.getValue() == null ||sheetsOfPaperDropdown.getValue() == null){
            typeOfPaperDropdown.getSelectionModel().select(0);
            numberOfPensDropdown.getSelectionModel().select(0);
            numberOfPencilsDropdown.getSelectionModel().select(0);
            sheetsOfPaperDropdown.getSelectionModel().select(0);
        }
    }

    public void roomChosenEvent(ActionEvent actionEvent) {
        try{
            if (roomsDropdown.getValue() != null){
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


