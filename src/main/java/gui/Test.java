package gui;

import libraryFunctions.Helper;
import libraryFunctions.Repository;
import objects.Booking;
import objects.User;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Booking booking = new  Booking("A",3,"A", Date.valueOf("2021-03-29"), Time.valueOf("9:00:00"), Time.valueOf("10:00:00"), 5);
        ArrayList<Time> timeArrayList = Repository.getBookingTimes(booking);
        timeArrayList.forEach(System.out::println);
        boolean overLap = new Helper().doTimesOverLap(Time.valueOf("9:00:00"), Time.valueOf("10:00:00"), timeArrayList);
        System.out.println(overLap);
    }
}
