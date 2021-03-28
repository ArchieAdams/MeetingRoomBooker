package objects;

import java.sql.Date;
import java.sql.Time;

public class Booking {

    private String bookingName;
    private int roomNumber;
    private String username;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int numberOfPeople;

    public Booking(String bookingName, int roomNumber, String username, Date date, Time startTime, Time endTime, int numberOfPeople) {
        this.bookingName = bookingName;
        this.roomNumber = roomNumber;
        this.username = username;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingName='" + bookingName + '\'' +
                ", roomNumber=" + roomNumber +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
