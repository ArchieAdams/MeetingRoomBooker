import libraryFunctions.repository;
import objects.User;

public class Main {
    public static void main(String[] args) {

        System.out.println(repository.userLogIn("AA200223","123"));
        repository.insertNewUser(new User("AA20023", "Archie", "Addams","AA200223@reigate.ac.uk", "123"));
    }
}
