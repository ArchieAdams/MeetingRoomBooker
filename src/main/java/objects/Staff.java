package objects;

public class Staff {

    private String username;


    public Staff(String username) {
        this.username = username;

    }

    @Override
    public String toString() {
        return "Staff{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
