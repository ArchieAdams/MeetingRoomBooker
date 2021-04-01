package objects;

import java.sql.Time;

public class CateringOrder {

    private int bookingID;
    private int roomNumber;
    private Time time;
    private int teaNum;
    private int coffeeNum;
    private int waterNum;
    private int oreoNum;
    private int quaversNum;
    private int hamSandwichNum;
    private int chickenWrapNum;

    public CateringOrder(int bookingID, int roomNumber, Time time, int teaNum, int coffeeNum, int waterNum, int oreoNum, int quaversNum, int hamSandwichNum, int chickenWrapNum) {
        this.bookingID = bookingID;
        this.roomNumber = roomNumber;
        this.time = time;
        this.teaNum = teaNum;
        this.coffeeNum = coffeeNum;
        this.waterNum = waterNum;
        this.oreoNum = oreoNum;
        this.quaversNum = quaversNum;
        this.hamSandwichNum = hamSandwichNum;
        this.chickenWrapNum = chickenWrapNum;
    }

    @Override
    public String toString() {
        return "CateringOrder{" +
                "bookingID=" + bookingID +
                ", roomNumber=" + roomNumber +
                ", time=" + time +
                ", teaNum=" + teaNum +
                ", coffeeNum=" + coffeeNum +
                ", waterNum=" + waterNum +
                ", oreoNum=" + oreoNum +
                ", quaversNum=" + quaversNum +
                ", hamSandwichNum=" + hamSandwichNum +
                ", chickenWrapNum=" + chickenWrapNum +
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(int teaNum) {
        this.teaNum = teaNum;
    }

    public int getCoffeeNum() {
        return coffeeNum;
    }

    public void setCoffeeNum(int coffeeNum) {
        this.coffeeNum = coffeeNum;
    }

    public int getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(int waterNum) {
        this.waterNum = waterNum;
    }

    public int getOreoNum() {
        return oreoNum;
    }

    public void setOreoNum(int oreoNum) {
        this.oreoNum = oreoNum;
    }

    public int getQuaversNum() {
        return quaversNum;
    }

    public void setQuaversNum(int quaversNum) {
        this.quaversNum = quaversNum;
    }

    public int getHamSandwichNum() {
        return hamSandwichNum;
    }

    public void setHamSandwichNum(int hamSandwichNum) {
        this.hamSandwichNum = hamSandwichNum;
    }

    public int getChickenWrapNum() {
        return chickenWrapNum;
    }

    public void setChickenWrapNum(int chickenWrapNum) {
        this.chickenWrapNum = chickenWrapNum;
    }
}
