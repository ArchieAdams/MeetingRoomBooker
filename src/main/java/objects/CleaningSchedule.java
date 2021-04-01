package objects;

import java.sql.Date;
import java.sql.Time;

public class CleaningSchedule {

    private int bookingID;
    private int roomNumber;
    private Date date;
    private Time startTime;
    private Time endTime;

    public CleaningSchedule(int bookingID, int roomNumber, Date date, Time startTime, Time endTime) {
        this.bookingID = bookingID;
        this.roomNumber = roomNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "CleaningSchedule{" +
                "bookingID=" + bookingID +
                ", roomNumber=" + roomNumber +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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
}
