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

import java.util.List;


@RestController
@RequestMapping("/api")
public class BeneficiaryController {
    @Autowired
    private LoadCSV LoadCSV;

    @GetMapping("/beneficiaries/{beneficiaryId}")
    public Beneficiary getBeneficiaryById(@PathVariable int beneficiaryId){
        return LoadCSV.getBeneficiaries().stream().filter(beneficiary -> beneficiary.getBeneficiaryId() == beneficiaryId).findFirst().orElse(null);
    }

    @GetMapping("/beneficiaries/{beneficiaryId}/accounts")
    public List<Account> getAccountsByBeneficiaryId(@PathVariable int beneficiaryId){
        return LoadCSV.getAccounts().stream().filter(account -> account.getBeneficiaryID() == beneficiaryId).toList();
    }

    @GetMapping("/beneficiaries/{beneficiaryId}/transactions")
    public List<Transaction> getTransactionsByBeneficiaryId(@PathVariable int beneficiaryId){
        // Find all account Ids of a beneficiary
        List<Integer> accountIds = LoadCSV.getAccounts().stream().filter(account -> account.getBeneficiaryID() == beneficiaryId).map(Account::getAccountId).toList();

        // Find all transactions for each account
        return LoadCSV.getTransactions().stream().filter(transaction -> accountIds.contains(transaction.getAccountId())).toList();
    }
}
