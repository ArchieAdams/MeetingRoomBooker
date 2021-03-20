package libraryFunctions;

import java.util.ArrayList;

public class printer {
    
    static ArrayList<iPrintable> objectsToPrint = new ArrayList<>();
    
    public static void addObjectsToList(iPrintable item){
        objectsToPrint.add(item);
    }

    //get iPrintable objects and print them all out
    public static void printObjects() {
        for (int i = 0; i < objectsToPrint.size(); i++) {
            System.out.println(objectsToPrint.get(i).getPrintableString());
        }
    }

}
