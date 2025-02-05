package trial_project_Intrasoft.trial_project_Intrasoft.model;

import java.text.DateFormat;

// Transaction Model Class Definition
public class Transaction {
    private int transactionId;
    private int accountId;
    private double amount;
    private String type;
    private String date;

    // Constructor
    public Transaction(int transactionId, int accountId, double amount, String type, String date){
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    // Get Functions
    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
