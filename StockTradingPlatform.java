import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    static ArrayList<Stock> market = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static User user = new User("Trader", 10000);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        market.add(new Stock("AAPL", "Apple", 150));
        market.add(new Stock("GOOG", "Google", 2800));
        market.add(new Stock("TCS", "Tata Consultancy Services", 3500));

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewMarket();
                case 2 -> buyStock(sc);
                case 3 -> sellStock(sc);
                case 4 -> viewPortfolio();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void viewMarket() {
        System.out.println("\nMarket Data:");
        for (Stock s : market) {
            System.out.println(s.symbol + " | " + s.name + " | ₹" + s.price);
        }
    }

    static void buyStock(Scanner sc) {
        viewMarket();
        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        for (Stock s : market) {
            if (s.symbol.equalsIgnoreCase(symbol)) {
                double cost = s.price * qty;
                if (user.balance >= cost) {
                    user.balance -= cost;
                    user.portfolio.put(symbol,
                        user.portfolio.getOrDefault(symbol, 0) + qty);
                    transactions.add(new Transaction(symbol, qty, "BUY", s.price));
                    System.out.println("Stock bought successfully");
                } else {
                    System.out.println("Insufficient balance");
                }
                return;
            }
        }
        System.out.println("Stock not found");
    }

    static void sellStock(Scanner sc) {
        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        if (user.portfolio.containsKey(symbol) && user.portfolio.get(symbol) >= qty) {
            for (Stock s : market) {
                if (s.symbol.equalsIgnoreCase(symbol)) {
                    user.balance += s.price * qty;
                    user.portfolio.put(symbol, user.portfolio.get(symbol) - qty);
                    transactions.add(new Transaction(symbol, qty, "SELL", s.price));
                    System.out.println("Stock sold successfully");
                    return;
                }
            }
        }
        System.out.println("Not enough stock to sell");
    }

    static void viewPortfolio() {
        System.out.println("\nPortfolio:");
        for (String key : user.portfolio.keySet()) {
            System.out.println(key + " : " + user.portfolio.get(key));
        }
        System.out.println("Balance: ₹" + user.balance);
    }
}
