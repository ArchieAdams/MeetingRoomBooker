package libraryFunctions;

import objects.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
//This class gets things in and out of the database

public class repository {

    private static final String DatabaseLocation = System.getProperty("user.dir") + "\\RoomBookerDatabase.accdb";
    private static Connection con;
    private static User currentUser;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
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
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                currentUser = new User(rs.getString("Username"), rs.getString("First Name"), rs.getString("Last Name"), rs.getString("Email"), rs.getString("Password"));
                if (!helper.CompareHashed(currentUser.getPassword(), password)) {
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
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                User user = new User(rs.getString("Username"), rs.getString("First Name"), rs.getString("Last Name"), rs.getString("Email"), rs.getString("Password"));
                userArrayList.add(user);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return userArrayList;
    }

    public static ArrayList<Room> getAllRooms() {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Rooms";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                Room room = new Room("Room "+rs.getString("Room Number"), rs.getInt("Capacity"), rs.getBoolean("Wheelchair Access"));
                roomArrayList.add(room);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return roomArrayList;
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
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.updateString("Username", user.getUsername());
                rs.updateString("First Name", user.getFirstName());
                rs.updateString("Last Name", user.getLastName());
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
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.moveToInsertRow();
                rs.updateString("Username", user.getUsername());
                rs.updateString("First Name", user.getFirstName());
                rs.updateString("Last Name", user.getLastName());
                rs.updateString("Email", user.getEmail());
                rs.updateString("Password", user.getPassword());
                rs.insertRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }
// </editor-fold>
}
