package trial_project_Intrasoft.trial_project_Intrasoft.service;

import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import trial_project_Intrasoft.trial_project_Intrasoft.model.Account;
import trial_project_Intrasoft.trial_project_Intrasoft.model.Beneficiary;
import trial_project_Intrasoft.trial_project_Intrasoft.model.Transaction;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadCSV {

    private final List<Beneficiary> beneficiaries = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    // Constructor to load the csv files after initialization has completed
    @PostConstruct
    public void init(){
        loadBeneficiariesCSV();
        loadAccountsCSV();
        loadTransactionsCSV();
    }

    // Get Functions for the loaded data
    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Load Beneficiaries CSV
    private void loadBeneficiariesCSV(){
        try {
            // Load CSV
            InputStream inputStream = getClass().getResourceAsStream("/beneficiaries.csv");
            if (inputStream == null) {
                throw new RuntimeException("beneficiaries.csv not in resources folder");
            }

            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));

            // Skip CSV header
            String[] header = csvReader.readNext();

            // Load each line
            String[] line;
            while ((line = csvReader.readNext()) != null){
                int beneficiaryId = Integer.parseInt(line[0]);
                String firstName = line[1];
                String lastName = line[2];

                beneficiaries.add(new Beneficiary(beneficiaryId, firstName, lastName));
            }
            csvReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Load Accounts CSV
    private void loadAccountsCSV(){
        try {
            // Load CSV
            InputStream inputStream = getClass().getResourceAsStream("/accounts.csv");
            if (inputStream == null) {
                throw new RuntimeException("accounts.csv not in resources folder");
            }

            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));

            // Skip CSV header
            String[] header = csvReader.readNext();

            // Load each line
            String[] line;
            while ((line = csvReader.readNext()) != null){
                int accountId = Integer.parseInt(line[0]);
                int beneficiaryId = Integer.parseInt(line[1]);

                accounts.add(new Account(accountId, beneficiaryId));
            }
            csvReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Load Transactions CSV
    private void loadTransactionsCSV(){
        try {
            // Load CSV
            InputStream inputStream = getClass().getResourceAsStream("/transactions.csv");
            if (inputStream == null) {
                throw new RuntimeException("transactions.csv not in resources folder");
            }

            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));

            // Skip CSV header
            String[] header = csvReader.readNext();

            // Load each line
            String[] line;
            while ((line = csvReader.readNext()) != null){
                int transactionID = Integer.parseInt(line[0]);
                int accountId = Integer.parseInt(line[1]);
                double amount = Double.parseDouble(line[2]);
                String type = line[3];
                String date = line[4];

                transactions.add(new Transaction(transactionID, accountId, amount, type, date));
            }
            csvReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
