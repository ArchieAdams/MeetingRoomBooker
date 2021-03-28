package libraryFunctions;


import javafx.scene.control.Label;

import java.sql.Time;
import java.util.ArrayList;

//These functions are ones that can be used anywhere in the project. They are essentially utility funcitons.
public class Helper {

    public static String hashPassword(String password) {
        //This should hash the password (Use a message digest = https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm)
        return password;
    }

    public static boolean compareHashed(String databasePassword, String userEnteredPassword) {
        return hashPassword(userEnteredPassword).equals(databasePassword);
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

    public boolean doTimesOverLap(Time firstStartTime, Time firstEndTime, ArrayList<Time> timesToCompare){
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
                System.out.println(("Your time slot overlaps with " + secondStartTime +" to "+ secondEndTime));
                return true;
            }
        }
        return false;
    }
}
