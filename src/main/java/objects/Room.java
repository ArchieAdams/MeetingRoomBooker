package objects;

import libraryFunctions.iPrintable;

public class Room  implements iPrintable {

    private String roomNumber;
    private int capacity;
    private boolean wheelchairAccess;

    public Room(String roomNumber ,int capacity, boolean wheelchairAccess) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.wheelchairAccess = wheelchairAccess;
    }

    @Override
    public String getPrintableString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", wheelchairAccess=" + wheelchairAccess +
                '}';
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", wheelchairAccess=" + wheelchairAccess +
                '}';
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }
}
