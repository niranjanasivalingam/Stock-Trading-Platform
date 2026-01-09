public class Transaction {
    String stockSymbol;
    int quantity;
    String type;
    double price;

    public Transaction(String stockSymbol, int quantity, String type, double price) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.type = type;
        this.price = price;
    }
}
