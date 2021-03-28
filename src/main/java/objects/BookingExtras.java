package objects;

public class BookingExtras {

    private int bookingID;
    private boolean projector;
    private int numberOfPens;
    private int numberOfPencils;
    private int sheetsOfPaper;
    private String typeOfPaper;

    public BookingExtras(int bookingID, boolean projector, int numberOfPens, int numberOfPencils, int sheetsOfPaper, String typeOfPaper) {
        this.bookingID = bookingID;
        this.projector = projector;
        this.numberOfPens = numberOfPens;
        this.numberOfPencils = numberOfPencils;
        this.sheetsOfPaper = sheetsOfPaper;
        this.typeOfPaper = typeOfPaper;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public boolean wantsProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public int getNumberOfPens() {
        return numberOfPens;
    }

    public void setNumberOfPens(int numberOfPens) {
        this.numberOfPens = numberOfPens;
    }

    public int getNumberOfPencils() {
        return numberOfPencils;
    }

    public void setNumberOfPencils(int numberOfPencils) {
        this.numberOfPencils = numberOfPencils;
    }

    public int getSheetsOfPaper() {
        return sheetsOfPaper;
    }

    public void setSheetsOfPaper(int sheetsOfPaper) {
        this.sheetsOfPaper = sheetsOfPaper;
    }

    public String getTypeOfPaper() {
        return typeOfPaper;
    }

    public void setTypeOfPaper(String typeOfPaper) {
        this.typeOfPaper = typeOfPaper;
    }
}
