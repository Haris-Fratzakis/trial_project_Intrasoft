package trial_project_Intrasoft.trial_project_Intrasoft.model;

// Account Model Class Definition
public class Account {
    private int accountId;
    private int beneficiaryID;

    // Constructor
    public Account(int accountId, int beneficiaryID){
        this.accountId = accountId;
        this.beneficiaryID = beneficiaryID;
    }

    // Get Functions
    public int getAccountId() {
        return accountId;
    }

    public int getBeneficiaryID() {
        return beneficiaryID;
    }
}
