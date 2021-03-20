package libraryFunctions;

import java.util.ArrayList;

public class Printer {
    
    static ArrayList<IPrintable> objectsToPrint = new ArrayList<>();
    
    public static void addObjectsToList(IPrintable item){
        objectsToPrint.add(item);
    }

    //get iPrintable objects and print them all out
    public static void printObjects() {
        for (int i = 0; i < objectsToPrint.size(); i++) {
            System.out.println(objectsToPrint.get(i).getPrintableString());
        }
    }

}
