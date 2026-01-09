import java.util.HashMap;

public class User {
    String name;
    double balance;
    HashMap<String, Integer> portfolio = new HashMap<>();

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}
