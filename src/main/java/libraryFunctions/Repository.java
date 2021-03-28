package libraryFunctions;

import objects.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//This class gets things in and out of the database

public class Repository {

    private static final String databaseLocation = System.getProperty("user.dir") + "\\RoomBookerDatabase.accdb";
    private static Connection con;
    private static User currentUser;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://" + databaseLocation, "", "");
            return con;
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return null;
    }

// <editor-fold defaultstate="collapsed" desc="User operations">
    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean userLogIn(String username, String password) {
        try {
            String sql = "SELECT * FROM Users where Username = '" + username + "'";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                currentUser = new User(rs.getString("Username"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getString("Password"));
                if (!Helper.compareHashed(currentUser.getPassword(), password)) {
                    return false;
                }
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return currentUser != null;
    }

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Return Arraylist of all objects">
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Users";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                User user = new User(rs.getString("Username"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), rs.getString("Password"));
                userArrayList.add(user);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return userArrayList;
    }

    public static ArrayList<String> getAllUsernames() {
        ArrayList<String> usernameArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Users";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                usernameArrayList.add(rs.getString("Username"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return usernameArrayList;
    }

    public static ArrayList<String> getAllEmails() {
        ArrayList<String> emailArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Users";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                emailArrayList.add(rs.getString("Email"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return emailArrayList;
    }

    public static ArrayList<Room> getAllRooms() {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Rooms";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                Room room = new Room("Room "+rs.getString("RoomNumber"), rs.getInt("Capacity"), rs.getBoolean("WheelchairAccess"));
                roomArrayList.add(room);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return roomArrayList;
    }

    public static ArrayList<String> getAllRoomNumbersAndCapacity() {
        ArrayList<String> roomNumbersArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Rooms";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                roomNumbersArrayList.add("Room "+rs.getString("RoomNumber") + " - capacity = "+rs.getString("Capacity") );
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return roomNumbersArrayList;
    }

    public static ArrayList<String> getAllRoomNumbers() {
        ArrayList<String> roomNumbersArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Rooms";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                roomNumbersArrayList.add(rs.getString("RoomNumber"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return roomNumbersArrayList;
    }

    public static int getBookingId(Booking booking) {
        try {
            String sql ="SELECT Bookings.* FROM Bookings WHERE RoomNumber = '"+booking.getRoomNumber()+"'";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                if (rs.getDate("Date").equals(booking.getDate()) && rs.getTime("StartTime").equals(booking.getStartTime()) &&
                    rs.getTime("EndTime").equals(booking.getEndTime())){
                    return rs.getInt("BookingID");
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return -1;
    }

    public static ArrayList<Time> getBookingTimes(Booking booking) {
        ArrayList<Time> timeArrayList = new ArrayList<>();
        try {
            String sql = "SELECT Bookings.* FROM Bookings WHERE RoomNumber = '"+booking.getRoomNumber()+"'"; //Date = '"+booking.getDate()+"' AND
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                if(rs.getDate("Date").equals(booking.getDate())){
                    timeArrayList.add(Time.valueOf(rs.getString("StartTime")));
                    timeArrayList.add(Time.valueOf(rs.getString("EndTime")));
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error in the repository class: " + e);
            System.out.println("No booking found.");
        }
        return timeArrayList;
    }

    public static ArrayList<Booking> getAllBookings(User user) {
        ArrayList<Booking> bookingArrayList = new ArrayList<>();
        try {
            String sql = "SELECT Bookings.* FROM Bookings WHERE Username = '"+user.getUsername()+"'";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                bookingArrayList.add(new Booking(rs.getString("BookingName"),rs.getInt("RoomNumber"), user.getUsername(),
                                                 rs.getDate("Date"),rs.getTime("StartTime"),rs.getTime("EndTime"),rs.getInt("NumberOfPeople")));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error in the repository class: " + e);
            System.out.println("No booking found.");
        }
        return bookingArrayList;
    }
// </editor-fold>

    
// <editor-fold defaultstate="collapsed" desc="SQL date conversion functions">
    private static String getSQLDateString(Date date) {
        //Be careful with dates in Access queries, they are not in standard SQL format (they often have extra #s and are the wrong way round). 
        //This converts the date into standard SQL format which will work with access when accesses from your java code
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + DATE_FORMAT.format(date) + "'";
    }

    private static String getSQLDateString(String date) {
        try {
            SimpleDateFormat UK_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            return "'" + DATE_FORMAT.format(UK_DATE_FORMAT.parse(date)) + "'";
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return "";
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Insert/Update functions">
    public static void updateUser(User user) {
        try {
            String sql = "SELECT Users.* FROM Users where Username = '"+user.getUsername()+"'";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.updateString("Username", user.getUsername());
                rs.updateString("FirstName", user.getFirstName());
                rs.updateString("LastName", user.getLastName());
                rs.updateString("Email", user.getEmail());
                rs.updateString("Password", user.getPassword());
                rs.updateRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }


    public static void insertNewUser(User user) {
        try {
            String sql = "SELECT Users.* FROM Users";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                System.out.println("hell");
                rs.moveToInsertRow();
                rs.updateString("Username", user.getUsername());
                rs.updateString("FirstName", user.getFirstName());
                rs.updateString("LastName", user.getLastName());
                rs.updateString("Email", user.getEmail());
                rs.updateString("Password", user.getPassword());
                rs.insertRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }

    public static void insertNewBooking(Booking booking) {
        try {
            String sql = "SELECT Bookings.* FROM Bookings";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.moveToInsertRow();
                rs.updateString("BookingName", booking.getBookingName());
                rs.updateInt("RoomNumber", booking.getRoomNumber());
                rs.updateString("Username", booking.getUsername());
                rs.updateDate("Date", booking.getDate());
                rs.updateString("StartTime", booking.getStartTime().toString());
                rs.updateString("EndTime", booking.getEndTime().toString());
                rs.updateInt("NumberOfPeople", booking.getNumberOfPeople());
                rs.insertRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }

    public static void insertBookingExtras(BookingExtras bookingExtras) {
        try {
            String sql = "SELECT BookingExtras.* FROM BookingExtras";
            ResultSet rs = ExecuteSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.moveToInsertRow();
                rs.updateInt("BookingID",bookingExtras.getBookingID());
                rs.updateBoolean("Projector", bookingExtras.wantsProjector());
                rs.updateInt("Pens", bookingExtras.getNumberOfPens());
                rs.updateInt("Pencils", bookingExtras.getNumberOfPencils());
                rs.updateInt("Paper", bookingExtras.getSheetsOfPaper());
                rs.updateString("PaperType", bookingExtras.getTypeOfPaper());
                rs.insertRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }


// </editor-fold>
}
