package trial_project_Intrasoft.trial_project_Intrasoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trial_project_Intrasoft.trial_project_Intrasoft.model.Account;
import trial_project_Intrasoft.trial_project_Intrasoft.model.Beneficiary;
import trial_project_Intrasoft.trial_project_Intrasoft.model.Transaction;
import trial_project_Intrasoft.trial_project_Intrasoft.service.LoadCSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class BeneficiaryController {
    @Autowired
    private LoadCSV LoadCSV;

    // Endpoint 1
    @GetMapping("/beneficiaries/{beneficiaryId}")
    public Beneficiary getBeneficiaryById(@PathVariable int beneficiaryId){
        return LoadCSV.getBeneficiaries().stream().filter(beneficiary -> beneficiary.getBeneficiaryId() == beneficiaryId).findFirst().orElse(null);
    }

    // Endpoint 2
    @GetMapping("/beneficiaries/{beneficiaryId}/accounts")
    public List<Account> getAccountsByBeneficiaryId(@PathVariable int beneficiaryId){
        return LoadCSV.getAccounts().stream().filter(account -> account.getBeneficiaryID() == beneficiaryId).toList();
    }

    // Endpoint 3
    @GetMapping("/beneficiaries/{beneficiaryId}/transactions")
    public List<Transaction> getTransactionsByBeneficiaryId(@PathVariable int beneficiaryId){
        // Find all account Ids of a beneficiary
        List<Integer> accountIds = LoadCSV.getAccounts().stream().filter(account -> account.getBeneficiaryID() == beneficiaryId).map(Account::getAccountId).toList();

        // Find all transactions for each account
        return LoadCSV.getTransactions().stream().filter(transaction -> accountIds.contains(transaction.getAccountId())).toList();
    }

    // Endpoint 4
    @GetMapping("/beneficiaries/{beneficiaryId}/balanceByTransactions")
    public List<Map<Integer, Double>> getBalanceByTransactions(@PathVariable int beneficiaryId){
        // Find all account Ids of a beneficiary
        List<Integer> accountIds = LoadCSV.getAccounts().stream().filter(account -> account.getBeneficiaryID() == beneficiaryId).map(Account::getAccountId).toList();

        // Find all transactions for each account
        List<Transaction> transactionsByBeneficiaryId = LoadCSV.getTransactions().stream().filter(transaction -> accountIds.contains(transaction.getAccountId())).toList();

        // Group all transactions by their account Id
        Map<Integer, List<Transaction>> transactionsOfBeneficiaryByAccount = transactionsByBeneficiaryId.stream().collect(Collectors.groupingBy(Transaction::getAccountId));

        // Calculate balance based on transactions for each account
        List<Map<Integer, Double>> beneficiaryBalancePerAccount = new ArrayList<>();
        for (Map.Entry<Integer, List<Transaction>> accountEntry : transactionsOfBeneficiaryByAccount.entrySet()) {
            int accountId = accountEntry.getKey();
            List<Transaction> transactionsPerAccount = accountEntry.getValue();

            double balance = 0.0;
            for (Transaction transaction : transactionsPerAccount){
                if (transaction.getType().equals("deposit")){
                    balance += transaction.getAmount();
                } else if (transaction.getType().equals("withdrawal")) {
                    balance -= transaction.getAmount();
                }
                else {
                    throw new RuntimeException("wrong transaction type");
                }
            }

            Map<Integer, Double> balancePerAccount = new HashMap<>();
            balancePerAccount.put(accountId, balance);
            beneficiaryBalancePerAccount.add(balancePerAccount);
        }

        return beneficiaryBalancePerAccount;
    }
}
