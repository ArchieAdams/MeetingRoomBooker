package libraryFunctions;


import javafx.scene.control.Label;
import objects.CateringOrder;
import objects.Staff;

import java.security.MessageDigest;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

//These functions are ones that can be used anywhere in the project. They are essentially utility funcitons.
public class Helper {

    public static String hashPassword(String password) {
        //Taken from https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm
        try {
            //Creating the MessageDigest object
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //Passing data to the created MessageDigest Object
            md.update(password.getBytes());

            //Compute the message digest
            byte[] digest = md.digest();

            //Converting the byte array in to HexString format
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
            password = hexString.toString();
        }catch (Exception e){

        }
        return password;
    }


    public boolean isTimeBefore(Time firstTime, Time secondTime){ //Is firstTime after secondTime
        return firstTime.toLocalTime().isBefore(secondTime.toLocalTime());
    }

    public boolean isTimeAfter(Time firstTime, Time secondTime){ //Is firstTime before secondTime
        return firstTime.toLocalTime().isAfter(secondTime.toLocalTime());
    }

    public boolean doTimesOverLap(Time firstStartTime, Time firstEndTime, Time secondStartTime, Time secondEndTime){
        boolean isSecondEndBeforeFirstStart = isTimeBefore(secondEndTime, firstStartTime); //Checks if E2 is before S1 (S2, E2, S1, E1) as End can't be before Start.
        boolean isSecondStartAfterFirstEnd = isTimeAfter(secondStartTime, firstEndTime); //Checks if S2 is after S1 (S1, E1, S2, E2) as End can't be before Start.
        boolean areTimesEqual = firstStartTime.equals(secondStartTime) || firstStartTime.equals(secondEndTime) ||
                                firstEndTime.equals(secondStartTime) || firstEndTime.equals(secondEndTime);
        return !(isSecondEndBeforeFirstStart || isSecondStartAfterFirstEnd) || areTimesEqual;
    }

    public boolean doTimesOverLap(Time firstStartTime, Time firstEndTime, ArrayList<Time> timesToCompare, Label errorLabel){
        for (int i = 1; i <= timesToCompare.size()/2; i++) {
            Time secondStartTime = timesToCompare.get((i*2)-2); //Get consecutive indexes e.g. if i is 5 then ST index is 8 and ET index is 9
            Time secondEndTime = timesToCompare.get((i*2)-1);
            secondStartTime = Time.valueOf(secondStartTime.toLocalTime().minusMinutes(30)); //Subtracts time for cleaning (30 min) before meeting
            secondEndTime = Time.valueOf(secondEndTime.toLocalTime().plusMinutes(30)); //Adds time for cleaning (30 min) after meeting
            boolean isSecondEndBeforeFirstStart = isTimeBefore(secondEndTime, firstStartTime); //Checks if E2 is before S1 (S2, E2, S1, E1) as End can't be before Start.
            boolean isSecondStartAfterFirstEnd = isTimeAfter(secondStartTime, firstEndTime); //Checks if S2 is after S1 (S1, E1, S2, E2) as End can't be before Start.
            boolean areTimesEqual = firstStartTime.equals(secondStartTime) || firstStartTime.equals(secondEndTime) ||
                    firstEndTime.equals(secondStartTime) || firstEndTime.equals(secondEndTime);
            if (!(isSecondEndBeforeFirstStart || isSecondStartAfterFirstEnd) || areTimesEqual){
                errorLabel.setText("Your time slot overlaps with " + secondStartTime +" to "+ secondEndTime);
                return true;
            }
        }
        return false;
    }

    public boolean doTimesOverLap(Time firstStartTime, Time firstEndTime, ArrayList<Time> timesToCompare){ //Use to check if your food order is within your meeting
        for (int i = 1; i <= timesToCompare.size()/2; i++) {
            Time secondStartTime = timesToCompare.get((i*2)-2); //Get consecutive indexes e.g. if i is 5 then ST index is 8 and ET index is 9
            Time secondEndTime = timesToCompare.get((i*2)-1);
            boolean isSecondEndBeforeFirstStart = isTimeBefore(secondEndTime, firstStartTime); //Checks if E2 is before S1 (S2, E2, S1, E1) as End can't be before Start.
            boolean isSecondStartAfterFirstEnd = isTimeAfter(secondStartTime, firstEndTime); //Checks if S2 is after S1 (S1, E1, S2, E2) as End can't be before Start.
            boolean areTimesEqual = firstStartTime.equals(secondStartTime) || firstStartTime.equals(secondEndTime) ||
                    firstEndTime.equals(secondStartTime) || firstEndTime.equals(secondEndTime);
            if (!(isSecondEndBeforeFirstStart || isSecondStartAfterFirstEnd) || areTimesEqual){
                return true;
            }
        }
        return false;
    }

    public CateringOrder changeCateringOrder(int index, int quantity, CateringOrder cateringOrder){ //Changes a variables using a string to get which variable to change.
        ArrayList<Integer> variables = new ArrayList<>(Arrays.asList(cateringOrder.getTeaNum(), cateringOrder.getCoffeeNum(), cateringOrder.getWaterNum(),
                cateringOrder.getOreoNum(), cateringOrder.getQuaversNum(), cateringOrder.getHamSandwichNum(), cateringOrder.getChickenWrapNum()));
        for (int i = 0; i < variables.size(); i++) {
            variables.set(index, quantity);
        }
        return (new CateringOrder(cateringOrder.getBookingID(), cateringOrder.getRoomNumber(),cateringOrder.getTime(), variables.get(0), variables.get(1), variables.get(2), variables.get(3), variables.get(4), variables.get(5), variables.get(6)));
    }

}
