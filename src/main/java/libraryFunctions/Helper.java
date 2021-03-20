package libraryFunctions;

//These functions are ones that can be used anywhere in the project. They are essentially utility funcitons.
public class Helper {

    public static String hashPassword(String password) {
        //This should hash the password (Use a message digest = https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm)
        return password;
    }

    public static boolean compareHashed(String databasePassword, String userEnteredPassword) {
        return hashPassword(userEnteredPassword).equals(databasePassword);
    }

}
